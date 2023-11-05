package ru.shishmakov.problems;

import org.junit.jupiter.api.Test;
import ru.shishmakov.problems.ReverseLinkedList.ListNode;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReverseLinkedListTest {

    @Test
    void reverseTheLinkedListSuccessfully() {
        // given
        ListNode originList = ReverseLinkedList.buildDefaultListNode(5);
        ReverseLinkedList linkedList = new ReverseLinkedList(originList);

        // when
        linkedList.run();
        ListNode reversedList = linkedList.getReversedList();

        // then
        assertThat(originList.toString())
                .isEqualTo("[1, 2, 3, 4, 5]");
        assertThat(reversedList.toString())
                .isEqualTo("[5, 4, 3, 2, 1]");
    }

    @Test
    void reverseSmallLinkedListSuccessfully() {
        // given
        ListNode originList = ReverseLinkedList.buildDefaultListNode(2);
        ReverseLinkedList linkedList = new ReverseLinkedList(originList);

        // when
        linkedList.run();
        ListNode reversedList = linkedList.getReversedList();

        // then
        assertThat(originList.toString())
                .isEqualTo("[1, 2]");
        assertThat(reversedList.toString())
                .isEqualTo("[2, 1]");
    }

    @Test
    void reverseEmptyLinkedListSuccessfully() {
        // given
        ListNode originList = new ListNode();
        ReverseLinkedList linkedList = new ReverseLinkedList(originList);

        // when
        linkedList.run();
        ListNode reversedList = linkedList.getReversedList();

        // then
        assertThat(originList.toString())
                .isEqualTo("[0]");
        assertThat(reversedList.toString())
                .isEqualTo("[0]");
    }
}
