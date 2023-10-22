package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidParenthesesTest {

    @ParameterizedTest(name = "parentheses {0} = true")
    @ValueSource(strings = {"()[{}]", "[(){}]", "[]{}()", "()[]{}"})
    void stringValidIfOpenedAndClosedParenthesesHaveNoError(String param) {
        // given
        ValidParentheses parentheses = new ValidParentheses(param);

        // when
        parentheses.run();
        boolean valid = parentheses.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @ParameterizedTest(name = "parentheses {0} = false")
    @ValueSource(strings = {"[({)]}", "({[", "()[{!}]", "qwe", ""})
    void stringInvalidIfOpenedAndClosedParenthesesHaveSequenceErrors(String param) {
        // given
        ValidParentheses parentheses = new ValidParentheses(param);

        // when
        parentheses.run();
        boolean valid = parentheses.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }
}
