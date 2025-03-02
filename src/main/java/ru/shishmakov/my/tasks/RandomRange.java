package ru.shishmakov.my.tasks;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRange implements Runnable {
    public static final int MIN_DEFAULT = 41;
    public static final int MAX_DEFAULT = 54;
    public static final int COUNTER_DEFAULT = 10;

    private final int min;
    private final int max;
    private final int counter;

    public RandomRange(int min, int max) {
        this(min, max, 10);
    }

    public RandomRange(int min, int max, int counter) {
        this.min = min;
        this.max = max;
        this.counter = counter;
    }

    @Override
    public void run() {
        Random random = ThreadLocalRandom.current();
        int counter = this.counter;
        while (counter-- > 0) {
            int deltaPlusOne = (max - min) + 1;
            int value = (int) (random.nextDouble() * deltaPlusOne);
            int truncatedRandomValue = min + value; // [0,0 .. 1,0) => [0 .. delta]
            System.out.println("Random value [" + min + " .. " + max + "] = " + truncatedRandomValue);
        }
    }

    public static void main(String[] args) {
        new RandomRange(MIN_DEFAULT, MAX_DEFAULT, COUNTER_DEFAULT).run();
    }
}
