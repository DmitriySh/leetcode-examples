package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

/**
 * 206 - Reverse Linked List.
 * <p/>
 * Given the 'head' of a singly linked list, reverse the list and return it.
 * <pre>
 * Example:
 *   origin   : 1  ->  2  ->  3  ->  4 -> null
 *   reversed : 4  ->  3  ->  2  ->  1 -> null
 *
 * Explanation:
 *   reversed    origin  next
 *     null   |    1  ->  2  ->  3  ->  4  ->  null
 *         reversed  origin  next
 *     null <- 1   |   2  ->  3  ->  4  ->  null
 *              reversed  origin  next
 *     null <- 1 <- 2   |   3  ->  4  ->  null
 *
 *     ...
 * </pre>
 *
 * <a href="https://leetcode.ca/2016-06-23-206-Reverse-Linked-List/">Reverse Linked List: problem solution</a>
 */
public class ReverseLinkedList implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final ListNode DEFAULT_LIST_NODE = buildDefaultListNode(5);

    private final ListNode headOriginList;
    private ListNode headReversedList;

    public ReverseLinkedList(ListNode headOriginList) {
        this.headOriginList = headOriginList;
    }

    public ListNode getHeadReversedList() {
        return headReversedList;
    }

    @Override
    public void run() {
        logger.info("Start reversing linked list...");
        logger.info("Origin linked list: {}", headOriginList);

        headReversedList = reverseList(copyList(headOriginList));
        logger.info("Result. Reversed linked list: {}", headReversedList);
    }

    private ListNode reverseList(ListNode origin) {
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

    public static final class ListNode {
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
