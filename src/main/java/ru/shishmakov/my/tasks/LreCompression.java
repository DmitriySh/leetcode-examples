package ru.shishmakov.my.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class LreCompression implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String DEFAULT_STRING = "AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBF";

    private final String srcValue;
    private String compressedValue;

    public LreCompression(String srcValue) {
        this.srcValue = srcValue;
    }

    public String getCompressedValue() {
        return compressedValue;
    }

    @Override
    public void run() {
        logger.info("Start LRE compression...");
        logger.info("Source string: {}", srcValue);

        this.compressedValue = lreCompress(srcValue);
        logger.info("Result. Compressed string: {}", compressedValue);
    }

    private String lreCompress(String source) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Source string is invalid: '" + srcValue + "'");
        }

        // accumulate info about string chars
        char prevChar = 0;
        int counter = 0;
        var strBuilder = new StringBuilder();
        for (int i = 0; i < srcValue.length(); i++) {
            char lastChar = source.charAt(i);
            if (i == 0) {
                prevChar = lastChar;
                counter = 1;
            } else {
                if (lastChar == prevChar) {
                    counter++;
                } else {
                    strBuilder.append(prevChar);
                    if (counter > 1) strBuilder.append(counter);
                    prevChar = lastChar;
                    counter = 1;
                }
            }
        }
        // add info about last char
        if (prevChar != 0) {
            strBuilder.append(prevChar);
            if (counter > 1) strBuilder.append(counter);
        }
        return strBuilder.toString();
    }

    public static void main(String[] args) {
        new LreCompression(LreCompression.DEFAULT_STRING).run();
    }
}
