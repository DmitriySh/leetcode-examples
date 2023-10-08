package ru.shishmakov.problems;

import java.util.ArrayDeque;
import java.util.Deque;

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
                    return false;
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
