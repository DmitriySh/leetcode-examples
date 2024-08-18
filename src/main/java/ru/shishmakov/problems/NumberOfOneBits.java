package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 191 - Number of 1 Bits.
 * <p/>
 * Given an unsigned integer 'n', return the number of '1' bits it has in binary representation
 * <pre>
 * Explanation: shift the number by the desired quantity of bits to the right, multiply to the 1 with AND operator and sum up the values
 * Examples:
 *      0000 0000 is 8 bit (1 byte)
 * ------------------------
 *      0000 0000 = 0  -> 0 bit
 *      0000 0001 = 1  -> 1 bit
 *      0000 0011 = 3  -> 2 bits
 *      0000 0111 = 7  -> 3 bits
 *      0000 1111 = 15 -> 4 bits
 * ------------------------
 *      14 >> 0 = 14
 *      14 >> 1 = 7
 *      14 >> 2 = 3
 *      14 >> 3 = 1
 *      14 >> 4 = 0
 *
 *      14             1
 *      (0000 1110) & (0000 0001) = 0
 *      7              1
 *      (0000 0111) & (0000 0001) = 1
 *      3              1
 *      (0000 0011) & (0000 0001) = 1
 *      1              1
 *      (0000 0001) & (0000 0001) = 1
 *      0              0
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

        var bitsResult = countBits(number);
        this.oneBitsCount = bitsResult.count;

        bitsResult.shifts.forEach(n -> logger.info(
                MessageFormat.format("Shifted number = {0}; bits = {1}", n, Integer.toBinaryString(n))
        ));
        logger.info("Result. It was counted {} of '1' bits", this.oneBitsCount);
    }

    private BitsResult countBits(int number) {
        int count = 0;

        var shifts = new ArrayList<Integer>(32);
        for (int i = 0; i < 32; i++) {
            int temp = number;
            count += (temp = (temp >> i)) & 1;
            shifts.add(temp);
        }
        return new BitsResult(count, shifts);
    }

    private record BitsResult(int count, List<Integer> shifts) {
    }
}
