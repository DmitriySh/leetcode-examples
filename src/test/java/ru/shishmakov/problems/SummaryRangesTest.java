package ru.shishmakov.problems;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SummaryRangesTest {

    @Test
    void shouldCompressDefaultSourceArrayByRanges() {
        // given
        var summaryRanges = new SummaryRanges(SummaryRanges.DEFAULT_ARRAY);

        // when
        summaryRanges.run();
        List<String> ranges = summaryRanges.getRanges();

        // then
        assertThat(ranges)
                .isNotNull()
                .isEqualTo(SummaryRanges.DEFAULT_RANGES);
    }

    @ParameterizedTest(name = "array={0}")
    @MethodSource("validArrays")
    void shouldCompressValidSourceArrayByRanges(int[] sourceArray, List<String> expectedRanges) {
        // given
        var summaryRanges = new SummaryRanges(sourceArray);

        // when
        summaryRanges.run();
        List<String> ranges = summaryRanges.getRanges();

        // then
        assertThat(ranges)
                .isNotNull()
                .isEqualTo(expectedRanges);
    }

    @ParameterizedTest(name = "array={0}")
    @MethodSource("notValidArrays")
    void shouldCompressNotValidSourceArrayByRanges(int[] sourceArray, List<String> expectedRanges) {
        // given
        var summaryRanges = new SummaryRanges(sourceArray);

        // when
        summaryRanges.run();
        List<String> ranges = summaryRanges.getRanges();

        // then
        assertThat(ranges)
                .isNotNull()
                .isEqualTo(expectedRanges);
    }

    public static Stream<Arguments> validArrays() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 3, 4, 7}, List.of("1->4", "7")),
                Arguments.arguments(new int[]{1, 4, 5, 2, 3, 9, 8, 11, 0, 13}, List.of("0->5", "8->9", "11", "13")),
                Arguments.arguments(new int[]{0, 1}, List.of("0->1")),
                Arguments.arguments(new int[]{0, 2}, List.of("0", "2")),
                Arguments.arguments(new int[]{11}, List.of("11"))
        );
    }

    public static Stream<Arguments> notValidArrays() {
        return Stream.of(
                Arguments.arguments(new int[0], List.of()),
                Arguments.arguments(null, List.of()),
                Arguments.arguments(new int[]{-11}, List.of("-11")),
                Arguments.arguments(new int[]{0, -1, 3, 1}, List.of("-1->1", "3"))
        );
    }
}
