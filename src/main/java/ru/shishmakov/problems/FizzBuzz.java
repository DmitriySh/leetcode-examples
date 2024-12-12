package ru.shishmakov.problems;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 412 - Fizz Buzz.
 * <p>
 * Given an integer 'number', return a string array answer (1-indexed) where:
 * <ul>
 *     <li>answer[i] == "FizzBuzz" if i is divisible by 3 and 5</li>
 *     <li>answer[i] == "Fizz" if i is divisible by 3</li>
 *     <li>answer[i] == "Buzz" if i is divisible by 5</li>
 *     <li>answer[i] == i (as a string) for other values</li>
 * </ul>
 * <p>
 * Constraints: 1 <= n <= 10^4
 * <p>
 * <a href="https://leetcode.ca/2017-01-15-412-Fizz-Buzz/">FizzBuzz: problem solution</a><br/>
 * <a href="https://thecode.media/fizzbuzz/">A FizzBuzz game: checks your programming logic</a>
 */
public class FizzBuzz implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int DEFAULT_NUMBER = 15;

    private final int number;
    private List<String> resultList;

    public FizzBuzz(int number) {
        this.number = number;
    }

    public List<String> getResultList() {
        return resultList;
    }

    @Override
    public void run() {
        logger.info("Start FizzBuzz...");
        logger.info("Number={}", number);

        this.resultList = fizzBuzz(number);
        logger.info("Result string: {}", resultList);
    }

    private List<String> fizzBuzz(int number) {
        var result = new ArrayList<String>();

        var builder = new StringBuilder(4);
        for (int i = 1; i <= number; i++) {
            if (i % 3 == 0) {
                builder.append("Fizz");
            }
            if (i % 5 == 0) {
                builder.append("Buzz");
            }
            if (builder.isEmpty()) {
                builder.append(i);
            }

            result.add(builder.toString());
            builder.setLength(0);
        }

        return result;
    }
}
