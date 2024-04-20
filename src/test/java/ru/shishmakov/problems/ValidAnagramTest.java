package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.shishmakov.problems.ValidAnagram.DEFAULT_SOURCE_TEXT;
import static ru.shishmakov.problems.ValidAnagram.DEFAULT_TARGET_TEXT;

public class ValidAnagramTest {

    @Test
    void stringValidIfTextIsAnagram() {
        // given
        var anagram = new ValidAnagram(DEFAULT_SOURCE_TEXT, DEFAULT_TARGET_TEXT);

        // when
        anagram.run();
        boolean valid = anagram.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @Test
    void stringNotValidIfTextIsNotAnagram() {
        // given
        var anagram = new ValidAnagram("abba", "baa");

        // when
        anagram.run();
        boolean valid = anagram.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }
}
