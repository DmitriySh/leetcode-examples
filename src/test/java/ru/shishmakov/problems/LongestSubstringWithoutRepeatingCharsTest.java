package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LongestSubstringWithoutRepeatingCharsTest {

    @Test
    void findLongestSubstringLengthFromDefaultStringSuccessfully() {
        // given
        var longestSubstring = new LongestSubstringWithoutRepeatingChars(LongestSubstringWithoutRepeatingChars.DEFAULT_STRING);

        // when
        longestSubstring.run();
        int length = longestSubstring.getLength();

        // then
        assertThat(length)
                .isEqualTo(3);
    }

    @ParameterizedTest(name = "string={0}, length={1}")
    @CsvSource(value = {"'',0", "a,1", "aab,2", "abc,3", "bbbbbb,1", "abcaabcbd,3"})
    void findLongestSubstringLength(String string, int expectedLength) {
        // given
        var longestSubstring = new LongestSubstringWithoutRepeatingChars(string);

        // when
        longestSubstring.run();
        int length = longestSubstring.getLength();

        // then
        assertThat(length)
                .isEqualTo(expectedLength);
    }
}
