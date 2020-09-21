package com.learning.java.algorithm.array;

import static com.learning.java.algorithm.sort.ArrayUtils.*;

public class ArrayRotate {

    public static int findRotateIndex(int[] array) {
        if (array == null || array.length == 0) return -1;
        if (array.length == 1) return 0;

        int left = 0, right = array.length - 1;
        while (array[left] > array[right]) {
            int mid = (left + right) / 2;
            if (array[mid] > array[mid + 1]) {
                return mid + 1;
            }
            if (array[left] < array[mid]) {
                left = mid;
            } else if (array[left] > array[mid]) {
                right = mid;
            } else {
                left++;
            }
        }
        return left;
    }

    public static int searchTargetIndex(int[] array, int target) {
        if (array == null || array.length == 0) return -1;
        if (array.length == 1) return array[0] == target ? 0 : -1;

        int rotateIndex = findRotateIndex(array);
        if (rotateIndex == 0) {
            return searchTargetIndexSorted(array, target, 0, array.length - 1);
        }
        if (array[0] == target) {
            return 0;
        } else if (array[0] < target) {
            return searchTargetIndexSorted(array, target, 0, rotateIndex - 1);
        } else {
            return searchTargetIndexSorted(array, target, rotateIndex, array.length - 1);
        }
    }

    public static int searchTargetIndexSorted(int[] array, int target, int left, int right) {
        if (array == null || array.length == 0) return -1;
        if (array.length == 1) return array[0] == target ? 0 : -1;

        while (left <= right) {
            int pivot = (left + right) / 2;
            if (array[pivot] < target) {
                left = pivot + 1;
            } else if (array[pivot] > target) {
                right = pivot - 1;
            } else {
                return pivot;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        print(Array4);
        System.out.println("Min of this Array1: " + Array4[findRotateIndex(Array4)]);
        print(Array5);
        System.out.println("Min of this Array1: " + Array5[findRotateIndex(Array5)]);
        System.out.println("Index of target 7: " + searchTargetIndex(Array5, 7));
        System.out.println("Index of target 9: " + searchTargetIndex(Array5, 9));
        System.out.println("Index of target 2: " + searchTargetIndex(Array5, 2));
        print(Array6);
        System.out.println("Min of this Array1: " + Array6[findRotateIndex(Array6)]);
    }

}
