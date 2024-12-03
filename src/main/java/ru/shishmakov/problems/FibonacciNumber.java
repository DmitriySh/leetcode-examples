package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 509 - Fibonacci Number.
 * <p>
 * The Fibonacci numbers, commonly denoted 'F(n)' form a sequence, called the <u>Fibonacci sequence</u>,
 * such that each number is the sum of the two preceding ones, starting from '0' and '1'.
 * You need calculate Fibonacci number based on the index number.
 * <p>
 * Note: <br/>
 * F(0) = 0, F(1) = 1 <br/>
 * F(n) = F(n - 1) + F(n - 2), for n > 1 (0 <= n <= 30)
 *
 * <pre>
 * Example:
 *   fib(2) = fib(1) + fib(0) = 1 + 0 = 1
 *   fib(3) = fib(2) + fib(1) = (fib(1) + fib(0)) + fib(1) = 1 + 0 + 1 = 2
 *   fib(4) = fib(3) + fib(2) = (fib(2) + fib(1)) + (fib(1) + fib(0)) = fib(1) + fib(0) + fib(1) + fib(1) + fib(0) = 1 + 0 + 1 + 1 + 0 = 3
 *
 *   index numbers : 0  1  2  3  4  5  6  7  8  9  10 ...
 *   calculated
 *   fib. number   : 0  1  1  2  3  5  8  13 21 34 55 ...
 * </pre>
 * <a href="https://leetcode.ca/2017-04-22-509-Fibonacci-Number/">Fibonacci Number: problem solution</a>
 */
public class FibonacciNumber implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int DEFAULT_NUMBER = 7;

    private final int indexNumber;
    private int fibNumber;

    public FibonacciNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getFibNumber() {
        return fibNumber;
    }

    @Override
    public void run() {
        logger.info("Start with index = {}...", indexNumber);

        this.fibNumber = fib2(indexNumber);
        logger.info("Result. Calculated fibonacci number: {} by the index {}", fibNumber, indexNumber);
    }

    private int fib1(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    private int fib2(int n) {
        int a = 0, b = 1;
        while (n > 0) {
            int c = a + b; // 1=0+1 ; 2=1+1 ; 3=2+1
            logger.info("fib({}) = fib({}) + fib({})", c, a, b);
            a = b;
            b = c;
            n--;
        }
        return a;
    }
}
