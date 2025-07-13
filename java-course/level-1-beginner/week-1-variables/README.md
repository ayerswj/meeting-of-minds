# Week 1: Variables, Data Types, and Operators

## 🎯 Learning Objectives
- Understand Java's basic data types
- Learn how to declare and initialize variables
- Master arithmetic, comparison, and logical operators
- Practice type conversion and casting
- Build your first Java programs

## 📚 Theory

### What are Variables?
Variables are containers that store data values in memory. In Java, every variable has a specific data type that determines what kind of data it can hold.

### Java Data Types

#### Primitive Data Types
Java has 8 primitive data types:

| Type | Size | Range | Default Value | Example |
|------|------|-------|---------------|---------|
| `byte` | 8 bits | -128 to 127 | 0 | `byte age = 25;` |
| `short` | 16 bits | -32,768 to 32,767 | 0 | `short year = 2024;` |
| `int` | 32 bits | -2³¹ to 2³¹-1 | 0 | `int population = 1000000;` |
| `long` | 64 bits | -2⁶³ to 2⁶³-1 | 0L | `long worldPopulation = 8000000000L;` |
| `float` | 32 bits | ±3.4E-38 to ±3.4E+38 | 0.0f | `float pi = 3.14f;` |
| `double` | 64 bits | ±1.7E-308 to ±1.7E+308 | 0.0 | `double gravity = 9.81;` |
| `char` | 16 bits | '\u0000' to '\uffff' | '\u0000' | `char grade = 'A';` |
| `boolean` | 1 bit | true or false | false | `boolean isActive = true;` |

#### Reference Data Types
- **String**: Sequence of characters
- **Arrays**: Collections of elements
- **Classes**: User-defined types
- **Interfaces**: Abstract types

### Variable Declaration and Initialization

```java
// Declaration
int age;
String name;

// Initialization
age = 25;
name = "John Doe";

// Declaration and initialization in one line
int score = 95;
String city = "New York";
```

### Naming Conventions
- Use camelCase: `firstName`, `totalScore`
- Start with a letter, underscore, or dollar sign
- Cannot use Java keywords
- Case-sensitive
- Descriptive names are preferred

### Operators

#### Arithmetic Operators
```java
int a = 10, b = 3;

int sum = a + b;        // 13
int difference = a - b; // 7
int product = a * b;    // 30
int quotient = a / b;   // 3
int remainder = a % b;  // 1

// Increment and decrement
int x = 5;
x++; // x = 6 (post-increment)
++x; // x = 7 (pre-increment)
x--; // x = 6 (post-decrement)
--x; // x = 5 (pre-decrement)
```

#### Comparison Operators
```java
int a = 10, b = 5;

boolean isEqual = (a == b);      // false
boolean isNotEqual = (a != b);   // true
boolean isGreater = (a > b);     // true
boolean isLess = (a < b);        // false
boolean isGreaterEqual = (a >= b); // true
boolean isLessEqual = (a <= b);  // false
```

#### Logical Operators
```java
boolean x = true, y = false;

boolean and = x && y;  // false (logical AND)
boolean or = x || y;   // true (logical OR)
boolean not = !x;      // false (logical NOT)
```

#### Assignment Operators
```java
int x = 10;

x += 5;  // x = x + 5 (15)
x -= 3;  // x = x - 3 (12)
x *= 2;  // x = x * 2 (24)
x /= 4;  // x = x / 4 (6)
x %= 4;  // x = x % 4 (2)
```

### Type Conversion and Casting

#### Implicit Conversion (Widening)
```java
int intValue = 100;
long longValue = intValue;  // Automatic conversion
float floatValue = longValue; // Automatic conversion
double doubleValue = floatValue; // Automatic conversion
```

#### Explicit Conversion (Narrowing)
```java
double doubleValue = 100.04;
int intValue = (int) doubleValue;  // 100 (truncated)
long longValue = (long) doubleValue; // 100

// Be careful with data loss
int largeInt = 1000000;
byte smallByte = (byte) largeInt; // Data loss occurs
```

## 💻 Practice Examples

### Example 1: Basic Variable Operations
```java
public class VariableBasics {
    public static void main(String[] args) {
        // Declaring variables
        String firstName = "Alice";
        String lastName = "Johnson";
        int age = 28;
        double height = 1.75;
        boolean isStudent = true;
        
        // String concatenation
        String fullName = firstName + " " + lastName;
        System.out.println("Full Name: " + fullName);
        
        // Arithmetic operations
        int birthYear = 2024 - age;
        System.out.println("Birth Year: " + birthYear);
        
        // Type conversion
        int heightCm = (int)(height * 100);
        System.out.println("Height in cm: " + heightCm);
        
        // Boolean operations
        System.out.println("Is student: " + isStudent);
    }
}
```

