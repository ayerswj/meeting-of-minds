# Level 1: Week 2 - Variables, Data Types & Operators

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Understand and use Java's primitive data types
- Work with reference types and objects
- Master Java operators and expressions
- Implement type casting and conversion
- Use constants and final variables
- Understand memory management and autoboxing

## 📚 Theory

### What are Variables?
Variables are containers that store data values in memory. In Java, every variable has a specific data type that determines:
- What kind of data it can store
- How much memory it uses
- What operations can be performed on it

### Variable Declaration and Initialization
```java
// Declaration
int age;

// Initialization
age = 25;

// Declaration and initialization in one line
int age = 25;

// Multiple variables of same type
int x = 10, y = 20, z = 30;
```

## 🔢 Primitive Data Types

### Integer Types
```java
// byte: 8-bit signed integer (-128 to 127)
byte smallNumber = 100;

// short: 16-bit signed integer (-32,768 to 32,767)
short mediumNumber = 1000;

// int: 32-bit signed integer (-2^31 to 2^31-1)
int regularNumber = 1000000;

// long: 64-bit signed integer (-2^63 to 2^63-1)
long bigNumber = 1000000000L; // Note the 'L' suffix
```

### Floating-Point Types
```java
// float: 32-bit floating point
float price = 19.99f; // Note the 'f' suffix

// double: 64-bit floating point (default for decimals)
double pi = 3.14159265359;
double salary = 50000.50;
```

### Character Type
```java
// char: 16-bit Unicode character
char grade = 'A';
char symbol = '$';
char unicode = '\u0041'; // Unicode for 'A'
```

### Boolean Type
```java
// boolean: true or false
boolean isStudent = true;
boolean isWorking = false;
```

### Complete Example
```java
public class DataTypesExample {
    public static void main(String[] args) {
        // Integer types
        byte byteValue = 127;
        short shortValue = 32767;
        int intValue = 2147483647;
        long longValue = 9223372036854775807L;
        
        // Floating-point types
        float floatValue = 3.14f;
        double doubleValue = 3.14159265359;
        
        // Character type
        char charValue = 'J';
        
        // Boolean type
        boolean booleanValue = true;
        
        // Display all values
        System.out.println("Byte: " + byteValue);
        System.out.println("Short: " + shortValue);
        System.out.println("Int: " + intValue);
        System.out.println("Long: " + longValue);
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("Char: " + charValue);
        System.out.println("Boolean: " + booleanValue);
        
        // Display ranges
        System.out.println("\nData Type Ranges:");
        System.out.println("Byte: " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("Short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("Int: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("Long: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
    }
}
```

## 📦 Reference Types

### String Class
```java
// String is a reference type, not primitive
String name = "John Doe";
String message = "Hello, World!";
String empty = ""; // Empty string
String nullString = null; // Null reference

// String concatenation
String firstName = "John";
String lastName = "Doe";
String fullName = firstName + " " + lastName;

// String methods
String text = "Hello World";
int length = text.length(); // 11
String upper = text.toUpperCase(); // "HELLO WORLD"
String lower = text.toLowerCase(); // "hello world"
boolean startsWith = text.startsWith("Hello"); // true
```

### Arrays
```java
// Array declaration and initialization
int[] numbers = {1, 2, 3, 4, 5};
String[] names = {"Alice", "Bob", "Charlie"};

// Array declaration with size
int[] scores = new int[5];
String[] cities = new String[3];

// Accessing array elements
int firstNumber = numbers[0]; // 1
String firstName = names[0]; // "Alice"

// Array length
int arrayLength = numbers.length; // 5
```

### Wrapper Classes
```java
// Wrapper classes for primitive types
Integer intWrapper = 100;
Double doubleWrapper = 3.14;
Boolean booleanWrapper = true;
Character charWrapper = 'A';

// Converting between primitive and wrapper
int primitiveInt = intWrapper; // Autounboxing
Integer wrapperInt = primitiveInt; // Autoboxing

// Useful methods
String numberString = "123";
int parsedInt = Integer.parseInt(numberString);
String intString = Integer.toString(123);
```

## 🔧 Operators

