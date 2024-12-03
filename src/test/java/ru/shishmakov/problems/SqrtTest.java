package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SqrtTest {

    @ParameterizedTest(name = "value=[{0}], squareRoot=[{1}]")
    @CsvSource(value = {"0,0", "1,1", "2,1", "3,1", "9,3", "10,3", "11,3"})
    void calculateSquareRoot(int source, int expectedResult) {
        // given
        Sqrt sqrt = new Sqrt(source);

        // when
        sqrt.run();
        int result = sqrt.getSquareRoot();

        // then
        assertThat(result)
                .isEqualTo(expectedResult);

    }

    @Test
    void calculateSquareRootWithMaxValue() {
        // given
        int expectedResult = 46_340;
        int maxValue = Integer.MAX_VALUE;

        Sqrt sqrt = new Sqrt(maxValue);

        // when
        sqrt.run();
        int result = sqrt.getSquareRoot();

        // then
        assertThat(result)
                .isEqualTo(expectedResult);

    }

    @Test
    void calculateSquareRootWithMinValue() {
        // given
        int expectedResult = 0;
        Sqrt sqrt = new Sqrt(Integer.MIN_VALUE);

        // when
        sqrt.run();
        int result = sqrt.getSquareRoot();

        // then
        assertThat(result)
                .isEqualTo(expectedResult);

    }
}
