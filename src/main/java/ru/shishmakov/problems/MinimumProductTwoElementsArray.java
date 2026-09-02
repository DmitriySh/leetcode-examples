package ru.shishmakov.problems;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ??? - Minimum Product of Two Elements in an Array.
 * <p>
 * Given the array of integers nums, you will choose 2 different indices 'i' and 'j' of that array.
 * Returns the minimum product value of {@code nums[i] * nums[j]} .
 *
 * <p>
 * <pre>
 * Example 1:
 * nums    = [9, 4, 2, 5, 3]
 * indexes =  0  1  2  3  4
 *         i=2, j=4 => 2 * 3 = 6
 * result = 6
 *
 * Example 2:
 * nums    = [-1, 123, 40, 555, 28]
 * indexes =  0   1    2   3    4
 *         i=0, j=3 => -1 * 555 = -555
 * result = -555
 * </pre>
 */
public class MinimumProductTwoElementsArray implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_ARRAY = new int[]{9, 4, 2, 5, 3};
    public static final int DEFAULT_PRODUCT = 6;

    private final int[] array;
    private int product;

    public MinimumProductTwoElementsArray(int[] array) {
        this.array = array;
    }

    public int getProduct() {
        return product;
    }

    @Override
    public void run() {
        logger.info("Start finding the minimum product value in the array: {}", array);

        this.product = findMinProduct1(array);
        logger.info("Result. Minimum product value: {} from array", this.product);
    }

    public int findMinProduct1(int[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("Array length is not enough");
        }

        int min = Integer.MAX_VALUE;   // наименьший
        int minAfter = Integer.MAX_VALUE;   // второй наименьший
        int max = Integer.MIN_VALUE;   // наибольший
        int maxBefore = Integer.MIN_VALUE;   // второй наибольший

        boolean hasPositive = false;
        boolean hasNegative = false;
        for (int num : array) {
            if (num > 0) hasPositive = true;
            if (num < 0) hasNegative = true;

            // Обновляем два наименьших
            //[num = -5], 1, 3
            if (num < min) {
                minAfter = min;
                min = num;
            } else if (num < minAfter) {
                minAfter = num;
            }

            // 1, 3, [num = 5]
            // Обновляем два наибольших
            if (num > max) {
                maxBefore = max;
                max = num;
            } else if (num > maxBefore) {
                maxBefore = num;
            }
        }
        System.out.printf("Found min: %s, min after: %s, max before: %s, max: %s\n", min, minAfter, maxBefore, max);


        int result;

        // Определяем режим и выбираем стратегию
        if (hasPositive && hasNegative) {
            // Смешанный случай: минимальное произведение — у двух противоположных чисел (если отсортировать)
            System.out.printf("Has positive and negative values: %s, %s \n", min, max);
            result = min * max;
        } else if (hasPositive) {
            // Все положительные: минимальное произведение — у двух наименьших чисел
            System.out.printf("All positive values: %s, %s \n", min, minAfter);
            result = min * minAfter;
        } else if (hasNegative) {
            // Все отрицательные: минимальное произведение — у двух наибольших чисел
            System.out.printf("All negative values: %s, %s \n", max, maxBefore);
            result = max * maxBefore;
        } else {
            System.out.println("All values are zero");
            result = 0;
        }

        return result;
    }

    public int findMinProduct2(int[] array) {
        if (array.length < 2) {
            throw new RuntimeException("Array length is not enough");
        }

        // 4, 0, 2, 1, 3, -1  ->>  -1, 0, 1, 2, 3, 4
        System.out.println("Origin array = " + Arrays.toString(array));
        Arrays.sort(array);
        System.out.println("Sorted array = " + Arrays.toString(array));

        int first = array[0];
        int second = array[1];
        int last = array[array.length - 1];
        int previousLast = array[array.length - 2];

        // если все положительные
        int a = first * second;

        // если есть положительные и отрицательные
        int b = first * last;

        // если все отрицательные
        int c = previousLast * last;

        return Math.min(a, Math.min(b, c));
    }
}
