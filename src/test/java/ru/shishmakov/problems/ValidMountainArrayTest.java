package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidMountainArrayTest {

    @Test
    void defaultMountainArrayShouldBeValid() {
        // given
        var validMountainArray = new ValidMountainArray(ValidMountainArray.DEFAULT_ARRAY);

        // when
        validMountainArray.run();
        boolean valid = validMountainArray.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @ParameterizedTest(name = "array=[{0}] is valid")
    @ValueSource(strings = {"0321", "123454321", "151", "0246531"})
    void mountainArrayShouldBeValid(String source) {
        // given
        int[] array = source.chars().mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
        var validMountainArray = new ValidMountainArray(array);

        // when
        validMountainArray.run();
        boolean valid = validMountainArray.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @ParameterizedTest(name = "array=[{0}] is not valid")
    @ValueSource(strings = {"", "1", "03", "12345", "157", "024246531", "01123410"})
    void mountainArrayShouldNotBeValid(String source) {
        // given
        int[] array = source.chars().mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
        var validMountainArray = new ValidMountainArray(array);

        // when
        validMountainArray.run();
        boolean valid = validMountainArray.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }
}
