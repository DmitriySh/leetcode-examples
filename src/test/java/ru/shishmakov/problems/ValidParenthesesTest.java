package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidParenthesesTest {

    @Test
    void name1() {
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
    void name2() {
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
    void name3() {
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
    void name4() {
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
    void name5() {
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
    void name6() {
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
