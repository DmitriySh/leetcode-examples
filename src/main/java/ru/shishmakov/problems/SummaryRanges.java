package ru.shishmakov.problems;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 228 - Summary Ranges.
 * <p/>
 * Given a sorted unique integer array 'nums', return list of strings that are:
 * <ul>
 *     <li>has sorted items by ranges [a, b] that cover all numbers from the array;</li>
 *     <li>each number should exactly one range</li>
 *     <li>should be no ranges with integers that are not in 'nums'</li>
 * </ul>
 * <p/>
 *
 * <pre>
 * Example:
 *     source array         : [0, 1, 2, 4, 5, 7, 10]
 *     separate to ranges   : [[0, 1, 2], [4, 5], [7], [10]]
 *
 *     result array         : ["0-2", "4-5", "7", "10"]
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-07-15-228-Summary-Ranges/">Summary Ranges: problem solution</a>
 */
public class SummaryRanges implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_ARRAY = new int[]{0, 1, 2, 4, 5, 7, 10};
    public static final List<String> DEFAULT_RANGES = List.of("0->2", "4->5", "7", "10");

    private final int[] array;
    private List<String> ranges;

    public SummaryRanges(int[] array) {
        this.array = array;
    }

    public List<String> getRanges() {
        return ranges;
    }

    @Override
    public void run() {
        logger.info("Start string compression...");
        logger.info("Source array: {}", array);

        this.ranges = compress(array);
        logger.info("Result array of ranges: {}", ranges);
    }

    public static void main(String[] args) {
        var result = compress(new int[]{1, 2, 3, 4, 7});
//         var result = compress(new int[]{1,4,5,2,3,9,8,11,0,13});
//         var result = compress(new int[]{11});
//         var result = compress(new int[0]);
//         var result = compress(null);
        System.out.println(result);
    }

    private static List<String> compress(int[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }

        if (array.length == 1) {
            return List.of(Integer.toString(array[0]));
        }

        // {1, 4, 5, 2, 3, 9, 8, 11, 0, 13}
        // {0, 1, 2, 3, 4, 5, 8, 9, 11, 13}
        Arrays.sort(array);
        logger.info("Sorted array: {}", array);

        int startRange = array[0];
        int endRange = array[0];

        List<String> result = new ArrayList<>();
        for (int i = 1; i < array.length; i++) {
            int nextItem = array[i];

            if (nextItem == endRange + 1) {
                endRange = nextItem;
            } else {
                result.add(buildRange(startRange, endRange));
                startRange = nextItem;
                endRange = nextItem;
            }
        }
        result.add(buildRange(startRange, endRange));

        return result;
    }

    private static String buildRange(int startRange, int endRange) {
        String range;
        if (startRange == endRange) {
            range = Integer.toString(startRange);
        } else {
            range = startRange + "->" + endRange;
        }

        logger.info("Append next range: {}", range);
        return range;
    }
}
