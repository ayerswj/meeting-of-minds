# Week 2: Control Flow - Conditionals and Loops

## 🎯 Learning Objectives
- Master if-else statements and nested conditionals
- Understand switch statements and their use cases
- Learn different types of loops (for, while, do-while)
- Practice break and continue statements
- Build interactive programs with user input

## 📚 Theory

### What is Control Flow?
Control flow determines the order in which statements are executed in a program. It allows us to make decisions and repeat actions based on conditions.

### Conditional Statements

#### If Statement
The most basic form of decision-making:

```java
if (condition) {
    // Code to execute if condition is true
}
```

#### If-Else Statement
Provides an alternative when the condition is false:

```java
if (condition) {
    // Code to execute if condition is true
} else {
    // Code to execute if condition is false
}
```

#### If-Else If-Else Statement
Handles multiple conditions:

```java
if (condition1) {
    // Code for condition1
} else if (condition2) {
    // Code for condition2
} else if (condition3) {
    // Code for condition3
} else {
    // Default code
}
```

#### Nested If Statements
If statements inside other if statements:

```java
if (condition1) {
    if (condition2) {
        // Code for both conditions true
    } else {
        // Code for condition1 true, condition2 false
    }
} else {
    // Code for condition1 false
}
```

### Switch Statement
Alternative to multiple if-else statements:

```java
switch (expression) {
    case value1:
        // Code for value1
        break;
    case value2:
        // Code for value2
        break;
    case value3:
        // Code for value3
        break;
    default:
        // Default code
        break;
}
```

**Important**: Don't forget the `break` statement to prevent fall-through!

### Loops

#### For Loop
Used when you know the number of iterations:

```java
for (initialization; condition; increment/decrement) {
    // Code to repeat
}
```

#### While Loop
Used when you don't know the number of iterations:

```java
while (condition) {
    // Code to repeat
}
```

#### Do-While Loop
Similar to while, but executes at least once:

```java
do {
    // Code to repeat
} while (condition);
```

#### Enhanced For Loop (For-Each)
For iterating over arrays and collections:

```java
for (dataType variable : array/collection) {
    // Code using variable
}
```

### Control Flow Keywords

#### Break Statement
- Exits the current loop or switch statement
- Can be used with labels for nested loops

#### Continue Statement
- Skips the current iteration and continues with the next
- Can be used with labels for nested loops

## 💻 Practice Examples

### Example 1: Grade Classifier
```java
import java.util.Scanner;

public class GradeClassifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your score (0-100): ");
        int score = scanner.nextInt();
        
        String grade;
        String message;
        
        if (score >= 90) {
            grade = "A";
            message = "Excellent! Keep up the great work!";
        } else if (score >= 80) {
            grade = "B";
            message = "Good job! You're doing well.";
        } else if (score >= 70) {
            grade = "C";
            message = "Satisfactory. Consider studying more.";
        } else if (score >= 60) {
            grade = "D";
            message = "You need to improve. Seek help if needed.";
        } else {
            grade = "F";
            message = "Failed. You must retake this course.";
        }
        
        System.out.println("Your grade: " + grade);
        System.out.println("Message: " + message);
        
        scanner.close();
    }
}
```

### Example 2: Number Guessing Game
```java
import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int secretNumber = random.nextInt(100) + 1; // 1-100
        int attempts = 0;
        int maxAttempts = 10;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        
        while (attempts < maxAttempts) {
            System.out.print("Enter your guess (" + (maxAttempts - attempts) + " attempts left): ");
            int guess = scanner.nextInt();
            attempts++;
            
            if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed it in " + attempts + " attempts!");
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try a higher number.");
            } else {
                System.out.println("Too high! Try a lower number.");
            }
        }
        
        if (attempts >= maxAttempts) {
            System.out.println("Game Over! The number was " + secretNumber);
        }
        
        scanner.close();
    }
}
```

