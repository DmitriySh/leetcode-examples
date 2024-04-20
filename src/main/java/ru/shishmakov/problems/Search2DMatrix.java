package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;

/**
 * 74 - Search a 2D Matrix.
 * <p/>
 * Given a 'number' and an MxN integer 2D 'matrix' with the following two properties:
 * <ul>
 * <li>each row is sorted in non-decreasing order</li>
 * <li>the first integer of each row is greater than the last integer of the previous row</li>
 * </ul>
 *
 * Return true if 'number' is in matrix or false otherwise.<br/>
 * <a href="https://leetcode.ca/2016-02-12-74-Search-a-2D-Matrix/">Search a 2D Matrix: problem solution</a>
 */
public class Search2DMatrix implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static final int[][] DEFAULT_MATRIX = new int[][]{
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60},
    };
    public static final int DEFAULT_NUMBER = 20;

    private final int[][] matrix;
    private final int number;
    private boolean found;
    private Map.Entry<Integer, Integer> position;

    public Search2DMatrix(int[][] matrix, int number) {
        this.matrix = matrix;
        this.number = number;
    }

    public boolean isFound() {
        return found;
    }

    public Map.Entry<Integer, Integer> getPosition() {
        return position;
    }

    @Override
    public void run() {
        logger.info("Start searching the number: {}...", number);
        printMatrix(matrix);

        NumberResult result = searchNumber();
        this.found = result.foundNumber;
        this.position = result.positionNumber;
        logger.info("Result. The number was: {}", result);
    }

    private NumberResult searchNumber() {
        boolean found = false;
        Map.Entry<Integer, Integer> position = null;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // [[1, 2, 3], [4, 5, 6]] => 6 length
        // left = 0, right = 5, middle = 2
        int left = 0;
        int right = rows * cols - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            int col = middle / cols;
            int row = middle % cols;

            int value = matrix[col][row];
            printPosition(col, row, value);
            if (value < number) {
                left = middle + 1;
            } else if (value > number) {
                right = middle - 1;
            } else { // equal
                found = true;
                position = Map.entry(col, row);
                break;
            }
        }
        logger.info("Stop.");
        return new NumberResult(found, position);
    }

    private void printPosition(int col, int row, int value) {
        logger.info(MessageFormat.format(
                "matrix[{0}][{1}]={2} {3}",
                col, row, value, value == number ? ("found!") : (value > number ? "<- left" : "-> right")
        ));
    }

    private void printMatrix(int[][] array) {
        for (int[] row : array) {
            logger.info(Arrays.toString(row));
        }
        logger.info("");
    }

    private static final class NumberResult {
        private final boolean foundNumber;
        private final Map.Entry<Integer, Integer> positionNumber;

        public NumberResult(boolean found, Map.Entry<Integer, Integer> position) {
            foundNumber = found;
            positionNumber = position;
        }

        @Override
        public String toString() {
            return this.foundNumber
                    ? MessageFormat.format("found in matrix[{0}][{1}]", positionNumber.getKey(), positionNumber.getValue())
                    : "not found";
        }
    }
}
