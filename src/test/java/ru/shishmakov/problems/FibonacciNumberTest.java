package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciNumberTest {

    @ParameterizedTest(name = "value=[{0}], calculated number=[{1}]")
    @CsvSource(value = {"0,0", "1,1", "2,1", "3,2", "4,3", "7,13", "30,832040"})
    public void calculateFibonacciNumberByIndex(int index, int calculatedNumber) {
        // given
        var fibonacciNumber = new FibonacciNumber(index);

        // when
        fibonacciNumber.run();
        int result = fibonacciNumber.getFibNumber();

        // then
        assertThat(result)
                .isEqualTo(calculatedNumber);
    }
}
