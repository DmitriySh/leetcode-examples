package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 242 - Valid Anagram.
 * <p/>
 *
 <a href="https://leetcode.ca/2016-07-29-242-Valid-Anagram/">Valid Anagram: problem solution</a>
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
