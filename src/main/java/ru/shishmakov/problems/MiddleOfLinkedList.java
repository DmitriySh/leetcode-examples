package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 876 - Middle of the Linked List.
 * <p/>
 * Given the 'head' of a singly linked list, return its middle node. If there are two middle nodes, return the second middle node.
 * <pre>
 * Example:
 *   origin : 1  ->  2  ->  3  ->  4 -> 5 -> 6 -> null
 *   middle node : 4 -> 5 -> 6 -> null
 *   middle position : 3
 *   middle value : 4
 *
 * Explanation: use fast and slow cursors by one iteration
 * </pre>
 *
 * <a href="https://github.com/carlos-anaya/leetcode/blob/master/problems/876-Middle%20of%20the%20Linked%20List.md">Middle of the Linked List: problem solution</a>
 */
public class MiddleOfLinkedList implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final ListNode DEFAULT_LIST_NODE = buildDefaultListNode(5);

    private final ListNode headOriginList;
    private int nodeIndex;
    private int nodeValue;

    public MiddleOfLinkedList(ListNode headOriginList) {
        this.headOriginList = headOriginList;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public int getNodeValue() {
        return nodeValue;
    }

    @Override
    public void run() {
        logger.info("Start searching the middle of LinkedList...");
        logger.info("Origin linked list: {}", headOriginList);

        NodeResult result = searchNodeIndex(headOriginList);
        this.nodeIndex = result.nodeIndex;
        this.nodeValue = result.node.val;
        logger.info("Result. {}", result);
    }

    private NodeResult searchNodeIndex(ListNode origin) {
        int index = 0;
        ListNode slowCursor = origin;
        ListNode fastCursor = origin;
        while (fastCursor != null && fastCursor.next != null) {
            slowCursor = slowCursor.next;
            fastCursor = fastCursor.next.next;
            index++;
        }
        return new NodeResult(index, slowCursor);
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

    private record NodeResult(int nodeIndex, ListNode node) {
        @Override
        public String toString() {
            return MessageFormat.format(
                    "LinkedList[{0}] = {1} is a middle",
                    nodeIndex, node.val
            );
        }
    }
}
