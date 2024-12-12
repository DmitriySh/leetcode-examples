package ru.shishmakov.problems;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FizzBuzzTest {

    @Test
    void shouldPrintDefaultNumberStrings() {
        // given
        List<String> expectedList = List.of("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz");
        FizzBuzz fizzBuzz = new FizzBuzz(FizzBuzz.DEFAULT_NUMBER);

        // when
        fizzBuzz.run();
        List<String> resultList = fizzBuzz.getResultList();

        // then
        assertThat(resultList)
                .isEqualTo(expectedList);
    }

    @ParameterizedTest(name = "number=[{0}] to string=[{1}]")
    @CsvSource({"1,1", "3,1 2 Fizz", "5,1 2 Fizz 4 Buzz"})
    void shouldPrintNumberStrings(int number, String expectedString) {
        // given
        List<String> expectedList = Arrays.asList(expectedString.split(" "));
        FizzBuzz fizzBuzz = new FizzBuzz(number);

        // when
        fizzBuzz.run();
        List<String> resultList = fizzBuzz.getResultList();

        // then
        assertThat(resultList)
                .isEqualTo(expectedList);
    }
}
