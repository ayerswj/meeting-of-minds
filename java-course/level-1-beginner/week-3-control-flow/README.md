# Week 3: Control Flow Statements

## Learning Objectives
- Master conditional statements (if, if-else, if-else-if)
- Understand switch statements and their use cases
- Learn different types of loops (for, while, do-while)
- Use break and continue statements effectively
- Apply control flow in real-world scenarios

## Theory

### 1. Conditional Statements

#### If Statement
```java
if (condition) {
    // code to execute if condition is true
}
```

#### If-Else Statement
```java
if (condition) {
    // code to execute if condition is true
} else {
    // code to execute if condition is false
}
```

#### If-Else-If Statement
```java
if (condition1) {
    // code for condition1
} else if (condition2) {
    // code for condition2
} else {
    // default code
}
```

### 2. Switch Statement
```java
switch (expression) {
    case value1:
        // code for value1
        break;
    case value2:
        // code for value2
        break;
    default:
        // default code
}
```

### 3. Loops

#### For Loop
```java
for (initialization; condition; increment/decrement) {
    // loop body
}
```

#### While Loop
```java
while (condition) {
    // loop body
}
```

#### Do-While Loop
```java
do {
    // loop body
} while (condition);
```

### 4. Break and Continue
- `break`: Exits the current loop or switch statement
- `continue`: Skips the current iteration and continues with the next

## Code Examples

### Example 1: Grade Calculator
```java
public class GradeCalculator {
    public static void main(String[] args) {
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
        
        System.out.println("Score: " + score + ", Grade: " + grade);
    }
}
```

### Example 2: Day of Week with Switch
```java
public class DayOfWeek {
    public static void main(String[] args) {
        int day = 3;
        String dayName;
        
        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
            case 7:
                dayName = "Weekend";
                break;
            default:
                dayName = "Invalid day";
        }
        
        System.out.println("Day " + day + " is " + dayName);
    }
}
```

### Example 3: Number Guessing Game
```java
import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        boolean guessed = false;
        
        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        
        while (!guessed && attempts < 10) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;
            
            if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed it in " + attempts + " attempts!");
                guessed = true;
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }
        
        if (!guessed) {
            System.out.println("Game over! The number was " + secretNumber);
        }
        
        scanner.close();
    }
}
```

### Example 4: Pattern Printing
```java
public class PatternPrinter {
    public static void main(String[] args) {
        // Print right triangle
        System.out.println("Right Triangle:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        // Print multiplication table
        System.out.println("\nMultiplication Table for 5:");
        for (int i = 1; i <= 10; i++) {
            System.out.println("5 x " + i + " = " + (5 * i));
        }
    }
}
```

## Practice Exercises

### Exercise 1: Temperature Converter
Create a program that converts temperatures between Celsius and Fahrenheit based on user choice.

### Exercise 2: Simple Calculator
Build a calculator that performs basic arithmetic operations using switch statements.

### Exercise 3: Prime Number Checker
Write a program that checks if a given number is prime using loops.

### Exercise 4: Fibonacci Series
Generate the first 20 numbers in the Fibonacci series using loops.

### Exercise 5: Password Validator
Create a program that validates passwords based on multiple criteria (length, special characters, etc.).

## Mini-Project: Student Grade Management System

Create a simple grade management system that:
- Takes student scores for multiple subjects
- Calculates average grades
- Assigns letter grades
- Provides performance feedback
- Uses various control flow statements

```java
import java.util.Scanner;

public class GradeManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        
        for (int student = 1; student <= numStudents; student++) {
            System.out.println("\n--- Student " + student + " ---");
            
            System.out.print("Enter student name: ");
            String name = scanner.next();
            
            System.out.print("Enter number of subjects: ");
            int numSubjects = scanner.nextInt();
            
            double totalScore = 0;
            
            for (int subject = 1; subject <= numSubjects; subject++) {
                System.out.print("Enter score for subject " + subject + ": ");
                double score = scanner.nextDouble();
                totalScore += score;
            }
            
            double average = totalScore / numSubjects;
            String grade = calculateGrade(average);
            String feedback = getFeedback(average);
            
            System.out.println("\nResults for " + name + ":");
            System.out.println("Average: " + String.format("%.2f", average));
            System.out.println("Grade: " + grade);
            System.out.println("Feedback: " + feedback);
        }
        
        scanner.close();
    }
    
    public static String calculateGrade(double average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
    
    public static String getFeedback(double average) {
        if (average >= 90) return "Excellent work!";
        else if (average >= 80) return "Good job!";
        else if (average >= 70) return "Keep improving!";
        else if (average >= 60) return "Need more effort!";
        else return "Requires significant improvement!";
    }
}
```

## Advanced Concepts

### 1. Nested Loops and Conditions
```java
public class NestedPatterns {
    public static void main(String[] args) {
        // Print pyramid pattern
        int rows = 5;
        for (int i = 1; i <= rows; i++) {
            // Print spaces
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            // Print stars
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
```

### 2. Enhanced Switch (Java 14+)
```java
public class EnhancedSwitch {
    public static void main(String[] args) {
        String day = "MONDAY";
        
        String result = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Unknown";
        };
        
        System.out.println(day + " is a " + result);
    }
}
```

### 3. Labeled Break and Continue
```java
public class LabeledStatements {
    public static void main(String[] args) {
        outerLoop: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    break outerLoop; // Breaks out of both loops
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
    }
}
```

## Weekly Challenge: Text-Based Adventure Game

Create a simple text-based adventure game with multiple paths and outcomes using control flow statements.

**Requirements:**
- Multiple story branches
- Character stats (health, inventory)
- Combat system
- Multiple endings
- Use of all control flow concepts

## Assessment Criteria
- [ ] Correct use of if-else statements
- [ ] Proper switch statement implementation
- [ ] Effective loop usage
- [ ] Break and continue statements
- [ ] Code readability and structure
- [ ] Problem-solving approach

## Resources
- [Java Control Flow Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)
- [Java Switch Statement](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html)
- [Java Loops](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/while.html)