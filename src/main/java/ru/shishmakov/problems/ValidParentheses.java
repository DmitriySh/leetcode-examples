package ru.shishmakov.problems;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * <ul>
 *     <li>open brackets must be closed by the same type of brackets;</li>
 *     <li>open brackets must be closed in the correct order.</li>
 * </ul>
 * <a href="https://leetcode.ca/2015-12-20-20-Valid-Parentheses/">Valid parentheses: problem solution</a>
 */
public class ValidParentheses implements Runnable {
    public static final String DEFAULT_PARENTHESES = "()[{}]";

    private final String parentheses;
    private boolean valid;

    public ValidParentheses(String parentheses) {
        this.parentheses = parentheses.trim();
    }

    @Override
    public void run() {
        System.out.println("Start validating parentheses...");
        System.out.println("Parentheses: " + parentheses + System.lineSeparator());

        this.valid = isValid(parentheses);
        System.out.println("Result. Parentheses is: " + (this.valid ? "valid" : "no valid"));
    }

    public boolean isValid() {
        return valid;
    }

    private boolean isValid(String parentheses) {
        if (parentheses.isEmpty()) {
            return false;
        }

        Deque<Character> queue = new ArrayDeque<>(parentheses.length());
        for (int index = 0; index < parentheses.length(); index++) {
            char nextChar = parentheses.charAt(index);

            if (nextChar == '{' || nextChar == '[' || nextChar == '(') {
                queue.addLast(nextChar); // add next open parentheses
            } else {
                if (queue.isEmpty()) {
                    return false; // wrong sequence
                }

                char lastChar = queue.peekLast();
                if ((nextChar == ')' && lastChar == '(')
                        || (nextChar == '}' && lastChar == '{')
                        || (nextChar == ']' && lastChar == '[')
                ) {
                    queue.removeLast(); // remove previous open parentheses
                } else {
                    return false; // wrong sequence
                }
            }

        }
        return queue.isEmpty(); // check wrong sequence
    }
}
