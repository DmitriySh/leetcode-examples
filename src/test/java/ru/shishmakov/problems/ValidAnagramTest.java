package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.shishmakov.problems.ValidAnagram.DEFAULT_SOURCE_TEXT;
import static ru.shishmakov.problems.ValidAnagram.DEFAULT_TARGET_TEXT;

public class ValidAnagramTest {

    @Test
    void defaultStringsValidAnagram() {
        // given
        var anagram = new ValidAnagram(DEFAULT_SOURCE_TEXT, DEFAULT_TARGET_TEXT);

        // when
        anagram.run();
        boolean valid = anagram.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @ParameterizedTest(name = "source=[{0}], target=[{1}] anagram")
    @CsvSource(value = {"abba,baba", "rat,tar", "anagram,nagaram", "evil,vile", "restful,fluster"})
    void stringValidAnagram(String source, String target) {
        // given
        var anagram = new ValidAnagram(source, target);

        // when
        anagram.run();
        boolean valid = anagram.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @ParameterizedTest(name = "source=[{0}], target=[{1}] not anagram")
    @CsvSource(value = {"abba,baa", "rat,car", "wg,wagon", "abc,not", "ffv,fff"})
    void stringNotValidAnagram(String source, String target) {
        // given
        var anagram = new ValidAnagram(source, target);

        // when
        anagram.run();
        boolean valid = anagram.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }
}
