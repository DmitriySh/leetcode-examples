package ru.shishmakov.problems;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 161 - One Edit Distance.
 * <p>
 * Given two strings 'first' and 'second', determine if they are both one edit distance apart.
 * <p>
 * There are 3 possibilities to satisfy one edit distance apart:
 * <ul>
 *     <li>insert one character into 'first' to get 'second';</li>
 *     <li>delete one character from 'first' to get 'second';</li>
 *     <li>replace one character of 'first' to get 'second'.</li>
 * </ul>
 *
 * <pre>
 * Example:
 *   "a"   |  "b"    = true
 *   "ab"  |  "b"    = true
 *   "ab"  | "cb"    = true
 *   "abcd"|"abxcd"  = true
 *   ""    |  "a"    = true
 *   ""    |  " "    = true
 *   ""    |  ""     = false
 *   "ab"  | ""      = false
 *   "ab"  | "ab"    = false
 *   "ab"  | "ba"    = false
 *   "ab"  | "abcd"  = false
 *
 * Explanation: you need only one character of differences and other parts should be equal
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-05-09-161-One-Edit-Distance/">One Edit Distance: problem solution</a>
 */
public class OneEditDistance implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String FIRST = "abcd";
    public static final String SECOND = "abxcd";

    private final String first;
    private final String second;
    private boolean isOneEdit;

    public OneEditDistance(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public boolean isOneEdit() {
        return isOneEdit;
    }

    @Override
    public void run() {
        logger.info("Start matching two strings to one edit...");
        logger.info("First: {}, second: {}", first, second);

        this.isOneEdit = isOneEditDistance(first, second);
        logger.info("Result. Two strings has one edit distance: {}", this.isOneEdit);
    }

    private boolean isOneEditDistance(String first, String second) {
        int fLength = first.length();
        int sLength = second.length();

        // [2..N] symbols are different
        if (Math.abs(fLength - sLength) > 1) {
            return false;
        }

        int minLength = Math.min(fLength, sLength);
        int index = 0;
        while (index < minLength) {
            char firstChar = first.charAt(index);
            char secondChar = second.charAt(index);

            if (firstChar == secondChar) {
                logger.info("first['{}'] == second['{}']", firstChar, secondChar);
                index++;
                continue;
            }

            logger.info("first['{}'] != second['{}']", firstChar, secondChar);
            // replace 1 char in first and second?
            if (fLength == sLength) {
                // ab[f]cd => cd
                // ab[x]cd => cd
                return equalString(first.substring(index + 1), second.substring(index + 1));
            }
            // delete 1 char in first?
            else if (fLength > sLength) {
                // ab[f]cd => cd
                // abcd    => cd
                return equalString(first.substring(index + 1), second.substring(index));
            }
            // insert 1 char first?
            else {
                // abcd  => cd
                // ab[x]cd => cd
                return equalString(first.substring(index), second.substring(index + 1));
            }
        }

        // 'abcd' and 'abcd' = false (no one edit)
        // 'abcd' and 'abc' = true   (has one edit)
        return Math.abs(fLength - sLength) == 1;
    }

    private boolean equalString(String first, String second) {
        logger.info("End parts: {} and {}", first, second);
        return first.equals(second);
    }
}
