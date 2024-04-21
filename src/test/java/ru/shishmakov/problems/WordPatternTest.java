package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class WordPatternTest {

    @ParameterizedTest(name = "pattern [{0}] text [{1}] = true")
    @CsvSource({"abba,dog cat cat dog", "abbc,dog cat cat fish", "a,b"})
    void stringMatchedIfFollowsPattern(String pattern, String text) {
        // given
        var wordPattern = new WordPattern(pattern, text);

        // when
        wordPattern.run();
        boolean matched = wordPattern.isMatch();

        // then
        assertThat(matched)
                .isTrue();
    }


    @ParameterizedTest(name = "pattern [{0}] text [{1}] = false")
    @CsvSource({"aaaa,dog cat cat dog", "abba,dog cat cat fish", "a,no way"})
    void stringNotMatchedIfNotFollowsPattern(String pattern, String text) {
        // given
        var wordPattern = new WordPattern(pattern, text);

        // when
        wordPattern.run();
        boolean matched = wordPattern.isMatch();

        // then
        assertThat(matched)
                .isFalse();
    }
}
