package ru.shishmakov.problems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayDeque;

/**
 * 20 - Valid Parentheses.
 * <p/>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * <ul>
 *     <li>open brackets must be closed by the same type of brackets;</li>
 *     <li>open brackets must be closed in the correct order.</li>
 * </ul>
 *
 * <pre>
 * Example:
 *   parentheses = "()[{}]"
 *   valid       = true
 *
 *   parentheses = "({[)"
 *   valid       = false
 * </pre>
 *
 * <a href="https://leetcode.ca/2015-12-20-20-Valid-Parentheses/">Valid parentheses: problem solution</a>
 */
public class ValidParentheses implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String DEFAULT_PARENTHESES = "()[{}]";

    private final String parentheses;
    private boolean valid;

    public ValidParentheses(String parentheses) {
        this.parentheses = parentheses.trim();
    }

    @Override
    public void run() {
        logger.info("Start validating parentheses...");
        logger.info("Parentheses: {}", parentheses);

        this.valid = validate(parentheses);
        logger.info("Result. Parentheses is: {}", (this.valid ? "valid" : "no valid"));
    }

    public boolean isValid() {
        return valid;
    }

    private boolean validate(String parentheses) {
        if (parentheses.isEmpty()) {
            return false;
        }

        var queue = new ArrayDeque<Character>(parentheses.length());
        for (int index = 0; index < parentheses.length(); index++) {
            char nextChar = parentheses.charAt(index);

            if (nextChar == '{' || nextChar == '[' || nextChar == '(') {
                queue.offerLast(nextChar); // add next open parentheses
            } else {
                if (queue.isEmpty()) {
                    return false; // wrong sequence
                }

                char lastChar = queue.peekLast();
                if (('(' == lastChar && nextChar == ')')
                        || ('{' == lastChar && nextChar == '}')
                        || ('[' == lastChar && nextChar == ']')
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
