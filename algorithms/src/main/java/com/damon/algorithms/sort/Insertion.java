package com.damon.algorithms.sort;

/**
 * @author damon
 * @version 2018/11/23
 */
public class Insertion {

    public static void sort(Comparable[] a) {
        int N = a.length;

            for (int i = 1; i < N; i++) {
                Comparable temp = a[i];
                int j = i;
                for (; j > 0 && less(temp, a[j - 1]); j -= 1) {
//                    exch(a, j, j - 1);
                    a[j] = a[j-1];
                }
                a[j] = temp;
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
