package com.learning.java.algorithm.sort;

public class ArrayUtils {

    public static int[] array = {8, 5, 6, 2, 0, 9, 3, 1, 4, 7};
    public static int[] array2 = {8, 8, 6, 6, 6, 9, 3, 1, 4, 4};
    public static int[] array3 = {2, 0, 1, 1, 0, 2, 1, 1, 0, 2};

    public static void print(int[] array) {
        if (emptyArray(array)) return;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

    public static void swap(int[] array, int i, int j) {
        if (noNeedOperation(array)) return;
        if (i < 0 || i >= array.length || j < 0 || j>= array.length) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static boolean emptyArray(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean noNeedOperation(int[] array) {
        return emptyArray(array) || array.length == 1;
    }

}
