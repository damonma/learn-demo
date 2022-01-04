package com.damon.guava.recommend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author damon
 * @version 2018/7/18
 */
public class Charpter2 {

    void splitData(List<Map<String, String>> data, int m, int k, int seed) {
        List<Map<String, String>> test = new ArrayList<>();
        List<Map<String, String>> train = new ArrayList<>();
        Random random = new Random();
        random.setSeed(seed);
        for (Map<String, String> map : data) {
            if (random.nextInt(m) == k) {
                test.add(map);
            } else {
                train.add(map);
            }
        }
    }

    void recall(List<Map<String, String>> train, List<Map<String, String>> test, int n) {

    }
}
