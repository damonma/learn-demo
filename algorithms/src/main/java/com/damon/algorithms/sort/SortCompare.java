package com.damon.algorithms.sort;

/**
 * @author damon
 * @version 2018/11/23
 */
public class SortCompare {

    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();

        if (alg.equals("Shell")) {
            Shell.sort(a);
        }
        if (alg.equals("Shell1")) {
            Shell1.sort(a);
        }

        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
                a[i] = Math.random();
            }

            total += time(alg, a);
        }

        return total;
    }

    public static void main(String[] args) {
        int N = 100000;
        int T = 1;

        double t1 = timeRandomInput("Shell", N, T);
        double t2 = timeRandomInput("Shell1", N, T);

        System.out.println(t1 + "    " + t2 + "    " + t2 / t1);
    }
}