### Arithmetic Operators
```java
public class ArithmeticOperators {
    public static void main(String[] args) {
        int a = 10;
        int b = 3;
        
        // Addition
        int sum = a + b; // 13
        
        // Subtraction
        int difference = a - b; // 7
        
        // Multiplication
        int product = a * b; // 30
        
        // Division
        int quotient = a / b; // 3 (integer division)
        double preciseQuotient = (double) a / b; // 3.333...
        
        // Modulus (remainder)
        int remainder = a % b; // 1
        
        // Increment and decrement
        int x = 5;
        int preIncrement = ++x; // x becomes 6, preIncrement is 6
        int postIncrement = x++; // postIncrement is 6, x becomes 7
        
        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);
        System.out.println("Precise Quotient: " + preciseQuotient);
        System.out.println("Remainder: " + remainder);
    }
}
```

### Assignment Operators
```java
public class AssignmentOperators {
    public static void main(String[] args) {
        int x = 10;
        
        // Simple assignment
        x = 20;
        
        // Compound assignment operators
        x += 5;  // Same as: x = x + 5;
        x -= 3;  // Same as: x = x - 3;
        x *= 2;  // Same as: x = x * 2;
        x /= 4;  // Same as: x = x / 4;
        x %= 3;  // Same as: x = x % 3;
        
        System.out.println("Final value of x: " + x);
    }
}
```

### Comparison Operators
```java
public class ComparisonOperators {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        
        // Equal to
        boolean isEqual = (a == b); // false
        
        // Not equal to
        boolean isNotEqual = (a != b); // true
        
        // Greater than
        boolean isGreater = (a > b); // false
        
        // Less than
        boolean isLess = (a < b); // true
        
        // Greater than or equal to
        boolean isGreaterOrEqual = (a >= b); // false
        
        // Less than or equal to
        boolean isLessOrEqual = (a <= b); // true
        
        System.out.println("a == b: " + isEqual);
        System.out.println("a != b: " + isNotEqual);
        System.out.println("a > b: " + isGreater);
        System.out.println("a < b: " + isLess);
        System.out.println("a >= b: " + isGreaterOrEqual);
        System.out.println("a <= b: " + isLessOrEqual);
    }
}
```

### Logical Operators
```java
public class LogicalOperators {
    public static void main(String[] args) {
        boolean x = true;
        boolean y = false;
        
        // Logical AND
        boolean andResult = x && y; // false
        
        // Logical OR
        boolean orResult = x || y; // true
        
        // Logical NOT
        boolean notResult = !x; // false
        
        // Short-circuit evaluation
        boolean shortCircuit = false && (5 / 0 == 0); // No error due to short-circuit
        
        System.out.println("x && y: " + andResult);
        System.out.println("x || y: " + orResult);
        System.out.println("!x: " + notResult);
        System.out.println("Short-circuit: " + shortCircuit);
    }
}
```

## 🔄 Type Casting and Conversion

### Implicit Casting (Widening)
```java
public class ImplicitCasting {
    public static void main(String[] args) {
        // Automatic type conversion (widening)
        byte byteValue = 100;
        short shortValue = byteValue; // byte to short
        int intValue = shortValue;    // short to int
        long longValue = intValue;    // int to long
        float floatValue = longValue; // long to float
        double doubleValue = floatValue; // float to double
        
        System.out.println("Byte: " + byteValue);
        System.out.println("Short: " + shortValue);
        System.out.println("Int: " + intValue);
        System.out.println("Long: " + longValue);
        System.out.println("Float: " + floatValue);
        System.out.println("Double: " + doubleValue);
    }
}
```

### Explicit Casting (Narrowing)
```java
public class ExplicitCasting {
    public static void main(String[] args) {
        // Explicit type conversion (narrowing)
        double doubleValue = 3.14159;
        float floatValue = (float) doubleValue; // double to float
        long longValue = (long) floatValue;     // float to long
        int intValue = (int) longValue;         // long to int
        short shortValue = (short) intValue;    // int to short
        byte byteValue = (byte) shortValue;     // short to byte
        
        // Be careful with data loss
        int largeNumber = 1000000;
        byte smallByte = (byte) largeNumber; // Data loss!
        
        System.out.println("Original double: " + doubleValue);
        System.out.println("Casted to float: " + floatValue);
        System.out.println("Casted to long: " + longValue);
        System.out.println("Casted to int: " + intValue);
        System.out.println("Large number: " + largeNumber);
        System.out.println("Casted to byte: " + smallByte); // Unexpected result!
    }
}
```