### Example 3: Menu-Driven Calculator
```java
import java.util.Scanner;

public class MenuCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Calculator Menu ===");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Choose an operation (1-5): ");
            
            int choice = scanner.nextInt();
            
            if (choice == 5) {
                System.out.println("Thank you for using the calculator!");
                break;
            }
            
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }
            
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();
            
            double result = 0;
            String operation = "";
            
            switch (choice) {
                case 1:
                    result = num1 + num2;
                    operation = "Addition";
                    break;
                case 2:
                    result = num1 - num2;
                    operation = "Subtraction";
                    break;
                case 3:
                    result = num1 * num2;
                    operation = "Multiplication";
                    break;
                case 4:
                    if (num2 != 0) {
                        result = num1 / num2;
                        operation = "Division";
                    } else {
                        System.out.println("Error: Division by zero!");
                        continue;
                    }
                    break;
            }
            
            System.out.println(operation + " result: " + num1 + " " + getOperator(choice) + " " + num2 + " = " + result);
        }
        
        scanner.close();
    }
    
    private static String getOperator(int choice) {
        switch (choice) {
            case 1: return "+";
            case 2: return "-";
            case 3: return "*";
            case 4: return "/";
            default: return "";
        }
    }
}
```

### Example 4: Pattern Printing
```java
public class PatternPrinter {
    public static void main(String[] args) {
        int rows = 5;
        
        // Right triangle pattern
        System.out.println("Right Triangle Pattern:");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        // Inverted triangle pattern
        System.out.println("\nInverted Triangle Pattern:");
        for (int i = rows; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        // Number pattern
        System.out.println("\nNumber Pattern:");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
```

## 🏋️ Exercises

### Exercise 1: Password Validator
Create a program that validates passwords based on these rules:
- At least 8 characters long
- Contains at least one uppercase letter
- Contains at least one lowercase letter
- Contains at least one digit
- Contains at least one special character (!@#$%^&*)

### Exercise 2: Prime Number Checker
Create a program that:
- Takes a number as input
- Checks if it's prime
- Displays all prime numbers up to that number
- Uses efficient algorithms

### Exercise 3: Simple ATM Machine
Create an ATM simulation with:
- Check balance
- Deposit money
- Withdraw money (with balance check)
- Exit option
- Persistent balance during session

### Exercise 4: Multiplication Table Generator
Create a program that:
- Generates multiplication tables for any number
- Allows user to specify the range
- Formats output nicely
- Handles invalid inputs

## 🔧 Practice Files

### PracticeFile1.java
```java
import java.util.Scanner;

public class PracticeFile1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // TODO: Create a simple quiz program
        // - Ask 5 multiple choice questions
        // - Track correct answers
        // - Display final score and percentage
        // - Give feedback based on performance
        
        scanner.close();
    }
}
```

### PracticeFile2.java
```java
public class PracticeFile2 {
    public static void main(String[] args) {
        // TODO: Create a number analysis program
        // - Generate 10 random numbers (1-100)
        // - Find the maximum and minimum
        // - Calculate average
        // - Count even and odd numbers
        // - Display all results
    }
}
```

## 📝 Quiz Questions

1. What is the difference between `if` and `switch` statements?
2. When would you use a `do-while` loop instead of a `while` loop?
3. What happens if you forget the `break` statement in a switch case?
4. What is the purpose of the `continue` statement?
5. How do you exit a nested loop from the inner loop?
6. What is the difference between `==` and `.equals()` for String comparison?
7. When would you use an enhanced for loop?
8. What is the output of `System.out.println(5 == 5.0);`?
9. How do you handle multiple conditions in an if statement?
10. What is the purpose of the `default` case in a switch statement?

## 🎯 Key Takeaways

- Use appropriate control structures for your logic
- Always handle edge cases and invalid inputs
- Be careful with loop conditions to avoid infinite loops
- Use meaningful variable names and comments
- Test your code with different inputs
- Consider efficiency when choosing loops

## 📚 Additional Resources

- [Java Control Flow Statements](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)
- [Java Switch Statement](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html)
- [Java Loops](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/while.html)

## 🚀 Next Steps

In the next lesson, we'll explore functions (methods) to organize our code better and make it reusable.