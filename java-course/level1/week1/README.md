# Level 1: Week 1 - Introduction to Java & Environment Setup

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Understand Java's history and philosophy
- Set up a complete Java development environment
- Write and run your first Java programs
- Understand basic Java syntax and structure
- Use an IDE for Java development
- Understand the JVM and bytecode concepts

## 📚 Theory

### What is Java?
Java is a high-level, object-oriented programming language developed by Sun Microsystems (now Oracle) in 1995. It was designed to be:
- **Platform Independent**: "Write Once, Run Anywhere" (WORA)
- **Object-Oriented**: Everything is an object
- **Secure**: Built-in security features
- **Robust**: Strong type checking and error handling
- **Multithreaded**: Built-in support for concurrent programming

### Java's Key Features
- **Compiled and Interpreted**: Java code is compiled to bytecode, then interpreted by the JVM
- **Garbage Collection**: Automatic memory management
- **Rich Standard Library**: Extensive built-in APIs
- **Enterprise Ready**: Widely used in enterprise applications

## 🛠️ Environment Setup

### Step 1: Install Java Development Kit (JDK)
```bash
# Check if Java is already installed
java -version
javac -version

# If not installed, download JDK 17+ from Oracle or use OpenJDK
# For Ubuntu/Debian:
sudo apt update
sudo apt install openjdk-17-jdk

# For macOS (using Homebrew):
brew install openjdk@17

# For Windows:
# Download from https://www.oracle.com/java/technologies/downloads/
```

### Step 2: Set Environment Variables
```bash
# For Linux/macOS, add to ~/.bashrc or ~/.zshrc:
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$PATH:$JAVA_HOME/bin

# For Windows:
# Set JAVA_HOME=C:\Program Files\Java\jdk-17
# Add %JAVA_HOME%\bin to PATH
```

### Step 3: Install an IDE
**Recommended IDEs:**
- **IntelliJ IDEA**: Professional IDE with excellent Java support
- **Eclipse**: Free, powerful IDE with extensive plugins
- **VS Code**: Lightweight editor with Java extensions

### Step 4: Install Build Tools
```bash
# Install Maven
# For Ubuntu/Debian:
sudo apt install maven

# For macOS:
brew install maven

# For Windows:
# Download from https://maven.apache.org/download.cgi

# Verify installation
mvn -version
```

## 🚀 Your First Java Program

### Hello World Program
```java
// HelloWorld.java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Compiling and Running
```bash
# Compile the program
javac HelloWorld.java

# Run the program
java HelloWorld
```

### Understanding the Code
- `public class HelloWorld`: Declares a public class named HelloWorld
- `public static void main(String[] args)`: The main method - entry point of the program
- `System.out.println()`: Prints text to the console
- File name must match class name: `HelloWorld.java`

## 📝 Basic Java Syntax

### Program Structure
```java
// Comments
// Single-line comment
/*
 * Multi-line comment
 */

// Package declaration (optional)
package com.example;

// Import statements
import java.util.Scanner;

// Class declaration
public class MyClass {
    // Class members (fields, methods)
    
    // Main method
    public static void main(String[] args) {
        // Program logic here
    }
}
```

### Naming Conventions
```java
// Classes: PascalCase
public class StudentManager { }

// Methods and variables: camelCase
public void calculateGrade() { }
int studentCount = 0;

// Constants: UPPER_SNAKE_CASE
public static final int MAX_STUDENTS = 100;

