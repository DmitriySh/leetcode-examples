package ru.shishmakov.problems;

/**
 * Given an integer 'n', return 'true' if it is a power of two and 'false' otherwise.<br/>
 * Explanation: if binary notation of number has single value '1' then source number is a power of two (n == 2^x)
 * <pre>
 * Examples:
 *      0000 0000 is 8 bit (1 byte)
 * ------------------------
 *      1st case
 *      0000 0001 = 2^0 = 1
 *      0000 0010 = 2^1 = 2
 *      0000 0100 = 2^2 = 4
 *      0000 1000 = 2^3 = 8
 *      0001 0000 = 2^4 = 16
 * ------------------------
 *     2nd case
 *     digit 8 = 0000 1000
 *     digit 1 = 0000 0001
 *     8 - 1 = 7 = 0000 0111
 *     (0000 1000) AND (0000 0111) = 0000 0000
 *     if binary multiplication has result '0000 0000' = number is a power of two
 *
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-07-18-231-Power-of-Two/">Power of Two: problem solution</a>
 */
public class PowerOfTwo implements Runnable {
    public static final int DEFAULT_NUMBER = 72;

    private final int number;
    private boolean powerOfTwo;

    public PowerOfTwo(int number) {
        this.number = number;
    }

    public boolean isPowerOfTwo() {
        return powerOfTwo;
    }

    @Override
    public void run() {
        System.out.println("Start validating the number...");
        System.out.println("Number: " + number + System.lineSeparator());

        this.powerOfTwo = validateNumber2(number);
        System.out.println("Result. The number is: " + (this.powerOfTwo ? "power of two" : "not power of two"));
    }

    private boolean validateNumber1(int number) {
        if (number <= 0) {
            return false;
        }

        while (number % 2 == 0) {
            number /= 2;
            System.out.println("n/2 = " + number);
        }
        return number == 1;
    }

    private boolean validateNumber2(int number) {
        if (number <= 0) {
            return false;
        }
        return (number & (number - 1)) == 0;
    }
}