## 🎯 Practice Examples

### Example 1: Temperature Converter
```java
import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Temperature Converter ===");
        System.out.print("Enter temperature: ");
        double temperature = scanner.nextDouble();
        
        System.out.print("Enter unit (C for Celsius, F for Fahrenheit): ");
        char unit = scanner.next().toUpperCase().charAt(0);
        
        double convertedTemp = 0;
        String resultUnit = "";
        
        switch (unit) {
            case 'C':
                convertedTemp = (temperature * 9/5) + 32;
                resultUnit = "Fahrenheit";
                break;
            case 'F':
                convertedTemp = (temperature - 32) * 5/9;
                resultUnit = "Celsius";
                break;
            default:
                System.out.println("Invalid unit! Please use C or F.");
                return;
        }
        
        System.out.printf("%.1f°%c = %.1f°%s%n", 
            temperature, unit, convertedTemp, resultUnit);
        
        scanner.close();
    }
}
```

### Example 2: Grade Calculator
```java
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Grade Calculator ===");
        
        // Input grades
        System.out.print("Enter assignment score (0-100): ");
        double assignment = scanner.nextDouble();
        
        System.out.print("Enter quiz score (0-100): ");
        double quiz = scanner.nextDouble();
        
        System.out.print("Enter exam score (0-100): ");
        double exam = scanner.nextDouble();
        
        // Calculate weighted average
        double assignmentWeight = 0.3; // 30%
        double quizWeight = 0.2;       // 20%
        double examWeight = 0.5;       // 50%
        
        double finalGrade = (assignment * assignmentWeight) + 
                           (quiz * quizWeight) + 
                           (exam * examWeight);
        
        // Determine letter grade
        String letterGrade;
        if (finalGrade >= 90) {
            letterGrade = "A";
        } else if (finalGrade >= 80) {
            letterGrade = "B";
        } else if (finalGrade >= 70) {
            letterGrade = "C";
        } else if (finalGrade >= 60) {
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }
        
        // Display results
        System.out.println("\n=== Grade Report ===");
        System.out.printf("Assignment (30%%): %.1f%n", assignment);
        System.out.printf("Quiz (20%%): %.1f%n", quiz);
        System.out.printf("Exam (50%%): %.1f%n", exam);
        System.out.printf("Final Grade: %.1f%% (%s)%n", finalGrade, letterGrade);
        
        scanner.close();
    }
}
```

## 🎯 Practice Exercises

### Exercise 1: Currency Converter
Create a program that converts between different currencies:
- Input: amount and source currency
- Convert to USD, EUR, and GBP
- Use fixed exchange rates for simplicity

### Exercise 2: BMI Calculator
Create a BMI (Body Mass Index) calculator:
- Input: weight (kg) and height (m)
- Calculate BMI using formula: BMI = weight / (height * height)
- Categorize: Underweight, Normal, Overweight, Obese

### Exercise 3: Simple Calculator with Memory
Create a calculator that:
- Performs basic arithmetic operations
- Has memory functions (store, recall, clear)
- Handles decimal numbers
- Validates input

## 🚀 Mini-Project: Bank Account Simulator

