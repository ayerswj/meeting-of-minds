# Lesson 2: Control Flow

## Learning Objectives
- Understand conditional statements (if, else, else if)
- Learn about switch statements
- Master different types of loops (for, while, do-while)
- Practice using break and continue statements
- Understand logical operators and comparison operators

## What is Control Flow?
Control flow determines the order in which statements are executed in a program. It allows us to make decisions and repeat code based on conditions.

## Conditional Statements

### If Statement
The most basic form of decision-making:
```java
if (condition) {
    // code to execute if condition is true
}
```

### If-Else Statement
Provides an alternative when the condition is false:
```java
if (condition) {
    // code to execute if condition is true
} else {
    // code to execute if condition is false
}
```

### If-Else If-Else Statement
Handles multiple conditions:
```java
if (condition1) {
    // code for condition1
} else if (condition2) {
    // code for condition2
} else {
    // code if no conditions are true
}
```

### Switch Statement
Alternative to multiple if-else statements:
```java
switch (variable) {
    case value1:
        // code for value1
        break;
    case value2:
        // code for value2
        break;
    default:
        // code if no cases match
        break;
}
```

## Comparison Operators
- `==` : Equal to
- `!=` : Not equal to
- `>` : Greater than
- `<` : Less than
- `>=` : Greater than or equal to
- `<=` : Less than or equal to

## Logical Operators
- `&&` : AND (both conditions must be true)
- `||` : OR (at least one condition must be true)
- `!` : NOT (negates the condition)

## Loops

### For Loop
Used when you know how many times to repeat:
```java
for (initialization; condition; increment) {
    // code to repeat
}
```

### While Loop
Repeats while a condition is true:
```java
while (condition) {
    // code to repeat
}
```

### Do-While Loop
Similar to while, but executes at least once:
```java
do {
    // code to repeat
} while (condition);
```

### Enhanced For Loop (For-Each)
Used to iterate over arrays and collections:
```java
for (dataType item : array) {
    // code for each item
}
```

## Control Statements

### Break Statement
Exits a loop or switch statement:
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break; // exits the loop when i equals 5
    }
    System.out.println(i);
}
```

### Continue Statement
Skips the current iteration and continues with the next:
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        continue; // skips printing 5
    }
    System.out.println(i);
}
```

## Examples

### Grade Calculator
```java
int score = 85;
String grade;

if (score >= 90) {
    grade = "A";
} else if (score >= 80) {
    grade = "B";
} else if (score >= 70) {
    grade = "C";
} else if (score >= 60) {
    grade = "D";
} else {
    grade = "F";
}
```

### Number Guessing Game
```java
int secretNumber = 42;
int guess = 50;

if (guess == secretNumber) {
    System.out.println("Correct!");
} else if (guess < secretNumber) {
    System.out.println("Too low!");
} else {
    System.out.println("Too high!");
}
```

### Counting Loop
```java
for (int i = 1; i <= 10; i++) {
    System.out.println("Count: " + i);
}
```

## Exercises

### Exercise 1: Simple Calculator
Create a program that takes two numbers and an operator (+, -, *, /) and performs the calculation.

### Exercise 2: Grade Classification
Write a program that takes a student's score and prints their grade (A, B, C, D, F).

### Exercise 3: Number Patterns
Print different number patterns using loops:
- Print numbers 1 to 10
- Print even numbers from 2 to 20
- Print a multiplication table

### Exercise 4: Password Checker
Create a program that checks if a password meets certain criteria:
- At least 8 characters long
- Contains at least one uppercase letter
- Contains at least one number

### Exercise 5: Prime Number Checker
Write a program that determines if a given number is prime.

## Practice Files
- `ConditionalsDemo.java` - If-else and switch examples
- `LoopsDemo.java` - Different types of loops
- `ControlFlowExercises.java` - Practice problems

## Key Takeaways
1. Use if-else for simple decisions, switch for multiple values
2. Choose the right loop type based on your needs
3. Always use proper indentation for readability
4. Be careful with infinite loops
5. Use break and continue judiciously

## Next Steps
After completing this lesson, move on to:
- Functions and methods
- Arrays and collections
- Object-oriented programming basics

## Additional Resources
- [Java Control Flow Statements](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)
- [Java Loops Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/while.html)