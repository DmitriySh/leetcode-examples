package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PowerOfTwoTest {

    @ParameterizedTest(name = "number {0} = false")
    @ValueSource(ints = {-4, -1, 0, 3, 5, 7, 100, 101, 120, 777})
    void numbersIsNotPowerOfTwo(int number) {
        // given
        var power = new PowerOfTwo(number);

        // when
        power.run();
        boolean isPower = power.isPowerOfTwo();

        // then
        assertThat(isPower)
                .isFalse();
    }

    @ParameterizedTest(name = "number {0} = true")
    @ValueSource(ints = {1, 2, 4, 8, 64, 256, 512, 1024})
    void numbersIsPowerOfTwo(int number) {
        // given
        var power = new PowerOfTwo(number);

        // when
        power.run();
        boolean isPower = power.isPowerOfTwo();

        // then
        assertThat(isPower)
                .isTrue();
    }
}