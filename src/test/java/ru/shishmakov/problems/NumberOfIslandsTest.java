package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberOfIslandsTest {

    @Test
    void testGridWithFiveIslands() {
        // given
        NumberOfIslands numberOfIslands = new NumberOfIslands(new int[][]{
                {1, 1, 0, 0, 1},
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1},
        });

        // when
        numberOfIslands.run();
        int islands = numberOfIslands.getIslands();

        // then
        assertThat(islands)
                .isEqualTo(5);
    }

    @Test
    void testGridWithThreeIslands() {
        // given
        NumberOfIslands numberOfIslands = new NumberOfIslands(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
        });

        // when
        numberOfIslands.run();
        int islands = numberOfIslands.getIslands();

        // then
        assertThat(islands)
                .isEqualTo(3);
    }

    @Test
    void testGridWithOneIsland() {
        NumberOfIslands numberOfIslands = new NumberOfIslands(new int[][]{
                {1, 1, 1},
                {0, 1, 0},
                {1, 1, 1}
        });

        // when
        numberOfIslands.run();
        int islands = numberOfIslands.getIslands();

        // then
        assertThat(islands)
                .isEqualTo(1);
    }

    @Test
    void testGridWithoutIsland() {
        NumberOfIslands numberOfIslands = new NumberOfIslands(new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        });

        // when
        numberOfIslands.run();
        int islands = numberOfIslands.getIslands();

        // then
        assertThat(islands)
                .isZero();
    }
}
