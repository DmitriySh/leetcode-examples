package ru.shishmakov.problems;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;


public class MaximumProductTwoElementsArrayTest {

    @Test
    void shouldReturnMaximumProductValue_ifArrayDefault() {
        // given
        var maximumProductTwoElementsArray = new MaximumProductTwoElementsArray(MaximumProductTwoElementsArray.DEFAULT_ARRAY);

        // when
        maximumProductTwoElementsArray.run();
        int productValue = maximumProductTwoElementsArray.getProduct();

        // then
        assertThat(productValue)
                .isEqualTo(MaximumProductTwoElementsArray.DEFAULT_PRODUCT);
    }

    @ParameterizedTest(name = "array={0}, product={1}")
    @MethodSource("validArrays")
    void shouldReturnMaximumProductValue(int[] array, int expectedProduct) {
        // given
        var maximumProductTwoElementsArray = new MaximumProductTwoElementsArray(array);

        // when
        maximumProductTwoElementsArray.run();
        int product = maximumProductTwoElementsArray.getProduct();

        // then
        assertThat(product)
                .isEqualTo(expectedProduct);
    }

    @ParameterizedTest(name = "array={0}")
    @MethodSource("notValidArrays")
    void shouldThrowException_ifArrayIllegal(int[] array) {
        // given
        var maximumProductTwoElementsArray = new MaximumProductTwoElementsArray(array);

        // expect
        Assertions.assertThatThrownBy(maximumProductTwoElementsArray::run)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Array length is not enough");
    }

    public static Stream<Arguments> validArrays() {
        return Stream.of(
                Arguments.of(new int[]{9, 4, 2, 5, 3}, 32),
                Arguments.of(new int[]{1, 5, 4, 5}, 16),
                Arguments.of(new int[]{3, 7}, 12),
                Arguments.of(new int[]{1, 5}, 0)
        );
    }

    public static Stream<int[]> notValidArrays() {
        return Stream.of(
                new int[]{},
                new int[]{2}
        );
    }
}
