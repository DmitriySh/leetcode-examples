package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Given an unsigned integer 'n', return the number of '1' bits it has in binary representation
 * <pre>
 * Explanation: shift the number by the desired quantity of bits to the right, multiply to the 1 with AND operator and sum up the values
 * Examples:
 *      0000 0000 is 8 bit (1 byte)
 * ------------------------
 *      0000 0000 = 0 -> 0 bit
 *      0000 0001 = 1 -> 1 bit
 *      0000 0011 = 3 -> 2 bits
 *      0000 0111 = 7 -> 3 bits
 * ------------------------
 *      14 >> 0 = 14
 *      (0000 1110) & (0000 0001) = 0
 *      14 >> 1 = 7
 *      (0000 0111) & (0000 0001) = 1
 *      14 >> 2 = 3
 *      (0000 0011) & (0000 0001) = 1
 *      14 >> 3 = 1
 *      (0000 0001) & (0000 0001) = 1
 *      14 >> 4 = 0
 *      (0000 0000) & (0000 0001) = 0
 *      Sum = 3 bits
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-06-08-191-Number-of-1-Bits/">Number of 1 Bits: problem solution</a>
 */
public class NumberOfOneBits implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int DEFAULT_NUMBER = 712;

    private final int number;
    private int oneBitsCount;

    public NumberOfOneBits(int number) {
        this.number = number;
    }

    public int getOneBitsCount() {
        return oneBitsCount;
    }

    @Override
    public void run() {
        logger.info("Start counting the number of '1' bits...");
        logger.info("Number: {}", number);

        this.oneBitsCount = countBits();
        logger.info("Result. It was counted {} of '1' bits", this.oneBitsCount);
    }

    private int countBits() {
        int count = 0;
        List<Integer> shifts = new ArrayList<>(32);

        for (int i = 0; i < 32; i++) {
            int temp = this.number;
            count += (temp = (temp >> i)) & 1;
            shifts.add(temp);
        }

        shifts.forEach(n -> logger.info(
                MessageFormat.format("Shifted number = {0}; bits = {1}", n, Integer.toBinaryString(n))
        ));
        return count;
    }
}
