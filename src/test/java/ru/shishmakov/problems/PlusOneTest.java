package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlusOneTest {

    @ParameterizedTest(name = "source [{0}] target[{1}]")
    @CsvSource({"99,100", "123,124", "239,240"})
    void shouldIncrementIntegerArray(String source, String target) {
        // given
        int[] sourceDigits = source.chars().mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
        int[] targetDigits = target.chars().mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
        var plusOne = new PlusOne(sourceDigits);

        // when
        plusOne.run();
        int[] array = plusOne.getDigitsArray();

        // then
        assertThat(array)
                .isEqualTo(targetDigits);
    }
}
