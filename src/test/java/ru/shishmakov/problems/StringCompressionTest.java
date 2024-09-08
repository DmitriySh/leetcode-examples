package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Character.SPACE_SEPARATOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class StringCompressionTest {

    @Test
    void compressDefaultCharsSuccessfully() {
        // given
        int expectedPayloadLength = 5;
        char[] expectedChars = {'a', 'b', '1', '2', 'c', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'c'};
        var stringCompression = new StringCompression(StringCompression.DEFAULT_CHARS);

        // when
        stringCompression.run();
        char[] originChars = stringCompression.getOriginChars();
        char[] chars = stringCompression.getChars();
        int newCharsLength = stringCompression.getNewLength();

        // then
        assertThat(originChars)
                .isNotEqualTo(chars);
        assertThat(chars)
                .isEqualTo(expectedChars);
        assertThat(newCharsLength)
                .isEqualTo(expectedPayloadLength);
    }

    @ParameterizedTest(name = "origin [{0}], expected [{1}]")
    @MethodSource("charsWithLengthProvider")
    void compressDifferentCharsSuccessfully(char[] originChars, char[] expectedChars, int expectedPayloadLength) {
        // given
        var stringCompression = new StringCompression(originChars);

        // when
        stringCompression.run();
        char[] chars = stringCompression.getChars();
        int newCharsLength = stringCompression.getNewLength();

        // then
        assertThat(chars)
                .isEqualTo(expectedChars);
        assertThat(newCharsLength)
                .isEqualTo(expectedPayloadLength);
    }

    static Stream<Arguments> charsWithLengthProvider() {
        return Stream.of(
                arguments(new char[]{'a'}, new char[]{'a'}, 1),
                arguments(new char[]{SPACE_SEPARATOR}, new char[]{SPACE_SEPARATOR}, 1),
                arguments(new char[]{'a', 'b'}, new char[]{'a', 'b'}, 2),
                arguments(new char[]{'a', 'b', 'b', 'b'}, new char[]{'a', 'b', '3', 'b'}, 3),
                arguments(new char[]{'a', 'a', 'b', 'b'}, new char[]{'a', '2', 'b', '2'}, 4),
                arguments(new char[]{'a', 'a', 'a', 'a'}, new char[]{'a', '4', 'a', 'a'}, 2),
                arguments(new char[]{'7', '7', '7', '8'}, new char[]{'7', '3', '8', '8'}, 3)
        );
    }
}
