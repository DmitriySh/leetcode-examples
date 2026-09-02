package ru.shishmakov.problems;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 1464 - Maximum Product of Two Elements in an Array.
 * <p>
 * Given the array of integers nums, you will choose 2 different indices 'i' and 'j' of that array.
 * Returns the maximum product value of {@code (nums[i] - 1) * (nums[j] - 1)} .
 *
 * <p>
 * Notes:
 * <ul>
 *     <li>all array elements are positive;</li>
 *     <li>array length = [2 .. 500].</li>
 * </ul>
 *
 * <p>
 * <pre>
 * Example 1:
 * nums    = [9, 4, 2, 5, 3]
 * indexes =  0  1  2  3  4
 *         i=0, j=3 => (9-1) * (5-1) = 32
 * result = 32
 *
 * Example 2:
 * nums    = [1, 5, 4, 5]
 * indexes =  0  1  2  3
 *         i=1, j=3 => (5-1) * (5-1) = 16
 * result = 16
 * </pre>
 * <a href="https://leetcode.ca/2019-12-03-1464-Maximum-Product-of-Two-Elements-in-an-Array/">Maximum Product of Two Elements in an Array: problem solution</a><br/>
 */
public class MaximumProductTwoElementsArray implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_ARRAY = new int[]{9, 4, 2, 5, 3};
    public static final int DEFAULT_PRODUCT = 32;

    private final int[] array;
    private int product;

    public MaximumProductTwoElementsArray(int[] array) {
        this.array = array;
    }

    public int getProduct() {
        return product;
    }

    @Override
    public void run() {
        logger.info("Start finding the maximum product value in the array: {}", array);

        this.product = findMaximumProduct(array);
        logger.info("Result. Maximum product value (nums[i] - 1) * (nums[j] - 1): {} from array", this.product);
    }

    private static int findMaximumProduct(int[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("Array length is not enough");
        }

        int max1 = array[0];   // наименьший из макс
        int max2 = array[1];   // наибольший из макс
        for (int i = 2; i < array.length; i++) {
            if (array[i] > max2) {
                if (max2 > max1) {
                    max1 = max2;
                }
                max2 = array[i];
            } else if (array[i] > max1) {
                max1 = array[i];
            }
        }
        System.out.printf("max1 = %s; max2 = %s\n\n", max1, max2);

        return (max1 - 1) * (max2 - 1);
    }
}
