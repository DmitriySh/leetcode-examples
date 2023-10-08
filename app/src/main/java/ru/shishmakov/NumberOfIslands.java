package ru.shishmakov;

import java.text.MessageFormat;
import java.util.Arrays;

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
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }

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
        System.out.println(MessageFormat.format(
                "Found grid[{0}][{1}] = {2}",
                r, c, grid[r][c]
        ));
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
}
