package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

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
