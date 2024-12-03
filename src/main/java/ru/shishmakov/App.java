/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.shishmakov;

import ru.shishmakov.problems.FibonacciNumber;
import ru.shishmakov.problems.LongestSubstringWithoutRepeatingChars;
import ru.shishmakov.problems.MergeIntervals;
import ru.shishmakov.problems.MiddleOfLinkedList;
import ru.shishmakov.problems.MoveZeroes;
import ru.shishmakov.problems.NumberOfIslands;
import ru.shishmakov.problems.NumberOfOneBits;
import ru.shishmakov.problems.OneEditDistance;
import ru.shishmakov.problems.PlusOne;
import ru.shishmakov.problems.PowerOfTwo;
import ru.shishmakov.problems.RemoveDuplicatesFromSortedArray;
import ru.shishmakov.problems.ReverseLinkedList;
import ru.shishmakov.problems.Search2DMatrix;
import ru.shishmakov.problems.Sqrt;
import ru.shishmakov.problems.StringCompression;
import ru.shishmakov.problems.ValidAnagram;
import ru.shishmakov.problems.ValidIpAddress;
import ru.shishmakov.problems.ValidPalindrome;
import ru.shishmakov.problems.ValidParentheses;
import ru.shishmakov.problems.WordPattern;

import static ru.shishmakov.problems.NumberOfIslands.DEFAULT_GRID;
import static ru.shishmakov.problems.Search2DMatrix.DEFAULT_MATRIX;
import static ru.shishmakov.problems.ValidParentheses.DEFAULT_PARENTHESES;

public class App {

    public void start(String param) {
        switch (param) {
            case "numberOfIslands" -> new NumberOfIslands(DEFAULT_GRID).run();
            case "validParentheses" -> new ValidParentheses(DEFAULT_PARENTHESES).run();
            case "powerOfTwo" -> new PowerOfTwo(PowerOfTwo.DEFAULT_NUMBER).run();
            case "search2DMatrix" -> new Search2DMatrix(DEFAULT_MATRIX, Search2DMatrix.DEFAULT_NUMBER).run();
            case "numberOfOneBits" -> new NumberOfOneBits(NumberOfOneBits.DEFAULT_NUMBER).run();
            case "reverseLinkedList" -> new ReverseLinkedList(ReverseLinkedList.DEFAULT_LIST_NODE).run();
            case "middleOfLinkedList" -> new MiddleOfLinkedList(MiddleOfLinkedList.DEFAULT_LIST_NODE).run();
            case "validPalindrome" -> new ValidPalindrome(ValidPalindrome.DEFAULT_TEXT).run();
            case "validAnagram" -> new ValidAnagram(
                    ValidAnagram.DEFAULT_SOURCE_TEXT, ValidAnagram.DEFAULT_TARGET_TEXT
            ).run();
            case "wordPattern" -> new WordPattern(WordPattern.DEFAULT_PATTERN, WordPattern.DEFAULT_TEXT).run();
            case "validIpAddress" -> new ValidIpAddress(ValidIpAddress.DEFAULT_IP_ADDRESS).run();
            case "plusOne" -> new PlusOne(PlusOne.DEFAULT_DIGITS).run();
            case "removeDuplicatesFromSortedArray" -> new RemoveDuplicatesFromSortedArray(
                    RemoveDuplicatesFromSortedArray.DEFAULT_ARRAY
            ).run();
            case "oneEditDistance" -> new OneEditDistance(OneEditDistance.FIRST, OneEditDistance.SECOND).run();
            case "fibonacciNumber" -> new FibonacciNumber(FibonacciNumber.DEFAULT_NUMBER).run();
            case "stringCompression" -> new StringCompression(StringCompression.DEFAULT_CHARS).run();
            case "longestSubstringWithoutRepeatingChars" -> new LongestSubstringWithoutRepeatingChars(LongestSubstringWithoutRepeatingChars.DEFAULT_STRING).run();
            case "mergeIntervals" -> new MergeIntervals(MergeIntervals.DEFAULT_ARRAY).run();
            case "sqrt" -> new Sqrt(Sqrt.DEFAULT_VALUE).run();
            case "moveZeroes" -> new MoveZeroes(MoveZeroes.DEFAULT_NUMS).run();
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
