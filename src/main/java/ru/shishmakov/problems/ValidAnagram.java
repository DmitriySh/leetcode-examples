package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 242 - Valid Anagram.
 * <p/>
 * Given two strings `source` and `target`, return `true` if `target` is an anagram of `source`, and `false` otherwise.
 * `Source` and `target` strings consist of lowercase English letters.
 * <p/>
 * Note: an <b>Anagram</b> is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * <pre>
 * Example:
 *   source = "anagram"
 *   target = "nagaram"
 *   valid  = true
 *
 *   source = "rat"
 *   target = "car"
 *   valid  = false
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-07-29-242-Valid-Anagram/">Valid Anagram: problem solution</a>
 */
public class ValidAnagram implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final int ENGLISH_ALPHABET_COUNT = 26;
    public static final String DEFAULT_SOURCE_TEXT = "listen";
    public static final String DEFAULT_TARGET_TEXT = "silent";

    private final String sourceText;
    private final String targetText;
    private boolean valid;

    public ValidAnagram(String sourceText, String targetText) {
        this.sourceText = sourceText;
        this.targetText = targetText;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public void run() {
        logger.info("Start validating anagram...");
        logger.info("Anagram: {} -> {}", sourceText, targetText);

        this.valid = isAnagram(sourceText, targetText);
        logger.info("Result. Text is: {}", (this.valid ? "anagram" : "not an anagram"));
    }

    private boolean isAnagram(String sourceText, String targetText) {
        if (sourceText.length() != targetText.length()) {
            return false;
        }

        // A - 65 ASCII; Z - 90  ASCII
        // a - 97 ASCII; z - 122 ASCII
        int[] alphabetCharsCount = new int[ENGLISH_ALPHABET_COUNT];
        for (int i = 0; i < sourceText.length(); i++) {
            alphabetCharsCount[sourceText.charAt(i) - 'a']++;
            alphabetCharsCount[targetText.charAt(i) - 'a']--;
        }
        for (int count : alphabetCharsCount) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
