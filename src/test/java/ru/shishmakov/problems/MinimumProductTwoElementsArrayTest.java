package ru.shishmakov.problems;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MinimumProductTwoElementsArrayTest {

    @Test
    void shouldReturnMinimumProductValue_ifArrayDefault() {
        // given
        var minimumProductTwoElementsArray = new MinimumProductTwoElementsArray(MinimumProductTwoElementsArray.DEFAULT_ARRAY);

        // when
        minimumProductTwoElementsArray.run();
        int productValue = minimumProductTwoElementsArray.getProduct();

        // then
        assertThat(productValue)
                .isEqualTo(MinimumProductTwoElementsArray.DEFAULT_PRODUCT);
    }

    @ParameterizedTest(name = "array={0}, product={1}")
    @MethodSource("validArrays")
    void shouldReturnMinimumProductValue(int[] array, int expectedProduct) {
        // given
        var minimumProductTwoElementsArray = new MinimumProductTwoElementsArray(array);

        // when
        minimumProductTwoElementsArray.run();
        int product = minimumProductTwoElementsArray.getProduct();

        // then
        assertThat(product)
                .isEqualTo(expectedProduct);
    }

    @ParameterizedTest(name = "array={0}")
    @MethodSource("notValidArrays")
    void shouldThrowException_ifArrayIllegal(int[] array) {
        // given
        var minimumProductTwoElementsArray = new MinimumProductTwoElementsArray(array);

        // expect
        Assertions.assertThatThrownBy(minimumProductTwoElementsArray::run)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Array length is not enough");
    }

    public static Stream<Arguments> validArrays() {
        return Stream.of(
                Arguments.of(new int[]{9, 4, 2, 5, 3}, 6),
                Arguments.of(new int[]{1, 5, 4, 5}, 4),
                Arguments.of(new int[]{3, 7}, 21),
                Arguments.of(new int[]{1, 5}, 5),
                Arguments.of(new int[]{-9, 4, 2, 5, -3}, -45),
                Arguments.of(new int[]{-9, -4, -2, -5, -3}, 6)
        );
    }

    public static Stream<int[]> notValidArrays() {
        return Stream.of(
                new int[]{},
                new int[]{2}
        );
    }
}
