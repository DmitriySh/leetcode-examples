package ru.shishmakov.my.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class ArrayMoveZeroNumber implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_ARRAY_NUMBERS = new int[]{1, 0, 0, 8, 9};

    private final int[] arrayNumbers;

    public ArrayMoveZeroNumber(int[] arrayNumbers) {
        this.arrayNumbers = arrayNumbers;
    }

    @Override
    public void run() {
        logger.info("Start moving zeros in array...");
        logger.info("Source array: {}", Arrays.toString(arrayNumbers));

        moveZeros(arrayNumbers);
        logger.info("Target array: {}", Arrays.toString(arrayNumbers));
    }

    private void moveZeros(int[] arrayNumbers) {
        for (int i = 0; i < arrayNumbers.length - 1; i++) {

            for (int j = 0; j < arrayNumbers.length - 1 - i; j++) {
                if (arrayNumbers[j] == 0) {
                    int temp = arrayNumbers[j];
                    arrayNumbers[j] = arrayNumbers[j + 1];
                    arrayNumbers[j + 1] = temp;
                }
            }

        }
    }

    public static void main(String[] args) {
        new ArrayMoveZeroNumber(ArrayMoveZeroNumber.DEFAULT_ARRAY_NUMBERS).run();
    }
}
