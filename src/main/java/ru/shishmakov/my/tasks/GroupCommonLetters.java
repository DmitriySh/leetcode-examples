package ru.shishmakov.my.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupCommonLetters implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final List<String> DEFAULT_WORDS = List.of("eat", "tea", "tan", "ate", "nat", "bat");

    private final List<String> arrayWords;
    private List<List<String>> groupedWords;

    public GroupCommonLetters(List<String> arrayWords) {
        this.arrayWords = arrayWords;
    }

    public List<List<String>> getGroupedWords() {
        return groupedWords;
    }

    @Override
    public void run() {
        logger.info("Start grouping words...");
        logger.info("Source list of words: {}", arrayWords);

        List<List<String>> groupedWords = groupWords(arrayWords);
        logger.info("Grouped list of words: {}", groupedWords);
    }

    private List<List<String>> groupWords(List<String> arrayWords) {
        var wordMap = new HashMap<String, List<String>>();
        for (String word : arrayWords) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = String.valueOf(chars);

            wordMap.merge(sortedWord, new ArrayList<>(List.of(word)), (old, next) -> {
                old.addAll(next);
                return old;
            });
        }

        return wordMap.values()
                .stream()
                .toList();
    }

    public static void main(String[] args) {
        new GroupCommonLetters(GroupCommonLetters.DEFAULT_WORDS).run();
    }
}
