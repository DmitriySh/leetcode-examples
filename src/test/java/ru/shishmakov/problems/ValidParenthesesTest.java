package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidParenthesesTest {

    @Test
    void stringValidIfOpenedAndClosedParenthesesHaveNoError() {
        // given
        ValidParentheses parentheses = new ValidParentheses("()[{}]");

        // when
        parentheses.run();
        boolean valid = parentheses.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @Test
    void stringValidIfOpenedAndClosedParenthesesHaveNoError2() {
        // given
        ValidParentheses parentheses = new ValidParentheses("[(){}]");

        // when
        parentheses.run();
        boolean valid = parentheses.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @Test
    void stringInvalidIfOpenedAndClosedParenthesesHaveSequenceErrors() {
        // given
        ValidParentheses parentheses = new ValidParentheses("[({)]}");

        // when
        parentheses.run();
        boolean valid = parentheses.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }

    @Test
    void stringInvalidIfClosedParenthesesAbsent() {
        // given
        ValidParentheses parentheses = new ValidParentheses("({[");

        // when
        parentheses.run();
        boolean valid = parentheses.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }

    @Test
    void stringInvalidIfAllParenthesesAbsent() {
        // given
        ValidParentheses parentheses = new ValidParentheses("qwe");

        // when
        parentheses.run();
        boolean valid = parentheses.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }

    @Test
    void stringInvalidIfValueEmpty() {
        // given
        ValidParentheses parentheses = new ValidParentheses("");

        // when
        parentheses.run();
        boolean valid = parentheses.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }
}
