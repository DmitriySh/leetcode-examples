package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumberOfOneBitsTest {

    @ParameterizedTest(name = "count bits of number {0}")
    @ValueSource(ints = {0, 1, 2, 3, 7, 8, 63, 85, 1073741824, Integer.MAX_VALUE})
    void shouldCountNumberOfOneBitsSuccessfully(int number) {
        // given
        var bitsNumber = new NumberOfOneBits(number);

        // when
        bitsNumber.run();
        int count = bitsNumber.getOneBitsCount();

        // then
        assertThat(count)
                .isEqualTo(Integer.bitCount(number));
    }
}
