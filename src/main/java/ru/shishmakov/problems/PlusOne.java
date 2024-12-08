package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

/**
 * 66 - Plus One.
 * <p>
 * You are given a large integer represented as an integer array 'digits'. The 'digits' are ordered in left-to-right order.
 * The large integer does not contain any leading 0's. <b>Increment</b> the large integer <b>by one</b> and return the resulting array of digits.
 *
 * <pre>
 * Example:
 *   origin digits   : [9, 9] + 1
 *   expected array  : [1, 0, 0]
 *
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-02-04-66-Plus-One/">Plus One: problem solution</a>
 */
public class PlusOne implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_DIGITS = new int[]{9, 9};

    private int[] digits;

    public PlusOne(int[] digits) {
        this.digits = digits;
    }

    public int[] getDigitsArray() {
        return digits;
    }

    @Override
    public void run() {
        logger.info("Start plus one to a large integer...");
        logger.info("Integer array: {}", Arrays.toString(digits));

        this.digits = plusOne(digits);
        logger.info("Result. Integer array: {}", Arrays.toString(digits));
    }

    private int[] plusOne(int[] digits) {
        int index = digits.length - 1;

        while (index >= 0) {
            digits[index]++;
            digits[index] %= 10;
            if (digits[index] != 0) {
                return digits;
            }
            index--;
        }

        // [9,9] -> [1,0,0]
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
