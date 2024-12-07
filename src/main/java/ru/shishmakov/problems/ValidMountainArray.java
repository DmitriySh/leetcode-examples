package ru.shishmakov.problems;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 941 - Valid Mountain Array.
 * <p>
 * Given an array of integers, return true if and only if it is a valid mountain array.
 * An input array of integers is valid if:
 * <ul>
 *     <li>array.length >= 3;</li>
 *     <li>have a strictly increasing part of the array (arr[0] < arr[1] < ... < arr[i - 1] < arr[i])</li>
 *     <li>have a strictly decreasing part of the array (arr[i] > arr[i + 1] > ... > arr[arr.length - 1])</li>
 * </ul>
 *
 * <pre>
 * Example:
 *   array   = [0, 2, 3, 4, 2, 1]
 *   valid  = true
 *
 *   text   = [0, 2, 1]
 *   valid  = true
 *
 *   text   = [0, 2, 3]
 *   valid  = false
 *
 *   text   = [0, 2, 3, 3, 4, 2]
 *   valid  = false
 * </pre>
 */
public class ValidMountainArray implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_ARRAY = new int[]{0, 2, 3, 4, 2, 1};

    private final int[] array;
    private boolean valid;

    public ValidMountainArray(int[] array) {
        this.array = array;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public void run() {
        logger.info("Start validating array...");
        logger.info("Array is: {}", Arrays.toString(array));

        this.valid = validMountainArray(array);
        logger.info("Result. Mountain array is valid: {}", this.valid);
    }

    private boolean validMountainArray(int[] array) {
        if (array.length < 3) {
            return false;
        }

        int middle = 0;
        for (int i = 0; i < array.length - 1; i++) {
            // growth
            if (array[i] < array[i + 1] && middle == 0) {
                continue;
            } else {
                middle = i;
            }

            // fall
            if (array[i] > array[i + 1] && middle != 0) {
                continue;
            } else {
                return false;
            }
        }
        return middle != 0;
    }
}
