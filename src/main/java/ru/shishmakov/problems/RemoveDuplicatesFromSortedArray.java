package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * 26 - Remove Duplicates from Sorted Array.
 * <p/>
 * <a href="https://lifewithdata.com/2023/06/01/leetcode-remove-duplicates-from-sorted-array-solution-in-python/">Remove Duplicates from Sorted Array: problem solution</a>
 */
public class RemoveDuplicatesFromSortedArray implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final int[] DEFAULT_ARRAY = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

    private final int[] nums;
    private int payloadSize;

    public RemoveDuplicatesFromSortedArray(int[] nums) {
        this.nums = nums;
    }

    public int[] getNums() {
        return nums;
    }

    public int getPayloadSize() {
        return payloadSize;
    }

    @Override
    public void run() {
        logger.info("Start removing duplicate elements in array...");
        logger.info("Origin array: {}", nums);

        this.payloadSize = removeDuplicates(nums);
        logger.info("Result array: {}, size: {}", nums, payloadSize);
    }

    private int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                left++;
                nums[left] = nums[right];
            }
            right++;
        }

        return left + 1;
    }
}
