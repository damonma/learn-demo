package com.damon.algorithms.sort;

/**
 * @author damon
 * @version 2018/11/22
 */
public class SortTest {

    public static void main(String[] args) {
        Integer[] array = {1, 4, 7, 3, 9, 2, 5, 8, 6};
//        Selection.sort(array);
//        Selection.show(array);

//        Insertion.sort(array);
//        Insertion.show(array);

//        Shell.sort(array);
//        Shell.show(array);

        Shell1.sort(array);
        Shell1.show(array);
    }
}
