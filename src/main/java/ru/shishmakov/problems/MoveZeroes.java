package ru.shishmakov.problems;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 283 - Move Zeroes.
 * <p>
 * Given an integer array 'nums', move all 0's <u>to the end (right part)</u> of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note: you must do this in-place without making a copy of the array.
 *
 * <pre>
 * Example:
 *   [0]                 =>  [0]
 *   [1, 0]              =>  [1, 0]
 *   [0, 0, 2]           =>  [2, 0, 0]
 *   [0, 0, 3, 0]        =>  [3, 0, 0, 0]
 *   [0, 4, 0, 0, 5, 0]  =>  [4, 5, 0, 0, 0, 0]
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-09-08-283-Move-Zeroes/">Move Zeroes: problem solution</a>
 */
public class MoveZeroes implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_NUMS = new int[]{7, 0, 0, 1, 3, 0, 12, 0};

    private final int[] originNums;
    private final int[] nums;

    public MoveZeroes(int[] nums) {
        this.originNums = nums.clone();
        this.nums = nums;
    }

    public int[] getOriginNums() {
        return originNums;
    }

    public int[] getNums() {
        return nums;
    }

    @Override
    public void run() {
        logger.info("Start move zeroes...");
        logger.info("Source array: {}", Arrays.toString(nums));

        moveZeroes(nums);
        logger.info("Result array: {}", Arrays.toString(nums));
    }

    private void moveZeroes(int[] nums) {
        int a = 0;
        for (int b = 0; b < nums.length; b++) {
            if (nums[b] != 0) {
                int temp = nums[a];
                nums[a] = nums[b];
                nums[b] = temp;
                a++;
            }
        }
    }
}
