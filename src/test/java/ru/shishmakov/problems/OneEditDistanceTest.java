package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class OneEditDistanceTest {

    @ParameterizedTest(name = "first [{0}], second [{1}] = true")
    @CsvSource(value = {"a,b", "ab,b", "ab,cb", "abcd,abxcd"})
    void stringsTrueIfOneEdit(String first, String second) {
        // given
        var oneEditDistance = new OneEditDistance(first, second);

        // when
        oneEditDistance.run();
        boolean hasOneEdit = oneEditDistance.isOneEdit();

        // then
        assertThat(hasOneEdit)
                .isTrue();
    }

    @ParameterizedTest(name = "first [{0}], second [{1}] = false")
    @CsvSource(value = {"ab,ba", "ab,abcd"})
    void stringsFalseIfNoOneEdit(String first, String second) {
        // given
        var oneEditDistance = new OneEditDistance(first, second);

        // when
        oneEditDistance.run();
        boolean hasOneEdit = oneEditDistance.isOneEdit();

        // then
        assertThat(hasOneEdit)
                .isFalse();
    }
}
