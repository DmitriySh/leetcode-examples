package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 161 - One Edit Distance.
 * <p/>
 * Given two strings 'first' and 'second', determine if they are both one edit distance apart.
 * <p/>
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
 *   " "   |  ""     = true
 *   ""    |  ""     = false
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

        // abcd
        // abxcd
        int minLength = Math.min(fLength, sLength);
        int position = 0;
        while (position < minLength) {
            char fChar = first.charAt(position);
            char sChar = second.charAt(position);
            if (fChar != sChar) {
                logger.info("'{}' and '{}' = not ok", fChar, sChar);
                if (fLength == sLength) {
                    // replace 1 char?
                    return equalStr(first.substring(position + 1), second.substring(position + 1));
                } else if (fLength > sLength) {
                    // delete 1 char?
                    return equalStr(first.substring(position + 1), second.substring(position));
                } else {
                    // insert 1 char?
                    return equalStr(first.substring(position), second.substring(position + 1));
                }
            } else {
                logger.info("'{}' and '{}' = ok", fChar, sChar);
            }
            position++;
        }
        return Math.abs(fLength - sLength) == 1;
    }

    private boolean equalStr(String a, String b) {
        logger.info("End parts: {} and {}", a, b);
        return a.equals(b);
    }
}
