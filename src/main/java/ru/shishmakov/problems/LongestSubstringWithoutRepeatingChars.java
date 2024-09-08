package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;

/**
 * 3 - Longest Substring Without Repeating Characters.
 * <p/>
 * Given a string 'str', find the length of the <u>longest substring</u> without repeating characters.
 * A substring contains a contiguous non-empty sequence of characters within a string.
 * <p>
 * Note: 'str' consists of english letters, digits, symbols and spaces. Source 'str' length: [0 .. 5*104]
 * <pre>
 * Example:
 *   str = abcabcbb
 *   substring length = 3 (abc)
 *
 *   str = bbbbb
 *   substring length = 1 (b)
 *
 *   str = pwwkew
 *   substring length = 3 (wke or kew)
 * </pre>
 * <a href="https://redquark.org/leetcode/0003-longest-substring-without-repeating-characters/">Longest Substring Without Repeating Characters: problem solution</a>
 */
public class LongestSubstringWithoutRepeatingChars implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String DEFAULT_STRING = "pwwkew";

    private final String str;
    private int length;

    public int getLength() {
        return length;
    }

    public LongestSubstringWithoutRepeatingChars(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        logger.info("Start find longest substring length without repeating characters ...");
        logger.info("Source string: {}", str);

        this.length = getLongestSubstringLength(str);
        logger.info("Result. Longest substring length: {}", this.length);
    }

    private int getLongestSubstringLength(String value) {
        Set<Character> substringChars = new HashSet<>();
        int left = 0, right = 0;
        int maxLength = 0;

        // move the substring window: [left .. right]
        while (right < value.length()) {
            char charRightPosition = value.charAt(right);
            if (substringChars.add(charRightPosition)) {
                right++;
                int substringLength = right - left;
                maxLength = Math.max(maxLength, substringLength);
                logger.info("Substring: {}, length: {}", value.substring(left, right), substringLength);
            } else {
                char charLeftPosition = value.charAt(left);
                substringChars.remove(charLeftPosition);
                left++;
            }
        }

        return maxLength;
    }
}
