package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MiddleOfLinkedListTest {

    @Test
    void shouldFindMiddleNodeForEvenLinkedListSizeSuccessfully() {
        // given
        var originList = MiddleOfLinkedList.buildDefaultListNode(6);
        var linkedList = new MiddleOfLinkedList(originList);

        // when
        linkedList.run();
        int middleNodeIndex = linkedList.getMiddleNodeIndex();
        int middleNodeValue = linkedList.getMiddleNodeValue();

        // then
        assertThat(originList.toString())
                .isEqualTo("[1, 2, 3, 4, 5, 6]");
        assertThat(middleNodeIndex)
                .isEqualTo(3);
        assertThat(middleNodeValue)
                .isEqualTo(4);
    }

    @Test
    void shouldFindMiddleNodeForOddLinkedListSizeSuccessfully() {
        // given
        var originList = MiddleOfLinkedList.buildDefaultListNode(5);
        var linkedList = new MiddleOfLinkedList(originList);

        // when
        linkedList.run();
        int middleNodeIndex = linkedList.getMiddleNodeIndex();
        int middleNodeValue = linkedList.getMiddleNodeValue();

        // then
        assertThat(originList.toString())
                .isEqualTo("[1, 2, 3, 4, 5]");
        assertThat(middleNodeIndex)
                .isEqualTo(2);
        assertThat(middleNodeValue)
                .isEqualTo(3);
    }
}