### Example 2: Temperature Converter
```java
public class TemperatureConverter {
    public static void main(String[] args) {
        double celsius = 25.0;
        
        // Convert to Fahrenheit
        double fahrenheit = (celsius * 9/5) + 32;
        
        // Convert to Kelvin
        double kelvin = celsius + 273.15;
        
        System.out.println("Temperature Conversions:");
        System.out.println("Celsius: " + celsius + "°C");
        System.out.println("Fahrenheit: " + fahrenheit + "°F");
        System.out.println("Kelvin: " + kelvin + "K");
        
        // Round to 2 decimal places
        fahrenheit = Math.round(fahrenheit * 100.0) / 100.0;
        kelvin = Math.round(kelvin * 100.0) / 100.0;
        
        System.out.println("\nRounded values:");
        System.out.println("Fahrenheit: " + fahrenheit + "°F");
        System.out.println("Kelvin: " + kelvin + "K");
    }
}
```

### Example 3: Simple Calculator
```java
public class SimpleCalculator {
    public static void main(String[] args) {
        double num1 = 15.5;
        double num2 = 4.2;
        
        // Basic operations
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        double quotient = num1 / num2;
        double remainder = num1 % num2;
        
        System.out.println("Calculator Results:");
        System.out.println(num1 + " + " + num2 + " = " + sum);
        System.out.println(num1 + " - " + num2 + " = " + difference);
        System.out.println(num1 + " * " + num2 + " = " + product);
        System.out.println(num1 + " / " + num2 + " = " + quotient);
        System.out.println(num1 + " % " + num2 + " = " + remainder);
        
        // Comparison operations
        System.out.println("\nComparisons:");
        System.out.println(num1 + " > " + num2 + " is " + (num1 > num2));
        System.out.println(num1 + " == " + num2 + " is " + (num1 == num2));
        System.out.println(num1 + " != " + num2 + " is " + (num1 != num2));
    }
}
```

## 🏋️ Exercises

### Exercise 1: Personal Information
Create a program that stores and displays your personal information:
- Name (String)
- Age (int)
- Height in meters (double)
- Weight in kg (double)
- Student status (boolean)

Calculate and display:
- Your BMI (Body Mass Index = weight / height²)
- Your age in months
- Your height in centimeters

### Exercise 2: Grade Calculator
Create a program that calculates a student's final grade:
- Store scores for 4 assignments (0-100)
- Store the final exam score (0-100)
- Calculate the final grade (assignments 60%, final exam 40%)
- Display the letter grade (A: 90+, B: 80-89, C: 70-79, D: 60-69, F: <60)

### Exercise 3: Currency Converter
Create a currency converter that:
- Converts between USD, EUR, and JPY
- Uses current exchange rates (you can use approximate values)
- Handles different amounts
- Displays results with proper formatting

## 🔧 Practice Files

### PracticeFile1.java
```java
public class PracticeFile1 {
    public static void main(String[] args) {
        // TODO: Declare variables for a book
        // - title (String)
        // - author (String)
        // - pages (int)
        // - price (double)
        // - isAvailable (boolean)
        
        // TODO: Initialize the variables with appropriate values
        
        // TODO: Display book information in a formatted way
        
        // TODO: Calculate and display the price per page
    }
}
```

### PracticeFile2.java
```java
public class PracticeFile2 {
    public static void main(String[] args) {
        // TODO: Create a simple interest calculator
        // - principal amount (double)
        // - rate of interest (double)
        // - time in years (int)
        
        // TODO: Calculate simple interest (P * R * T / 100)
        
        // TODO: Calculate total amount (principal + interest)
        
        // TODO: Display all calculations with proper formatting
    }
}
```

## 📝 Quiz Questions

1. What is the default value of an `int` variable?
2. What is the difference between `++x` and `x++`?
3. Which data type would you use to store a person's age?
4. What happens when you cast a `double` to an `int`?
5. What is the result of `5 / 2` in Java?
6. What is the result of `5.0 / 2` in Java?
7. What is the difference between `==` and `=`?
8. What is the size of a `char` in Java?
9. Can you assign a `long` value to an `int` variable without casting?
10. What is the purpose of the `final` keyword when used with variables?

## 🎯 Key Takeaways

- Java is a strongly-typed language
- Variables must be declared before use
- Primitive types store values directly
- Reference types store memory addresses
- Operators follow precedence rules
- Type conversion can be implicit or explicit
- Always use meaningful variable names

## 📚 Additional Resources

- [Java Data Types](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [Java Operators](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html)
- [Java Naming Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)

## 🚀 Next Steps

In the next lesson, we'll explore control flow statements (if-else, loops) to make our programs more dynamic and interactive.