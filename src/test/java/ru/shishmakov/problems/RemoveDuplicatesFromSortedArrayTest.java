package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RemoveDuplicatesFromSortedArrayTest {

    @Test
    void shouldRemoveDuplicatesFromDefaultArray() {
        // given
        int[] numsBefore = RemoveDuplicatesFromSortedArray.DEFAULT_ARRAY;
        var removeDuplicates = new RemoveDuplicatesFromSortedArray(numsBefore.clone());

        // when
        removeDuplicates.run();
        int[] numsAfter = removeDuplicates.getNums();
        int payloadSize = removeDuplicates.getPayloadSize();

        // then
        assertThat(numsBefore)
                .isEqualTo(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        assertThat(payloadSize)
                .isEqualTo(5);
        assertThat(numsAfter)
                .isEqualTo(new int[]{0, 1, 2, 3, 4, 2, 2, 3, 3, 4}); // payload: [0, 1, 2, 3, 4]
    }

    @Test
    void shouldRemoveDuplicatesFromArrayWithNegativeElements() {
        // given
        int[] numsBefore = new int[]{-7, -1, -1, -1, 0, 2, 3, 3, 3, 4};
        var removeDuplicates = new RemoveDuplicatesFromSortedArray(numsBefore.clone());

        // when
        removeDuplicates.run();
        int[] numsAfter = removeDuplicates.getNums();
        int payloadSize = removeDuplicates.getPayloadSize();

        // then
        assertThat(payloadSize)
                .isEqualTo(6);
        assertThat(numsAfter)
                .isEqualTo(new int[]{-7, -1, 0, 2, 3, 4, 3, 3, 3, 4}); // payload: [-7, -1, 0, 2, 3, 4]
    }

    @Test
    void shouldRemoveDuplicatesFromArrayWithZeroElements() {
        // given
        int[] numsBefore = new int[]{0, 0, 0};
        var removeDuplicates = new RemoveDuplicatesFromSortedArray(numsBefore.clone());

        // when
        removeDuplicates.run();
        int[] numsAfter = removeDuplicates.getNums();
        int payloadSize = removeDuplicates.getPayloadSize();

        // then
        assertThat(payloadSize)
                .isEqualTo(1);
        assertThat(numsAfter)
                .isEqualTo(new int[]{0, 0, 0}); // payload: [0]
    }

    @Test
    void shouldSkipArrayWithOneElements() {
        // given
        int[] numsBefore = new int[]{0};
        var removeDuplicates = new RemoveDuplicatesFromSortedArray(numsBefore.clone());

        // when
        removeDuplicates.run();
        int[] numsAfter = removeDuplicates.getNums();
        int payloadSize = removeDuplicates.getPayloadSize();

        // then
        assertThat(payloadSize)
                .isEqualTo(1);
        assertThat(numsAfter)
                .isEqualTo(new int[]{0}); // payload: [0]
    }
}
