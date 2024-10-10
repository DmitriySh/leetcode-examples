package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MergeIntervalsTest {

    @Test
    void shouldMergingDefaultTwoDimensionalArraySuccessfully() {
        // given
        int[][] intervals = MergeIntervals.DEFAULT_ARRAY;
        MergeIntervals mergeIntervals = new MergeIntervals(intervals);

        // when
        mergeIntervals.run();
        int[][] resultIntervals = mergeIntervals.getResultIntervals();

        // then
        assertThat(resultIntervals)
                .isEqualTo(new int[][]{{1, 6}, {8, 18}});
    }

    @ParameterizedTest(name = "origin {0}, expected {1}")
    @MethodSource("charsWithLengthProvider")
    void shouldMergingTwoDimensionalArraySuccessfully(int[][] origin, int[][] expected) {
        // given
        MergeIntervals mergeIntervals = new MergeIntervals(origin);

        // when
        mergeIntervals.run();
        int[][] resultIntervals = mergeIntervals.getResultIntervals();

        // then
        assertThat(resultIntervals)
                .isEqualTo(expected);
    }

    static Stream<Arguments> charsWithLengthProvider() {
        return Stream.of(
                arguments(new int[][]{{1, 4}, {0, 4}}, new int[][]{{0, 4}}),
                arguments(new int[][]{{2, 7}, {7, 4}}, new int[][]{{2, 7}}),
                arguments(new int[][]{{1, 4}, {5, 6}}, new int[][]{{1, 4}, {5, 6}}),
                arguments(new int[][]{{1, 4}, {2, 3}}, new int[][]{{1, 4}})
        );
    }
}
