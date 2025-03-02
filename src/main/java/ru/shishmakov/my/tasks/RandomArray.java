package ru.shishmakov.my.tasks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArray implements Runnable {
    public static final int LENGTH_DEFAULT = 10;

    private final int length;

    public RandomArray(int length) {
        this.length = length;
    }

    @Override
    public void run() {
        Integer[] array = randomArray(length);
        System.out.println("Array with positive random values: " + Arrays.asList(array));
    }

    private static Integer[] randomArray(int count) {
        var random = ThreadLocalRandom.current();
        var set = new HashSet<Integer>();
        while (count > 0) {
            int value = random.nextInt(0, Integer.MAX_VALUE);
            if (set.add(value)) {
                count--;
            }
        }
        return set.toArray(Integer[]::new);
    }

    public static void main(String[] args) {
        new RandomArray(LENGTH_DEFAULT).run();
    }
}
