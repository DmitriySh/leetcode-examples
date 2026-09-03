package ru.shishmakov.problems;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1385 - Find the Distance Value Between Two Arrays.
 * <p>
 * Given two integer arrays 'array1' and 'array2', and the integer 'threshold', return the <u>distance value</u> between the two arrays.
 * The <u>distance value</u> is defined as the number of elements {@code array1[i]} such that there is not any element
 * in {@code array2[j]} where {@code |arr1[i]-arr2[j]| <= d}.
 * <p>
 * <pre>
 * Example:
 *     threshold = 2
 *     array1 = [4, 5, 8] (what, values we are looking for)
 *     array2 = [1, 8, 9, 10] (where, values we are looking for)
 *
 *     [(value - threshold) .. (value + threshold)] each value from array1 has own range of values
 *     ranges = 4 => [2 .. 6] no values in array2 (distance++)
 *              5 => [3 .. 7] no values in array2 (distance++)
 *              8 => [6 .. 10] has value in array2
 *     distance = 2
 * </pre>
 * <a href="https://leetcode.ca/2019-09-15-1385-Find-the-Distance-Value-Between-Two-Arrays/">Find the Distance Value Between Two Arrays</a><br/>
 */
public class FindDistanceBetweenTwoArrays implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_ARRAY1 = new int[]{4, 5, 8};
    public static final int[] DEFAULT_ARRAY2 = new int[]{10, 9, 1, 8};
    public static final int DEFAULT_THRESHOLD = 2;

    private final int[] array1;
    private final int[] array2;
    private final int threshold;

    private int distance;

    public FindDistanceBetweenTwoArrays(int[] array1, int[] array2, int threshold) {
        this.array1 = array1;
        this.array2 = array2;
        this.threshold = threshold;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public void run() {
        logger.info(
                "Start find distance value between the two arrays first: {}, second: {}",
                Arrays.toString(array1), Arrays.toString(array2)
        );

        this.distance = findTheDistance(array1, array2, threshold);
        logger.info("Result. Calculated distance: {} between the two arrays", distance);
    }

    private static int findTheDistance(int[] array1, int[] array2, int threshold) {
        logger.info("Threshold: {}", threshold);
        logger.info("Origin array1: {}", array1);
        logger.info("Origin array2: {}", array2);

        // {10, 9, 1, 8} => {1, 8, 9, 10}
        Arrays.sort(array2);
        logger.info("Sorted array2: {}", array2);

        int distance = 0;
        for (int value : array1) {
            int leftPartRange = value - threshold;
            int rightPartRange = value + threshold;
            boolean found = hasValueInRange(array2, leftPartRange, rightPartRange);
            if (found) {
                logger.info(
                        "Value = {} found in range [{} .. {}] in array {}",
                        value, leftPartRange, rightPartRange, array2
                );
            } else {
                logger.info(
                        "Value = {} not found in range [{} .. {}] in array {}",
                        value, leftPartRange, rightPartRange, array2
                );
                distance++;
            }
        }

        return distance;
    }

    private static boolean hasValueInRange(
            final int[] sortedArray,
            final int leftPartRange,
            final int rightPartRange
    ) {
        int left = 0;
        int right = sortedArray.length;

        // array = [1,   8,   9,   10].length
        //          0    1    2    3
        //         left-->            right
        while (left < right) {
            int middle = (left + right) >> 1; // divide by 2
            int value = sortedArray[middle];
            if (leftPartRange <= value && value <= rightPartRange) {
                // within the range
                return true;
            }

            if (leftPartRange <= value) {
                right = middle; // move to the left
            } else {
                left = middle + 1; // move to the right
            }
        }
        return false;
    }
}
