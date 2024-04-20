package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import ru.shishmakov.problems.MiddleOfLinkedList.ListNode;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MiddleOfLinkedListTest {

    @Test
    void evenSizeLinkedListShouldFindMiddleNodeSuccessfully() {
        // given
        var originList = MiddleOfLinkedList.buildDefaultListNode(6);
        var linkedList = new MiddleOfLinkedList(originList);

        // when
        linkedList.run();
        int nodeIndex = linkedList.getNodeIndex();
        int nodeValue = linkedList.getNodeValue();

        // then
        assertThat(nodeIndex)
                .isEqualTo(3);
        assertThat(nodeValue)
                .isEqualTo(4);
    }

    @Test
    void oddSizeLinkedListShouldFindMiddleNodeSuccessfully() {
        // given
        var originList = MiddleOfLinkedList.buildDefaultListNode(5);
        var linkedList = new MiddleOfLinkedList(originList);

        // when
        linkedList.run();
        int nodeIndex = linkedList.getNodeIndex();
        int nodeValue = linkedList.getNodeValue();

        // then
        assertThat(nodeIndex)
                .isEqualTo(2);
        assertThat(nodeValue)
                .isEqualTo(3);
    }

    @Test
    void emptyLinkedListShouldFindMiddleNodeSuccessfully() {
        // given
        var originList = new ListNode();
        var linkedList = new MiddleOfLinkedList(originList);

        // when
        linkedList.run();
        int nodeIndex = linkedList.getNodeIndex();
        int nodeValue = linkedList.getNodeValue();

        // then
        assertThat(nodeIndex)
                .isEqualTo(0);
        assertThat(nodeValue)
                .isEqualTo(0);
    }
}
