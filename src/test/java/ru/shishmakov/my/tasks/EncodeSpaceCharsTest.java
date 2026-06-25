package ru.shishmakov.my.tasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class EncodeSpaceCharsTest {

    @Test
    void encodeShouldReplaceSpacesAndSetNewPayloadLengthFromDefaultCharsSuccessfully() {
        // given
        char[] defaultChars = EncodeSpaceChars.DEFAULT_CHARS;
        int defaultPayloadLength = EncodeSpaceChars.DEFAULT_PAYLOAD_LENGTH;
        var encodeSpaceChars = new EncodeSpaceChars(defaultChars.clone(), defaultPayloadLength);

        // when
        encodeSpaceChars.run();
        char[] resultChars = encodeSpaceChars.getChars();
        int newPayloadLength = encodeSpaceChars.getNewPayloadLength();

        // then
        assertThat(defaultChars)
                .isEqualTo(new char[]{'a', ' ', 'h', 'a', 't', ' ', 'o', 'n', '1', 'R', 'r', '&', '4', '!'});

        assertThat(resultChars)
                .isNotNull()
                .hasSize(defaultChars.length)
                .isNotEqualTo(defaultChars)
                .isEqualTo(new char[]{'a', '%', '2', '0', 'h', 'a', 't', '%', '2', '0', 'o', 'n', '4', '!'});

        assertThat(newPayloadLength)
                .isGreaterThan(defaultPayloadLength)
                .isEqualTo(12);

        assertThat(new String(defaultChars, 0, defaultPayloadLength))
                .isEqualTo("a hat on");

        assertThat(new String(resultChars, 0, newPayloadLength))
                .isEqualTo("a%20hat%20on");
    }

    @ParameterizedTest(name = "source={0}, length={1} to target={2}, newLength={3}")
    @CsvSource(value = {
            "'',0,'',0",
            "a,1,a,1",
            "abc,1,abc,1",
            "abc,3,abc,3",
            "abc d!,3,abc d!,3",
            "a bcd!,3,a%20b!,5",
            "a bcdef,3,a%20bef,5",
            "' a bcdef',4,%20a%20b,8",
            "'  abcdef',4,%20%20ab,8",
    })
    void encodeShouldReplaceSpacesAndSetNewPayloadLengthSuccessfully(
            String source, int sourcePayloadLength,
            String target, int targetPayloadLength
    ) {
        // given
        var encodeSpaceChars = new EncodeSpaceChars(source.toCharArray(), sourcePayloadLength);

        // when
        encodeSpaceChars.run();
        char[] resultChars = encodeSpaceChars.getChars();
        int newPayloadLength = encodeSpaceChars.getNewPayloadLength();

        // then
        assertThat(newPayloadLength)
                .isEqualTo(targetPayloadLength);
        assertThat(new String(resultChars, 0, newPayloadLength))
                .isEqualTo(new String(target.toCharArray(), 0, targetPayloadLength));
        assertThat(new String(resultChars))
                .isEqualTo(target);
    }
}
