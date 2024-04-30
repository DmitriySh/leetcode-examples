package ru.shishmakov.my.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

/**
 * You have:
 * <ul>
 *   <li>an array of char[];</li>
 *   <li>`n` is the number of elements of "useful" part of the array starting from the left;</li>
 *   <li>the elements on the right of `n` can be ignored.</li>
 * </ul>
 * <p>
 * You should apply URL address encoding and replace spaces into URL-safe formats in the array of chars: ' ' => '%', '2', '0'.
 * Use in-place algorithm of replacement only the "useful" part of the array. All non-whitespace characters should be stayed the same.
 */
public class EncodeSpaceChars implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final char[] DEFAULT_CHARS = new char[]{'a', ' ', 'h', 'a', 't', ' ', 'o', 'n', '1', 'R', 'r', '&', '4', '!'};
    public static final int DEFAULT_PAYLOAD_LENGTH = 8;

    private final char[] chars;
    private final int payloadLength;

    private int newPayloadLength;

    public EncodeSpaceChars(char[] chars, int payloadLength) {
        this.chars = chars;
        this.payloadLength = payloadLength;
    }

    public char[] getChars() {
        return chars;
    }

    public int getNewPayloadLength() {
        return newPayloadLength;
    }

    @Override
    public void run() {
        logger.info("Start encoding space chars...");
        logger.info("Source char array: {}, payload length: {}", Arrays.toString(chars), payloadLength);

        this.newPayloadLength = encodeSpaces(chars, payloadLength);
        logger.info("Result char array: {}, new payload length: {}", Arrays.toString(chars), newPayloadLength);
    }

    private int encodeSpaces(char[] array, int length) {
        int spaceCount = 0;
        char[] spaceChars = new char[]{'%', '2', '0'};
        for (int i = 0; i < length - 1; i++) {
            if (array[i] == ' ') spaceCount++;
        }
        int result = length + spaceCount * 2;

        for (int i = length - 1; i >= 0; i--) {
            if (spaceCount == 0) {
                break;
            }

            if (array[i] != ' ') {
                array[i + spaceCount * 2] = array[i];
            } else {
                spaceCount--;
                for (int j = 0; j < spaceChars.length; j++) {
                    array[i + j + 2 * spaceCount] = spaceChars[j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new EncodeSpaceChars(EncodeSpaceChars.DEFAULT_CHARS, EncodeSpaceChars.DEFAULT_PAYLOAD_LENGTH).run();
    }
}
