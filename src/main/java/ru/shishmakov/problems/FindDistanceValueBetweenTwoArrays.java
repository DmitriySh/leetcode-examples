package ru.shishmakov.problems;

import java.util.Arrays;

/**
 * 1385 - Find the Distance Value Between Two Arrays.
 * <p>
 * Given two integer arrays 'array1' and 'array2', and the integer 'threshold', return the distance value between the two arrays.
 * <p>
 * <a href="https://leetcode.ca/2019-09-15-1385-Find-the-Distance-Value-Between-Two-Arrays/">Find the Distance Value Between Two Arrays</a><br/>
 */
public class FindDistanceValueBetweenTwoArrays implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {
//        int[] array1 = {4, 5, 8}; int[] array2 = {10, 9, 1, 8}; int result = 2; int d = 2;
//        int[] array1 = {1, 4, 2, 3}; int[] array2 = {-4, -3, 6, 10, 20, 30}; int result = 2; int d = 3;
//        int[] array1 = {4,-3,-7,0,-10}; int[] array2 = {10}; int result = 0; int d = 69;
        int[] array1 = {4, -3, -7, 0, -10};
        int[] array2 = {};
        int result = 5;
        int d = 7;

        int distance = findTheDistance(array1, array2, d);
        if (distance != result) {
            throw new RuntimeException(distance + " not equal " + result);
        }
    }

    // threshold = 2
    // array1 = {4, 5, 8} по значениям которого ищем
    // array2 = {1, 8, 9, 10} в котором ищем
    // найти числа в 'array', которые находятся в диапазоне значений
    // [(value - threshold) .. (value + threshold)]
    // ranges = 4 => [2 .. 6] диапазон значений
    //          5 => [3 .. 7] диапазон значений
    //          8 => [6 .. 10] диапазон значений
    private static int findTheDistance(int[] array1, int[] array2, int threshold) {
        System.out.println("Threshold: " + threshold);
        System.out.println("Origin array1: " + Arrays.toString(array1));
        System.out.println("Origin array2: " + Arrays.toString(array2));

        // {10, 9, 1, 8} => {1, 8, 9, 10}
        Arrays.sort(array2);
        System.out.println("Sorted array2: " + Arrays.toString(array2));

        int distance = 0;
        for (int value : array1) {
            boolean found = hasValueInRange(array2, value - threshold, value + threshold);
            if (found) {
                System.out.printf("Value = %s found in range [%s .. %s]\n", value, value - threshold, value + threshold);
            } else {
                System.out.printf("Value = %s not found in array %s\n", value, Arrays.toString(array2));
                distance++;
            }
        }

        System.out.printf("Count distance: %s between arrays", distance);
        return distance;
    }

    private static boolean hasValueInRange(
            final int[] sortedArray,
            final int leftPartRange,
            final int rightPartRange
    ) {
        int leftIndex = 0;
        int rightIndex = sortedArray.length;

        // array = [1,   8,   9,   10].length
        //          0    1    2    3
        //         left-->            right
        while (leftIndex < rightIndex) {
            int middle = (leftIndex + rightIndex) >> 1;
            int value = sortedArray[middle];
            if (leftPartRange <= value && value <= rightPartRange) {
                // within the range
                return true;
            }

            if (leftPartRange <= value) {
                rightIndex = middle; // move to the left
            } else {
                leftIndex = middle + 1; // move to the right
            }
        }
        return false;
    }
}
