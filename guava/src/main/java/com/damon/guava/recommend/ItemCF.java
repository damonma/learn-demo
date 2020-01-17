package com.damon.guava.recommend;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author damon
 * @version 2018/7/18
 */
public class ItemCF {

    Table<String, String, Integer> train = HashBasedTable.create();

    void readData(String[] uid_score_bid) {
        for (String str : uid_score_bid) {
            String[] strings = str.split(",");
            train.put(strings[0], strings[2], Integer.valueOf(strings[1]));
        }

        System.out.println(JSON.toJSONString(train.rowMap()));
    }

    Table<String, String, Double> itemSimilarity() {
        Table<String, String, Double> cTable = HashBasedTable.create();
        Map<String, Integer> nMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> firstEntry : train.rowMap().entrySet()) {
            for (Map.Entry<String, Integer> secondEntry : firstEntry.getValue().entrySet()) {
                int count = nMap.getOrDefault(secondEntry.getKey(), 0);
                count++;
                nMap.put(secondEntry.getKey(), count);
                for (Map.Entry<String, Integer> thirdEntry : firstEntry.getValue().entrySet()) {
                    if (thirdEntry.getKey().equals(secondEntry.getKey())) {
                        continue;
                    }
                    Double val = cTable.get(secondEntry.getKey(), thirdEntry.getKey());
                    double count2 = val == null ? 0 : val;
//                    count2++; 第一版
                    count2 += 1 / Math.log(1 + firstEntry.getValue().size() * 1.0);
                    cTable.put(secondEntry.getKey(), thirdEntry.getKey(), count2);
                }
            }
        }

        System.out.println("N:" + JSON.toJSONString(nMap));
        System.out.println("C:" + JSON.toJSONString(cTable.rowMap()));

        Map<String, Double> wTableMax = new HashMap<>();
        Table<String, String, Double> wTable = HashBasedTable.create();
        for (Map.Entry<String, Map<String, Double>> outEntry : cTable.rowMap().entrySet()) {
            for (Map.Entry<String, Double> innerEntry : outEntry.getValue().entrySet()) {
                Double value = innerEntry.getValue() / Math.sqrt(nMap.get(outEntry.getKey()) * nMap.get(innerEntry.getKey()));
                wTable.put(outEntry.getKey(), innerEntry.getKey(), value);
                wTableMax.put(innerEntry.getKey(), 0.0);
                if (value > wTableMax.get(innerEntry.getKey())) {
                    wTableMax.put(innerEntry.getKey(), value);
                }
            }
        }

        System.out.println("wTable:" + JSON.toJSONString(wTable.rowMap()));
//        for (Map.Entry<String, Map<String, Double>> entry : wTable.rowMap().entrySet()) {
//            System.out.println(entry.getKey() + ":" + JSON.toJSONString(entry.getValue()));
//        }

        for (Map.Entry<String, Map<String, Double>> outEntry : cTable.rowMap().entrySet()) {
            for (Map.Entry<String, Double> innerEntry : outEntry.getValue().entrySet()) {
                double value = wTable.get(outEntry.getKey(), innerEntry.getKey()) / wTableMax.get(innerEntry.getKey());
                wTable.put(outEntry.getKey(), innerEntry.getKey(), value);
            }
        }

        System.out.println("xTableMax:" + JSON.toJSONString(wTableMax));
//        for (Map.Entry<String, Double> entry : wTableMax.entrySet()) {
//            System.out.println(entry.getKey() + ":" + JSON.toJSONString(entry.getValue()));
//        }

        System.out.println("wTable:");
        for (Map.Entry<String, Map<String, Double>> entry : wTable.rowMap().entrySet()) {
            System.out.println(entry.getKey() + ":" + JSON.toJSONString(entry.getValue()));
        }

        return wTable;
    }

    Map<String, Double> recommend(Table<String, String, Double> wTable, String user, int k, int n) {
        Map<String, Double> rank = new HashMap<>();
        Map<String, Integer> action_item = train.row(user);
        for (Map.Entry<String, Integer> outEntry : action_item.entrySet()) {
            for (Map.Entry<String, Double> innerEntry : sorted(wTable.column(outEntry.getKey()), k).entrySet()) {
                if (action_item.containsKey(innerEntry.getKey())) {
                    continue;
                }

                double count = rank.getOrDefault(innerEntry.getKey(), 0.0);
                count += outEntry.getValue() * innerEntry.getValue();
                rank.put(innerEntry.getKey(), count);
            }
        }

        return sorted(rank, n);
    }

    private Map<String, Double> sorted(Map<String, Double> map, int k) {
        Map<String, Double> result = new LinkedHashMap<>();
        new ArrayList<>(map.entrySet()).stream().sorted(Comparator.comparing(Map.Entry::getValue)).limit(k).forEach
                (entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(1/(Math.log(1) + 3 * 1.0));

//        String[] uid_score_bid = {"A,1,a","A,1,b","A,1,d","B,1,b","B,1,c","B,1,e","C,1,c","C,1,d","D,1,b",
//                "D,1,c", "D,1,d","E,1,a","E,1,d"};
        String[] uid_score_bid = {"A,1,a","A,1,b","A,1,c","B,1,d","B,1,e","B,1,f","C,1,g","C,1,h","D,1,i",
                "D,1,j", "D,1,k","E,1,l","E,1,m"};
        ItemCF itemCF = new ItemCF();
        itemCF.readData(uid_score_bid);
        Table<String, String, Double> wTable = itemCF.itemSimilarity();
        Map<String, Double> map = itemCF.recommend(wTable, "A", 3, 10);
        System.out.println("result:");
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
