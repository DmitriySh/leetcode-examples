package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 468 - Validate IP Address.
 * <p/>
 * <a href="https://leetcode.ca/2017-03-12-468-Validate-IP-Address/">Validate IP Address: problem solution</a>
 */
public class ValidIpAddress implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String DEFAULT_IP_ADDRESS = "2001:0db8:85a3:0000:0000:8a2e:0370:7334";

    private final String queryIP;
    private String type;

    public ValidIpAddress(String queryIP) {
        this.queryIP = queryIP;
    }

    public String getType() {
        return type;
    }

    @Override
    public void run() {
        logger.info("Start validating IP address...");
        logger.info("IP address: {}", queryIP);

        this.type = validIPAddress(queryIP);
        logger.info("Result. IP address type: {}", type);
    }

    private String validIPAddress(String queryIP) {
        if (queryIP.contains(".")) {  // x1.x2.x3.x4
            String[] segments = queryIP.split("\\.", -1);
            if (segments.length != 4) {
                return "Neither";
            }

            for (String segment : segments) {
                // 0 <= xi <= 255
                if (segment.isEmpty() || segment.length() > 3 || (segment.startsWith("0") && segment.length() > 1)) {
                    return "Neither";
                }
                // digits = "0123456789"
                if (!segment.chars().allMatch(ch -> '0' <= (char) ch && (char) ch <= '9')) {
                    return "Neither";
                }
                int number = Integer.parseInt(segment);
                if (!(0 <= number && number <= 255)) {
                    return "Neither";
                }
            }
            return "IPv4";
        } else if (queryIP.contains(":")) {  // x1:x2:x3:x4:x5:x6:x7:x8
            String[] segments = queryIP.split(":", -1);
            if (segments.length != 8) {
                return "Neither";
            }

            for (String segment : segments) {
                // 1 <= xi.length <= 4
                if (segment.isEmpty() || segment.length() > 4) {
                    return "Neither";
                }
                // iPv6Chars = "0123456789abcdefABCDEF"
                if (!segment.chars().map(Character::toLowerCase)
                        .allMatch(ch -> ('0' <= (char) ch && (char) ch <= '9') || ('a' <= (char) ch && (char) ch <= 'f'))
                ) {
                    return "Neither";
                }
            }
            return "IPv6";
        }

        return "Neither";
    }
}
