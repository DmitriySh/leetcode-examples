package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 69 - Sqrt(x).
 * <p>
 * Given a non-negative integer 'x', return the <u>square root</u> of 'x' rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 * <p>
 * Note: you mustn't use any built-in exponent function or operator.
 *
 * <pre>
 * Example:
 *   sqrt(10) = 3
 *   sqrt(9)  = 3
 *   sqrt(8)  = 2
 *   sqrt(7)  = 2
 *   ...
 *   sqrt(3)  = 1
 *   sqrt(2)  = 1
 *   sqrt(1)  = 1
 *   sqrt(0)  = 0
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-02-07-69-Sqrt(x)/">69 - Sqrt(x): problem solution</a>
 */
public class Sqrt implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int DEFAULT_VALUE = 81;

    private final int number;
    private int squareRoot;

    public Sqrt(int number) {
        this.number = number;
    }

    public int getSquareRoot() {
        return squareRoot;
    }

    @Override
    public void run() {
        logger.info("Start find square root...");
        logger.info("Source value = {}", number);

        squareRoot = mySqrt(number);
        logger.info("sqrt(value) = {}", squareRoot);
    }

    private int mySqrt(int sourceSquare) {
        int left = 0;
        int right = sourceSquare;

        while (left < right) {
            int mid = (left + right + 1) / 2; // 4 >>> 1 = 2
            if (mid > sourceSquare / mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
