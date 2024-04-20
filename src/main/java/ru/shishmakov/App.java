/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.shishmakov;

import ru.shishmakov.problems.MiddleOfLinkedList;
import ru.shishmakov.problems.NumberOfIslands;
import ru.shishmakov.problems.NumberOfOneBits;
import ru.shishmakov.problems.PowerOfTwo;
import ru.shishmakov.problems.ReverseLinkedList;
import ru.shishmakov.problems.Search2DMatrix;
import ru.shishmakov.problems.ValidParentheses;

import static ru.shishmakov.problems.NumberOfIslands.DEFAULT_GRID;
import static ru.shishmakov.problems.Search2DMatrix.DEFAULT_MATRIX;
import static ru.shishmakov.problems.ValidParentheses.DEFAULT_PARENTHESES;

public class App {

    public void start(String param) {
        switch (param) {
            case "numberOfIslands" -> {
                new NumberOfIslands(DEFAULT_GRID).run();
            }
            case "validParentheses" -> {
                new ValidParentheses(DEFAULT_PARENTHESES).run();
            }
            case "powerOfTwo" -> {
                new PowerOfTwo(PowerOfTwo.DEFAULT_NUMBER).run();
            }
            case "search2DMatrix" -> {
                new Search2DMatrix(DEFAULT_MATRIX, Search2DMatrix.DEFAULT_NUMBER).run();
            }
            case "numberOfOneBits" -> {
                new NumberOfOneBits(NumberOfOneBits.DEFAULT_NUMBER).run();
            }
            case "reverseLinkedList" -> {
                new ReverseLinkedList(ReverseLinkedList.DEFAULT_LIST_NODE).run();
            }
            case "middleOfLinkedList" -> {
                new MiddleOfLinkedList(MiddleOfLinkedList.DEFAULT_LIST_NODE).run();
            }
            default -> throw new IllegalArgumentException("Unavailable param: " + param);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalStateException("No params in args array");
        }
        new App().start(args[0]);
    }
}
