package com.damon.algorithms.sort;

/**
 * @author damon
 * @version 2018/11/23
 */
public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h +1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                Comparable temp = a[i];
                int j = i;
                for (; j >= h && less(temp, a[j - h]); j -= h) {
//                    exch(a, j, j - h);
                    a[j] = a[j - h];
                }
                a[j] = temp;
            }

            h /= 3;
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }

        return true;
    }
}
