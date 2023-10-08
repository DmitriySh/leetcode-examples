package ru.shishmakov;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * Given a 2D grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * <p/>
 * <a href="https://leetcode.ca/2016-06-17-200-Number-of-Islands/">Number of islands: problem solution</a>
 */
public class NumberOfIslands implements Runnable {

    private final int[][] grid = new int[][]{
            {1, 1, 0, 0, 1},
            {0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0},
            {0, 0, 0, 1, 1},
    };

    @Override
    public void run() {
        System.out.println("Start counting number of islands...");
        printGrid(grid);

        int islands = countIslands();
        System.out.println("Result. Grid islands: " + islands);
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
        printFoundLand(grid, r, c);
        int lastRow = grid.length - 1;
        int lastCol = grid[0].length - 1;

        grid[r][c] = 2;
        if (r >= 0 && r < lastRow && grid[r + 1][c] == 1) {
            dfs(grid, r + 1, c);
        }
        if (c >= 0 && c < lastCol && grid[r][c + 1] == 1) {
            dfs(grid, r, c + 1);
        }
    }

    private static void printFoundLand(int[][] grid, int r, int c) {
        System.out.println(MessageFormat.format(
                "Found grid[{0}][{1}] = {2}",
                r, c, grid[r][c]
        ));
    }

    private void printGrid(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(System.lineSeparator());
    }
}
