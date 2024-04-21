package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Objects;

/**
 * 290 - Word Pattern.
 * <p/>
 *
 * <a href="https://leetcode.ca/2016-09-15-290-Word-Pattern/">Word Pattern: problem solution</a>
 */
public class WordPattern implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String DEFAULT_PATTERN = "abba";
    public static final String DEFAULT_TEXT = "dog cat cat dog";

    private final String pattern;
    private final String text;
    private boolean match;

    public WordPattern(String pattern, String text) {
        this.pattern = pattern;
        this.text = text;
    }

    public boolean isMatch() {
        return match;
    }

    @Override
    public void run() {
        logger.info("Start search pattern in a text...");
        logger.info("Pattern: {}, text: {}", pattern, text);

        this.match = hasWordPattern(pattern, text);
        logger.info("Result. Pattern is: {}", (this.match ? "match" : "no match"));
    }

    public boolean hasWordPattern(String pattern, String text) {
        String[] words = text.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        var charToWord = new HashMap<Character, String>();
        var wordToChar = new HashMap<String, Character>();
        for (int i = 0; i < pattern.length(); i++) {
            char pChar = pattern.charAt(i);
            String word = words[i];

            charToWord.putIfAbsent(pChar, word);
            wordToChar.putIfAbsent(word, pChar);

            if (!Objects.equals(word, charToWord.get(pChar)) ||
                    !Objects.equals(pChar, wordToChar.get(word))) {
                return false;
            }
        }
        return true;
    }
}
