package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

/**
 * Given a 2D grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * <p/>
 * <a href="https://leetcode.ca/2016-06-17-200-Number-of-Islands/">Number of islands: problem solution</a>
 */
public class NumberOfIslands implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static final int[][] DEFAULT_GRID = new int[][]{
            {1, 1, 0, 0, 1},
            {0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0},
            {0, 0, 0, 1, 1},
    };

    private final int[][] grid;
    private int islands;

    public NumberOfIslands(int[][] grid) {
        this.grid = grid;
    }

    public int getIslands() {
        return islands;
    }

    @Override
    public void run() {
        logger.info("Start counting number of islands...");
        printGrid(grid);

        this.islands = countIslands();
        logger.info("Result. Grid islands: {}", this.islands);
    }

    private int countIslands() {
        int rows = grid.length;
        int cols = grid[0].length;

        int islands = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    islands++;
                    dfs(grid, r, c);
                    printGrid(grid);
                }
            }
        }
        return islands;
    }

    /**
     * Depth-first search (DFS) is an algorithm for traversing graph data structure into the deep.
     * <br/>
     * 0 - water
     * 1 - land
     * 2 - marker of the previous step
     */
    private void dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] != 1) {
            return;
        }

        grid[r][c] = 2;
        printFoundLand(grid, r, c);
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    private static void printFoundLand(int[][] grid, int r, int c) {
        logger.info("Found grid[{}][{}] = {}", r, c, grid[r][c]);
    }

    private void printGrid(int[][] array) {
        for (int[] row : array) {
            logger.info(Arrays.toString(row));
        }
        logger.info("");
    }
}
