package com.damon.webapp;

import java.util.*;

/**
 * @author damon
 * @description
 * @time 2021/12/26 下午8:12
 */
public class LotteryService {

    public static void main(String[] args) {
        LotteryService lotteryService = new LotteryService();
        lotteryService.chromosphere(5);
        lotteryService.superLotto(5);
        lotteryService.markSix(2);
    }

    public void chromosphere(int stakeNum) {
        List<Stake> stakes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < stakeNum; i++) {
            // 红色球
            Set<Integer> red = new TreeSet<>();
            while (red.size() < 6) {
                red.add(random.nextInt(33) + 1);
            }

            // 蓝色球
            Set<Integer> blue = new TreeSet<>();
            blue.add(random.nextInt(16) + 1);

            Stake stake = new Stake();
            stake.setRed(red);
            stake.setBlue(blue);
            stakes.add(stake);
        }
        System.out.println("双色球：");
        for (Stake stake : stakes) {
            System.out.println(stake.toString());
        }
    }

    public void superLotto(int stakeNum) {
        List<Stake> stakes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < stakeNum; i++) {
            // 红色球
            Set<Integer> red = new TreeSet<>();
            while (red.size() < 5) {
                red.add(random.nextInt(35) + 1);
            }

            // 蓝色球
            Set<Integer> blue = new TreeSet<>();
            while (blue.size() < 2) {
                blue.add(random.nextInt(12) + 1);
            }
            Stake stake = new Stake();
            stake.setRed(red);
            stake.setBlue(blue);
            stakes.add(stake);
        }
        System.out.println("大乐透：");
        for (Stake stake : stakes) {
            System.out.println(stake.toString());
        }
    }

    public void markSix(int stakeNum) {
        List<Stake> stakes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            Set<Integer> set = new TreeSet<>();
            while (set.size() < 6) {
                set.add(random.nextInt(49) + 1);
            }
            Stake stake = new Stake();
            stake.setRed(set);
            stakes.add(stake);
        }

        System.out.println("六合彩：");
        for (Stake stake : stakes) {
            System.out.println(stake.toString());
        }
    }

    static class Stake {
        private Set<Integer> red;

        private Set<Integer> blue;

        public Set<Integer> getRed() {
            return red;
        }

        public void setRed(Set<Integer> red) {
            this.red = red;
        }

        public Set<Integer> getBlue() {
            return blue;
        }

        public void setBlue(Set<Integer> blue) {
            this.blue = blue;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("Stake:");
            if (null != red && !red.isEmpty()) {
                builder.append("red").append(red);
            }
            if (null != blue && !blue.isEmpty()) {
                builder.append(", ").append("blue").append(blue);
            }

            return builder.toString();
        }
    }
}
