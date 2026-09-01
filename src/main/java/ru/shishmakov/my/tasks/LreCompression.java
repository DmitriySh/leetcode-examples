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
            return source;
        }

        // accumulate info about string chars
        char previousChar = 0;
        int counter = 0;
        var strBuilder = new StringBuilder();
        for (int i = 0; i < srcValue.length(); i++) {
            char currentChar = source.charAt(i);
            if (i == 0) {
                previousChar = currentChar;
                counter = 1;
            } else {
                if (currentChar == previousChar) {
                    counter++;
                } else {
                    strBuilder.append(previousChar);
                    if (counter > 1) strBuilder.append(counter);
                    previousChar = currentChar;
                    counter = 1;
                }
            }
        }
        // add info about last char
        if (previousChar != 0) {
            strBuilder.append(previousChar);
            if (counter > 1) strBuilder.append(counter);
        }
        return strBuilder.toString();
    }

    public static void main(String[] args) {
        new LreCompression(LreCompression.DEFAULT_STRING).run();
    }
}
