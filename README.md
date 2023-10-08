Leetcode examples
=======

My pet project.
This is a code examples with my solutions for Leetcode problems.

## Requirements:
  * Java SE Development Kit 11
  * Gradle 6.X (or you could use Gradle wrapper)
  * Git 1.7.x (or newer)


## Solved problems
 * [200 - Number of Islands](https://leetcode.com/problems/number-of-islands/description/)
 * [20 - Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)


## Run:
 * build app
```bash
~$ ./gradlew clean build

BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 executed
```

 * start app
```bash
~$ java -jar ./build/libs/leetcode-examples.jar validParentheses
Start validating parentheses...
Parentheses: ()[{}]

Result. Parentheses is: valid
```

* parameters: 
  * numberOfIslands
  * validParentheses
