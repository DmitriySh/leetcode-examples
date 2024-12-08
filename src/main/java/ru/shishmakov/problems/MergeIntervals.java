package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56 - Merge Intervals.
 * <p>
 * Given an array of 'intervals' where intervals[i] = [a, b], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * <pre>
 * Example:
 * intervals = [[1,3], [2,6], [8,10], [15,18]]
 *
 *             [1,3] and [2,6] overlap => [1,6]
 *
 * result    = [[1,6], [8,10], [15,18]]
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-01-25-56-Merge-Intervals/">Merge Intervals: problem solution</a>
 */
public class MergeIntervals implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[][] DEFAULT_ARRAY = new int[][]{{1, 3}, {2, 6}, {10, 18}, {8, 10}};

    private final int[][] intervals;
    private int[][] mergedIntervals;

    public MergeIntervals(int[][] intervals) {
        this.intervals = intervals;
    }

    public int[][] getMergedIntervals() {
        return mergedIntervals;
    }

    @Override
    public void run() {
        logger.info("Start merge intervals...");
        logger.info("Origin intervals: {}", Arrays.deepToString(intervals));

        this.mergedIntervals = merge(intervals);
        logger.info("Result. {}", Arrays.deepToString(mergedIntervals));
    }

    private int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] prev = result.get(result.size() - 1);
            int[] curr = intervals[i];

            //  a1 b1   a2 b2
            // {0, 4}, {1, 4}
            int b1 = prev[1];
            int a2 = curr[0];
            int b2 = curr[1];
            if (a2 > b1) {
                result.add(curr);
            } else {
                prev[1] = Math.max(b1, b2);
            }
        }
        return result.toArray(new int[0][0]);
    }
}
