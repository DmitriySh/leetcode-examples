package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

/**
 * 443 - String Compression.
 * <p/>
 * Given an array of characters 'chars', compress it using the following algorithm:
 * <ul>
 *   <li>begin with an empty string 'str';</li>
 *   <li>for each group of consecutive repeating characters in 'chars':</li>
 *   <ul>
 *      <li>if the group's length is 1, append the character to 'str';</li>
 *      <li>otherwise, append the character followed by the group's length;</li>
 *   </ul>
 *   <li>group lengths >= 10 will be split into multiple characters in 'chars'</li>
 *   <li>compressed string 'str' should be stored in the input character array 'chars'</li>
 *   <li>return the new payload length of the input character array 'chars'</li>
 * </ul>
 *
 * <a href="https://leetcode.ca/2017-02-15-443-String-Compression/">String Compression: problem solution</a>
 */
public class StringCompression implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final char[] DEFAULT_CHARS = new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'c'};

    private final char[] originChars;
    private final char[] chars;
    private int newLength;

    public StringCompression(char[] chars) {
        this.originChars = chars.clone();
        this.chars = chars;
    }

    public char[] getOriginChars() {
        return originChars;
    }

    public char[] getChars() {
        return chars;
    }

    public int getNewLength() {
        return newLength;
    }

    @Override
    public void run() {
        logger.info("Start string compression...");
        logger.info("Array of characters before: {}", Arrays.toString(chars));

        this.newLength = compress(chars);
        logger.info("Array of characters after: {}, new length: {}", Arrays.toString(chars), this.newLength);
    }

    public int compress(char[] chars) {
        int first = 0, second = 1;
        char prevChar = 0;
        int counter = 0;

        while (second < chars.length) {
            if (prevChar == 0) {
                prevChar = chars[first];
                counter = 1;
            }

            char nextChar = chars[second];
            if (prevChar == nextChar) {
                counter++;
            } else {
                // need the number?
                if (counter > 1) {
                    if (counter < 10) {
                        chars[++first] = Character.forDigit(counter, 10);
                    } else {
                        String intStr = Integer.toString(counter);
                        for (int i = 0; i < intStr.length(); i++) {
                            chars[++first] = intStr.charAt(i);
                        }
                    }
                }
                chars[++first] = nextChar;
                prevChar = nextChar;
                counter = 1;
            }
            second++;
        }

        if (counter > 1) {
            if (counter < 10) {
                chars[++first] = Character.forDigit(counter, 10);
            } else {
                String intStr = Integer.toString(counter);
                for (int i = 0; i < intStr.length(); i++) {
                    chars[++first] = intStr.charAt(i);
                }
            }
        }
        return first + 1; // new length
    }

    public static void main(String[] args) {
        var compression = new StringCompression(DEFAULT_CHARS);
        compression.run();
    }
}
