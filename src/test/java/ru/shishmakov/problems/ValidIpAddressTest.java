package ru.shishmakov.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidIpAddressTest {

    @ParameterizedTest(name = "IPv4 {0} = true")
    @ValueSource(strings = {"172.16.254.1", "192.168.1.0", "0.0.0.0", "0.0.1.1"})
    void stringIsValidIpV4Address(String param) {
        // given
        var validIpAddress = new ValidIpAddress(param);

        // when
        validIpAddress.run();
        String type = validIpAddress.getType();

        // then
        assertThat(type)
                .isEqualTo("IPv4");
    }

    @ParameterizedTest(name = "IPv4 {0} = false")
    @ValueSource(strings = {"256.16.254.1", "192.168.01.0", "192.0.0", "192.0.0.1.12", "0.12.1d.0"})
    void stringIsNotValidIpV4Address(String param) {
        // given
        var validIpAddress = new ValidIpAddress(param);

        // when
        validIpAddress.run();
        String type = validIpAddress.getType();

        // then
        assertThat(type)
                .isEqualTo("Neither");
    }

    @ParameterizedTest(name = "IPv6 {0} = true")
    @ValueSource(strings = {"2001:0db8:85a3:0:0:8A2E:0370:7334", "2001:db8:3333:4444:CCCC:DDDD:EEEE:FFFF", "1:1:1:1:1:1:1:1"})
    void stringIsValidIpV6Address(String param) {
        // given
        var validIpAddress = new ValidIpAddress(param);

        // when
        validIpAddress.run();
        String type = validIpAddress.getType();

        // then
        assertThat(type)
                .isEqualTo("IPv6");
    }

    @ParameterizedTest(name = "IPv6 {0} = false")
    @ValueSource(strings = {"2001:0db8:85a3:::8A2E:0377:7334", "0A2001:0db8:85a3:0000:0000:8a2e:0370:7334", "1:1:1:1:1:1:1:1:2"})
    void stringIsNotValidIpV6Address(String param) {
        // given
        var validIpAddress = new ValidIpAddress(param);

        // when
        validIpAddress.run();
        String type = validIpAddress.getType();

        // then
        assertThat(type)
                .isEqualTo("Neither");
    }
}
