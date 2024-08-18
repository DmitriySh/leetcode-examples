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
 * <p/>
 * Constraints:
 * <ul>
 *     <li>the number of nodes in the list is in the range [1..100]</li>
 *     <li>1 <= Node.val <= 100</li>
 * </ul>
 *
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

    private final ListNode linkedList;
    private int middleNodeIndex;
    private int middleNodeValue;

    public MiddleOfLinkedList(ListNode linkedList) {
        this.linkedList = linkedList;
    }

    public int getMiddleNodeIndex() {
        return middleNodeIndex;
    }

    public int getMiddleNodeValue() {
        return middleNodeValue;
    }

    @Override
    public void run() {
        logger.info("Start searching the middle of LinkedList...");
        logger.info("Origin linked list: {}", linkedList);

        NodeResult result = searchNodeIndex(linkedList);
        this.middleNodeIndex = result.nodeIndex;
        this.middleNodeValue = result.node.value;
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
        ListNode node = null;
        for (int value = size; value > 0; value--) {
            node = new ListNode(value, node);
        }
        return node;
    }

    public static final class ListNode {
        private final int value;
        private ListNode next;

        ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode listNode = this;
            List<Integer> arrayList = new ArrayList<>();
            while (listNode != null) {
                arrayList.add(listNode.value);
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
                    nodeIndex, node.value
            );
        }
    }
}
