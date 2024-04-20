package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import ru.shishmakov.problems.ReverseLinkedList.ListNode;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReverseLinkedListTest {

    @Test
    void reverseTheLinkedListSuccessfully() {
        // given
        var originList = ReverseLinkedList.buildDefaultListNode(10);
        var linkedList = new ReverseLinkedList(originList);

        // when
        linkedList.run();
        var reversedList = linkedList.getHeadReversedList();

        // then
        assertThat(originList.toString())
                .isEqualTo("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
        assertThat(reversedList.toString())
                .isEqualTo("[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]");
    }

    @Test
    void reverseSmallLinkedListSuccessfully() {
        // given
        var originList = ReverseLinkedList.buildDefaultListNode(2);
        var linkedList = new ReverseLinkedList(originList);

        // when
        linkedList.run();
        var reversedList = linkedList.getHeadReversedList();

        // then
        assertThat(originList.toString())
                .isEqualTo("[1, 2]");
        assertThat(reversedList.toString())
                .isEqualTo("[2, 1]");
    }

    @Test
    void reverseEmptyLinkedListSuccessfully() {
        // given
        var originList = new ListNode();
        var linkedList = new ReverseLinkedList(originList);

        // when
        linkedList.run();
        var reversedList = linkedList.getHeadReversedList();

        // then
        assertThat(originList.toString())
                .isEqualTo("[0]");
        assertThat(reversedList.toString())
                .isEqualTo("[0]");
    }
}
