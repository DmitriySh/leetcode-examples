package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class ReverseLinkedList implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final ListNode DEFAULT_LIST_NODE = buildDefaultListNode(5);

    private final ListNode originList;
    private ListNode reversedList;

    public ReverseLinkedList(ListNode originList) {
        this.originList = originList;
    }

    public ListNode getReversedList() {
        return reversedList;
    }

    @Override
    public void run() {
        logger.info("Start reversing linked list...");
        logger.info("Origin linked list: {}", originList);

        reversedList = reverseList(copyList(originList));
        logger.info("Result. Reversed linked list: {}", reversedList);
    }

    private ListNode reverseList(ListNode origin) {
        // reversed    origin  next
        //   null   |    1  ->  2  ->  3  ->  4  ->  null
        ListNode reversed = null;
        while (origin != null) {
            ListNode next = origin.next;
            origin.next = reversed;
            reversed = origin;
            origin = next;
        }
        return reversed;
    }

    /**
     * Make a copy of a linked list. This is help not to change the original linked list
     */
    private ListNode copyList(ListNode origin) {
        if (origin == null) return null;

        ListNode cursor = new ListNode(origin.val); // will help build a new chain
        ListNode result = cursor; // reference to the top of linked list
        ListNode originNext = origin.next;
        while (originNext != null) {
            cursor.next = new ListNode(originNext.val);
            cursor = cursor.next;
            originNext = originNext.next;
        }
        return result;
    }

    public static ListNode buildDefaultListNode(int size) {
        ListNode node = new ListNode(size);
        for (int i = size - 1; i > 0; i--) {
            node = new ListNode(i /* value */, node /* next */);
        }
        return node;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode listNode = this;
            List<Integer> arrayList = new ArrayList<>();
            while (listNode != null) {
                arrayList.add(listNode.val);
                listNode = listNode.next;
            }
            return arrayList.toString();
        }
    }
}
