package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidPalindromeTest {

    @ParameterizedTest(name = "palindrome {0} = true")
    @ValueSource(strings = {"abba", "Madam, in Eden, I’m Adam.", "No lemon, no melon!", "A23, 32a", "", "  "})
    void stringValidIfTextIsPalindrome(String param) {
        // given
        var palindrome = new ValidPalindrome(param);

        // when
        palindrome.run();
        boolean valid = palindrome.isValid();

        // then
        assertThat(valid)
                .isTrue();
    }

    @ParameterizedTest(name = "palindrome {0} = false")
    @ValueSource(strings = {"ab", "test", "My mother", "No lemon", "123"})
    void stringNotValidIfTextIsNotPalindrome(String param) {
        // given
        var palindrome = new ValidPalindrome(param);

        // when
        palindrome.run();
        boolean valid = palindrome.isValid();

        // then
        assertThat(valid)
                .isFalse();
    }
}
