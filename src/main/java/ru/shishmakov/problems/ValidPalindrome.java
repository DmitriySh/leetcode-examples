package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 125 - Valid Palindrome.
 * <p>
 * Given a string `text`, return `true` if it is a palindrome, or `false` otherwise.
 * An input string is palindrome if:
 * <ul>
 *     <li>after converting all uppercase letters into lowercase letters, it reads the same forward and backward;</li>
 *     <li>alphanumeric characters include letters and numbers and removing all non-alphanumeric characters.</li>
 * </ul>
 *
 * <pre>
 * Example:
 *   text   = "No lemon, no melon!"
 *   valid  = true
 *
 *   text   = "A23"
 *   valid  = false
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-04-03-125-Valid-Palindrome/">Valid palindrome: problem solution</a>
 */
public class ValidPalindrome implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String DEFAULT_TEXT = "A man, a plan, a canal: Panama";

    private final String text;
    private boolean valid;

    public ValidPalindrome(String text) {
        this.text = text;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public void run() {
        logger.info("Start validating text...");
        logger.info("Palindrome: {}", text);

        this.valid = isPalindrome(text);
        logger.info("Result. Text is: {}", (this.valid ? "palindrome" : "not a palindrome"));
    }

    private boolean isPalindrome(String value) {
        int leftIndex = 0;
        int rightIndex = value.length() - 1;

        while (leftIndex < rightIndex) {
            char leftChar = Character.toLowerCase(value.charAt(leftIndex));
            char rightChar = Character.toLowerCase(value.charAt(rightIndex));

            if (!Character.isLetterOrDigit(leftChar)) {
                leftIndex++;
                continue; // skip char
            }
            if (!Character.isLetterOrDigit(rightChar)) {
                rightIndex--;
                continue; // skip char
            }
            if (leftChar == rightChar) {
                leftIndex++;
                rightIndex--; // go next chars
            } else {
                return false; // chars are not equal
            }
        }
        return true;
    }
}
