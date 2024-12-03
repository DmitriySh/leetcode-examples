package ru.shishmakov.problems;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoveZeroesTest {

    @Test
    void shouldMoveZeroRightWithDefaultNumsArray() {
        // given
        int[] expectedNums = {7, 1, 3, 12, 0, 0, 0, 0};
        var moveZeroes = new MoveZeroes(MoveZeroes.DEFAULT_NUMS);

        // when
        moveZeroes.run();
        int[] originNums = moveZeroes.getOriginNums();
        int[] resultNums = moveZeroes.getNums();

        // then
        Assertions.assertThat(originNums)
                .isNotEqualTo(resultNums);
        Assertions.assertThat(resultNums)
                .isEqualTo(expectedNums);
    }

    @ParameterizedTest(name = "source=[{0}], target=[{1}]")
    @CsvSource(value = {"0,0", "10,10", "002,200", "0030,3000", "040050,450000"})
    void shouldMoveZeroRight(String source, String expected) {
        // given
        int[] sourceNums = source.chars().mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
        int[] expectedNums = expected.chars().mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
        var moveZeroes = new MoveZeroes(sourceNums);

        // when
        moveZeroes.run();
        int[] resultNums = moveZeroes.getNums();

        // then
        Assertions.assertThat(resultNums)
                .isEqualTo(expectedNums);
    }
}