```java
import java.util.Scanner;

public class BankAccountSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Account variables
        String accountNumber = "1234567890";
        String accountHolder = "John Doe";
        double balance = 1000.0;
        double interestRate = 0.05; // 5% annual interest
        
        System.out.println("=== Bank Account Simulator ===");
        System.out.println("Account: " + accountNumber);
        System.out.println("Holder: " + accountHolder);
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.printf("Interest Rate: %.1f%%%n", interestRate * 100);
        
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Calculate Interest");
            System.out.println("5. Exit");
            
            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.printf("Current Balance: $%.2f%n", balance);
                    break;
                    
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double deposit = scanner.nextDouble();
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.printf("Deposited $%.2f. New balance: $%.2f%n", 
                            deposit, balance);
                    } else {
                        System.out.println("Invalid deposit amount!");
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawal = scanner.nextDouble();
                    if (withdrawal > 0 && withdrawal <= balance) {
                        balance -= withdrawal;
                        System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", 
                            withdrawal, balance);
                    } else {
                        System.out.println("Invalid withdrawal amount or insufficient funds!");
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter number of years: ");
                    int years = scanner.nextInt();
                    if (years > 0) {
                        double interest = balance * interestRate * years;
                        double newBalance = balance + interest;
                        System.out.printf("Interest for %d years: $%.2f%n", years, interest);
                        System.out.printf("New balance after interest: $%.2f%n", newBalance);
                    } else {
                        System.out.println("Invalid number of years!");
                    }
                    break;
                    
                case 5:
                    System.out.println("Thank you for using Bank Account Simulator!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice! Please enter 1-5.");
            }
        }
    }
}
```

## 🎯 Advanced Concepts

### Autoboxing and Unboxing
```java
public class AutoboxingExample {
    public static void main(String[] args) {
        // Autoboxing: primitive to wrapper
        Integer intObj = 100; // int to Integer
        Double doubleObj = 3.14; // double to Double
        Boolean boolObj = true; // boolean to Boolean
        
        // Unboxing: wrapper to primitive
        int primitiveInt = intObj; // Integer to int
        double primitiveDouble = doubleObj; // Double to double
        boolean primitiveBool = boolObj; // Boolean to boolean
        
        // Null handling
        Integer nullInt = null;
        // int nullPrimitive = nullInt; // NullPointerException!
        
        // Safe unboxing
        int safeInt = (nullInt != null) ? nullInt : 0;
        
        System.out.println("Autoboxing and unboxing work automatically!");
    }
}
```

### Type Inference (var keyword)
```java
public class TypeInferenceExample {
    public static void main(String[] args) {
        // Type inference with var (Java 10+)
        var message = "Hello, World!"; // String
        var number = 42; // int
        var price = 19.99; // double
        var isActive = true; // boolean
        
        // Arrays with var
        var numbers = new int[]{1, 2, 3, 4, 5};
        var names = new String[]{"Alice", "Bob", "Charlie"};
        
        // Limitations
        // var x; // Error: cannot infer type
        // var y = null; // Error: cannot infer type
        
        System.out.println("Type inference makes code more concise!");
    }
}
```

### Constants and Final Variables
```java
public class ConstantsExample {
    // Class constants (static final)
    public static final double PI = 3.14159;
    public static final String COMPANY_NAME = "TechCorp";
    public static final int MAX_EMPLOYEES = 1000;
    
    public static void main(String[] args) {
        // Local constants
        final int DAYS_IN_WEEK = 7;
        final String GREETING = "Hello";
        
        // Constants in calculations
        double radius = 5.0;
        double area = PI * radius * radius;
        
        System.out.println("Area of circle: " + area);
        System.out.println("Days in week: " + DAYS_IN_WEEK);
        System.out.println("Company: " + COMPANY_NAME);
        
        // Attempting to modify constants (will cause compilation error)
        // PI = 3.14; // Error: cannot assign a value to final variable
        // DAYS_IN_WEEK = 8; // Error: cannot assign a value to final variable
    }
}
```

## 📖 Additional Resources

- [Java Primitive Data Types](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [Java Operators](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)
- [Java Type Conversion](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/conversion.html)

## 🎯 Weekly Challenge

Create a **Scientific Calculator** that:
1. Supports basic arithmetic operations
2. Includes scientific functions (sin, cos, tan, log, sqrt)
3. Handles different number formats (decimal, binary, hexadecimal)
4. Has memory functions
5. Validates all inputs and handles errors gracefully

## ✅ Checklist

- [ ] Can declare and initialize variables of all primitive types
- [ ] Understand the difference between primitive and reference types
- [ ] Can use all arithmetic, comparison, and logical operators
- [ ] Can perform type casting and conversion
- [ ] Understand autoboxing and unboxing
- [ ] Can use constants and final variables
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 3 - Control Flow & Functions](./../week3/README.md)