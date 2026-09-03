package ru.shishmakov.problems;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FindDistanceValueBetweenTwoArraysTest {

    @Test
    void shouldFindDistanceWithDefaultArrays() {
        // given
        var findDistanceValueBetweenTwoArrays = new FindDistanceValueBetweenTwoArrays(
                FindDistanceValueBetweenTwoArrays.DEFAULT_ARRAY1,
                FindDistanceValueBetweenTwoArrays.DEFAULT_ARRAY2,
                FindDistanceValueBetweenTwoArrays.DEFAULT_THRESHOLD
        );

        // when
        findDistanceValueBetweenTwoArrays.run();
        int distance = findDistanceValueBetweenTwoArrays.getDistance();

        // then
        assertThat(distance)
                .isEqualTo(2);
    }

    @ParameterizedTest(name = "array1={0}, array2={1}, threshold={2}")
    @MethodSource("validArrays")
    void shouldFindDistance(int[] array1, int[] array2, int threshold, int expectedDistance) {
        // given
        var findDistanceValueBetweenTwoArrays = new FindDistanceValueBetweenTwoArrays(array1, array2, threshold);

        // when
        findDistanceValueBetweenTwoArrays.run();
        int distance = findDistanceValueBetweenTwoArrays.getDistance();

        // then
        assertThat(distance)
                .isEqualTo(expectedDistance);
    }

    private static Stream<Arguments> validArrays() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 4, 2, 3}, // array1
                        new int[]{-4, -3, 6, 10, 20, 30}, // array2
                        3, // threshold
                        2 // distance
                ),
                Arguments.of(
                        new int[]{4, -3, -7, 0, -10},
                        new int[]{10},
                        69,
                        0
                ),
                Arguments.of(
                        new int[]{4, -3, -7, 0, -10},
                        new int[0],
                        7,
                        5
                )
        );
    }
}
