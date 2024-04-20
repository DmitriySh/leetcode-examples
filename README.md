Leetcode examples
=======

My pet project.
This is a code examples with my solutions for Leetcode problems.

## Requirements:
  * Java SE Development Kit 11
  * Gradle 6.X (or you could use Gradle wrapper)
  * Git 1.7.x (or newer)


## Solved problems
 * [20 - Valid Parentheses](https://leetcode.com/problems/valid-parentheses) (whether the string with parentheses is valid or not)
 * [74 - Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix) (the number is in the matrix, true or false)
 * [125 - Valid Palindrome](https://leetcode.com/problems/valid-palindrome) (text is a palindrome, true or false)
 * [191 - Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits) (the number of 1 bits in unsigned integer)
 * [200 - Number of Islands](https://leetcode.com/problems/number-of-islands) (counting the number of islands in the grid NxN)
 * [206 - Reverse Linked List](https://leetcode.com/problems/reverse-linked-list) (reverse the linked list)
 * [231 - Power of two](https://leetcode.com/problems/power-of-two) (number is a power of two or not)
 * [876 - Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list) (find middle of the linked list)


## Run:
 * build app
```bash
~$ ./gradlew clean build

BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 executed
```

 * start app with problem (example)
```bash
~$ java -jar ./build/libs/leetcode-examples.jar validParentheses
Start validating parentheses...
Parentheses: ()[{}]

Result. Parentheses is: valid
```

* parameters: 
  * numberOfIslands
  * validParentheses
  * powerOfTwo
  * search2DMatrix
  * numberOfOneBits
  * reverseLinkedList
  * middleOfLinkedList
  * validPalindrome
