package com.damon.algorithms.interview;


import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author damon
 * @version 2020/1/16
 */
public class Median {
    /**
     * 题目：请使用1个内存为128M的JVM，对随机生成的1亿个 1-10亿的正整数求中位数。
     */

    /**
     * 思路：因为有1亿个数据，且内存只有128M，因此无法把数据全部加载，采用分区间统计的思路来解决
     * 1.数据总个数为1亿，是偶数，因此中位数是第5千万个数据
     * 2.数据范围为1~10亿，因此区间分成1~10（为了简便起见，区间不使用文件，而是数组，直接存在内存）
     * 3.利用随机函数生成1亿个数据，并存储到10个区间
     * 4.统计每个区间的数据总个数，然后从区间1到区间10计算总和
     * 5.若区间n之前的总个数小于5千万，区间n+1之前的总个数大于5千万，则中位数必在区间n
     * 6.计算区间n之前的区间总个数a，并用中位数位置position - a得到新的position
     * 6.把新的position代入下一轮，采用同样的思路继续处理区间n的数据，只到找到中位数
     */

    /**
     * 寻找中位数
     *
     * @param nums 目标数据
     * @param min 目标数据最小范围
     * @param max 目标数据最大范围
     * @param position 中位数位置
     * @return 中位数
     */
    public static int findMedian(List<Integer> nums, int min, int max, int position) {
        // 判断是否已到达区间最小个数，若是，则排序，求中位数
        int length = nums.size();
        if (length <= 10) {
            Collections.sort(nums);
            position = position == 0 ? position : position - 1;
            return nums.get(position);
        }

        // 设置区间最大、最小值
        Map<Integer, Range> rangeMap = new HashMap<>();
        int segment = (max - min + 1) / 10;
        for (int i = 0; i < 10; i++) {
            int low = i * segment + min;
            int high = (i + 1) * segment + min - 1;
            rangeMap.put(i, new Range(low, high));
        }

        // 将数据划分至对应区间
        Map<Integer, List<Integer>> numMap = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            for (Map.Entry<Integer, Range> entry : rangeMap.entrySet()) {
                Range range = entry.getValue();
                if (range.getLow() <= num && range.getHigh() >= num) {
                    List<Integer> numList = numMap.get(entry.getKey());
                    if (null == numList) {
                        numList = new ArrayList<>();
                    }
                    numList.add(num);
                    numMap.put(entry.getKey(), numList);
                }
            }
        }

        // 计算区间数据的个数和，判断中位数所在区间，并计算中位数在新区间的位置
        int count = 0;
        List<Integer> subNums = new ArrayList<>();
        int index = 0;
        for (Map.Entry<Integer, List<Integer>> entry : numMap.entrySet()) {
            int size = entry.getValue().size();
            if (count >= position - size) {
                index = entry.getKey();
                subNums = entry.getValue();
                position -= count;
                break;
            }
            count += size;
        }

        // 获取到目标区间范围，继续递归处理
        Range range = rangeMap.get(index);
        return findMedian(subNums, range.getLow(), range.getHigh(), position);
    }

    public static void main(String[] args) {
        test1();
//        test2();
//        test3();
//        test4();
    }

    private static void test1() {
//        int n = 100000000; // 总个数
        int n = 10000; // 总个数
        int min = 1; // 数据最小值
//        int max = 1000000000; // 数据最大值
        int max = 100000; // 数据最大值
        int position = (n + 1) / 2; // 中位数位置
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = (int) (Math.random() * max + 1);
            nums.add(num);
        }
        System.out.println(findMedian(nums, min, max, position));
    }

    private static void test2() {
        int n = 13; // 总个数
        int min = 1; // 数据最小值
        int max = 100; // 数据最大值
        int position = (n + 1) / 2; // 中位数位置
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = (int) (Math.random() * max + 1);
            nums.add(num);
        }
        System.out.println(findMedian(nums, min, max, position));
        Collections.sort(nums);
        for (Integer num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void test3() {
        int min = 1; // 数据最小值
        int max = 100; // 数据最大值
        List<Integer> nums = new ArrayList<>();
        nums.add(10);
        nums.add(30);
        nums.add(70);
        nums.add(40);
        nums.add(90);
        nums.add(20);
        nums.add(50);
        nums.add(60);
        nums.add(80);
        nums.add(55);
        nums.add(10);
        nums.add(15); // [10,10,15,20,30,40,50,55,60,70,80,90] 输出40
        nums.add(85); // [10,10,15,20,30,40,50,55,60,70,80,85,90] 输出50
        int position = (nums.size() + 1) / 2; // 中位数位置
        System.out.println(findMedian(nums, min, max, position));
        Collections.sort(nums);
        for (Integer num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void test4() {
//        List<Integer> nums = Lists.newArrayList(14, 22, 36, 40, 40, 47, 54, 62, 63, 85, 87, 91, 98);
        List<Integer> nums = Lists.newArrayList(14, 22, 23, 31, 32, 33, 61, 62, 63, 85, 87, 91);
        int position = (nums.size() + 1) / 2; // 中位数位置
        System.out.println(findMedian(nums, 1, 100, position));
    }

    static class Range {
        private int low;
        private int high;

        Range(int low, int high) {
            this.low = low;
            this.high = high;
        }

        public int getLow() {
            return low;
        }

        public int getHigh() {
            return high;
        }
    }
}
