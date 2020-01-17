package com.damon.algorithms.sort;

/**
 * @author damon
 * @version 2018/11/23
 */
public class Shell1 {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int d = a.length;
        while(d > 1)
        {
            d = d / 2;
            for(int x = 0; x < d; x++)
            {
                for(int i=x+d;i<N;i=i+d)
                {
                    Comparable temp = a[i];
                    int j = i - d;
                    for(; j >=0 && less(temp, a[j]);j=j-d)
                    {
                        a[j+d]=a[j];
                    }
                    a[j+d]=temp;
                }
            }
//            if(d==1)
//            {
//                break;
//            }
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
