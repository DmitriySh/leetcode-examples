package ru.shishmakov.my.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class LreDeCompression implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String DEFAULT_STRING = "A4B3C2XYZD4E3F3A6B28F";

    private final String srcValue;
    private String decompressedValue;

    public LreDeCompression(String srcValue) {
        this.srcValue = srcValue;
    }

    public String getDecompressedValue() {
        return decompressedValue;
    }

    @Override
    public void run() {
        logger.info("Start LRE decompression...");
        logger.info("Source string: {}", srcValue);

        this.decompressedValue = lreDecompress(srcValue);
        logger.info("Result. Decompressed string: {}", decompressedValue);
    }

    private String lreDecompress(String source) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Source string is invalid: '" + srcValue + "'");
        }

        var strBuilder = new StringBuilder();
        var strNumber = new StringBuilder();
        char prevChar = 0;
        int i = -1;
        while (i++ < source.length() - 1) {
            char lastChar = source.charAt(i);
            if (i == 0) {
                prevChar = lastChar;
                continue;
            }

            if (Character.isDigit(lastChar)) {
                strNumber.append(lastChar);
            } else {
                String strN = strNumber.toString();
                int number = strN.isEmpty() ? 1 : Integer.parseInt(strN);
                for (int j = 0; j < number; j++) {
                    strBuilder.append(prevChar);
                }
                strNumber.setLength(0);
                prevChar = lastChar;
            }
        }
        if (prevChar != 0) {
            String strN = strNumber.toString();
            int number = strN.isEmpty() ? 1 : Integer.parseInt(strN);
            for (int j = 0; j < number; j++) {
                strBuilder.append(prevChar);
            }
        }

        return strBuilder.toString();
    }

    public static void main(String[] args) {
        new LreDeCompression(LreDeCompression.DEFAULT_STRING).run();
    }
}
