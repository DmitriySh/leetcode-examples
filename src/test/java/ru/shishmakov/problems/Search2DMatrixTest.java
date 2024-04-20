package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Search2DMatrixTest {

    @Test
    void shouldFoundNumberInMatrixIfAvailable() {
        // given
        var search2DMatrix = new Search2DMatrix(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60},
        }, 20);

        // when
        search2DMatrix.run();
        boolean found = search2DMatrix.isFound();

        // then
        assertThat(found)
                .isTrue();
    }

    @Test
    void shouldFoundNumberInMatrixIfAvailable2() {
        // given
        var search2DMatrix = new Search2DMatrix(new int[][]{
                {-11, -5, -1, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60},
        }, -5);

        // when
        search2DMatrix.run();
        boolean found = search2DMatrix.isFound();

        // then
        assertThat(found)
                .isTrue();
    }

    @Test
    void shouldNotFoundNumberInMatrixIfNotAvailable() {
        // given
        var search2DMatrix = new Search2DMatrix(new int[][]{
                {0, 3, 5, 9},
                {10, 11, 16, 20},
                {22, 30, 34, 60},
        }, 21);

        // when
        search2DMatrix.run();
        boolean found = search2DMatrix.isFound();

        // then
        assertThat(found)
                .isFalse();
    }
}
