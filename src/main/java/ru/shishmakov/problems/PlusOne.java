package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

/**
 * 66 - Plus One.
 * <p/>
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
        int length = digits.length;

        int j = length;
        while (--j >= 0) {
            digits[j]++;
            digits[j] %= 10;
            if (digits[j] != 0) {
                return digits;
            }
        }

        digits = new int[length + 1];
        digits[0] = 1;
        return digits;
    }
}