// Packages: lowercase
package com.example.project;
```

## 🎯 Practice Examples

### Example 1: Simple Calculator
```java
import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        
        System.out.print("Enter operation (+, -, *, /): ");
        char operation = scanner.next().charAt(0);
        
        double result = 0;
        
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero!");
                    return;
                }
                break;
            default:
                System.out.println("Error: Invalid operation!");
                return;
        }
        
        System.out.printf("%.2f %c %.2f = %.2f%n", num1, operation, num2, result);
        scanner.close();
    }
}
```

### Example 2: Number Guessing Game
```java
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        
        int secretNumber = random.nextInt(100) + 1; // 1 to 100
        int attempts = 0;
        int maxAttempts = 10;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have " + maxAttempts + " attempts to guess it.");
        
        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;
            
            if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed it in " + attempts + " attempts!");
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try again. Attempts left: " + (maxAttempts - attempts));
            } else {
                System.out.println("Too high! Try again. Attempts left: " + (maxAttempts - attempts));
            }
        }
        
        if (attempts >= maxAttempts) {
            System.out.println("Game Over! The number was " + secretNumber);
        }
        
        scanner.close();
    }
}
```

## 🎯 Practice Exercises

### Exercise 1: Personal Information Display
Create a program that displays your personal information:
- Name
- Age
- Favorite programming language
- Hobbies

### Exercise 2: Temperature Converter
Create a program that converts temperatures between Celsius and Fahrenheit:
- Ask user for temperature and unit
- Convert and display result
- Support both directions (C to F and F to C)

### Exercise 3: Simple Interest Calculator
Create a program that calculates simple interest:
- Input: Principal, Rate, Time
- Output: Simple Interest and Total Amount
- Formula: SI = P × R × T / 100

## 🚀 Mini-Project: Student Grade Calculator

```java
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Student Grade Calculator ===");
        
        // Input student information
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        // Input grades for each subject
        String[] subjects = new String[numSubjects];
        double[] grades = new double[numSubjects];
        
        for (int i = 0; i < numSubjects; i++) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter subject " + (i + 1) + " name: ");
            subjects[i] = scanner.nextLine();
            
            System.out.print("Enter grade for " + subjects[i] + " (0-100): ");
            grades[i] = scanner.nextDouble();
            
            // Validate grade
            if (grades[i] < 0 || grades[i] > 100) {
                System.out.println("Invalid grade! Please enter a value between 0 and 100.");
                i--; // Retry this subject
            }
        }
        
        // Calculate average
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        double average = total / numSubjects;
        
        // Determine letter grade
        String letterGrade = getLetterGrade(average);
        
        // Display results
        System.out.println("\n=== Grade Report ===");
        System.out.println("Student: " + studentName);
        System.out.println("Number of subjects: " + numSubjects);
        System.out.println("\nSubject Grades:");
        
        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("%-15s: %.1f%% (%s)%n", 
                subjects[i], grades[i], getLetterGrade(grades[i]));
        }
        
        System.out.println("\n=== Summary ===");
        System.out.printf("Average Grade: %.2f%%%n", average);
        System.out.println("Letter Grade: " + letterGrade);
        System.out.println("Status: " + getStatus(average));
        
        scanner.close();
    }
    
    public static String getLetterGrade(double grade) {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }
    
    public static String getStatus(double average) {
        if (average >= 60) return "PASS";
        else return "FAIL";
    }
}
```

## 🎯 Advanced Concepts

### JVM Architecture
```
┌─────────────────────────────────────────────────────────────┐
│                    Java Application                        │
├─────────────────────────────────────────────────────────────┤
│                    Java Class Libraries                     │
├─────────────────────────────────────────────────────────────┤
│                    JVM (Java Virtual Machine)              │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────────────┐   │
│  │ Class Loader│ │ Bytecode    │ │ Garbage Collector   │   │
│  │             │ │ Verifier    │ │                     │   │
│  └─────────────┘ └─────────────┘ └─────────────────────┘   │
├─────────────────────────────────────────────────────────────┤
│                    Operating System                        │
└─────────────────────────────────────────────────────────────┘
```

### Bytecode and Compilation Process
```java
// Source code (HelloWorld.java)
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

// Compilation: javac HelloWorld.java
// Creates: HelloWorld.class (bytecode)

// Execution: java HelloWorld
// JVM interprets bytecode and runs the program
```

### Garbage Collection Basics
```java
// Objects are automatically cleaned up when no longer referenced
public class GarbageCollectionExample {
    public static void main(String[] args) {
        // Object created
        String str1 = new String("Hello");
        
        // Object referenced by str1
        String str2 = str1;
        
        // str1 now points to null, original object may be garbage collected
        str1 = null;
        
        // Object still referenced by str2, won't be collected
        System.out.println(str2);
        
        // After str2 goes out of scope, object can be collected
    }
}
```

## 📖 Additional Resources

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [IntelliJ IDEA Documentation](https://www.jetbrains.com/idea/documentation/)
- [Eclipse Documentation](https://www.eclipse.org/documentation/)

## 🎯 Weekly Challenge

Create a **Personal Library Management System** that:
1. Allows users to add books with title, author, and rating
2. Displays all books in the library
3. Searches for books by title or author
4. Calculates average rating of all books
5. Saves and loads library data from a file

## ✅ Checklist

- [ ] Java development environment is set up
- [ ] Can write and run simple Java programs
- [ ] Understand basic Java syntax and structure
- [ ] Can use an IDE for Java development
- [ ] Understand JVM and bytecode concepts
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 2 - Variables, Data Types & Operators](./../week2/README.md)