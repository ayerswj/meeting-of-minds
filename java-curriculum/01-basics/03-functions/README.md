# Lesson 3: Functions and Methods

## Learning Objectives
- Understand the concept of functions and methods in Java
- Learn how to create and call methods
- Master method parameters and return values
- Understand method overloading
- Practice creating utility methods and organizing code

## What are Methods?
Methods are blocks of code that perform specific tasks. They help organize code, make it reusable, and improve readability. In Java, methods are always defined within classes.

## Method Structure

### Basic Method Syntax
```java
accessModifier returnType methodName(parameterList) {
    // method body
    return value; // if returnType is not void
}
```

### Method Components
- **Access Modifier**: Controls who can access the method (public, private, protected, default)
- **Return Type**: The data type of the value the method returns (void if no return)
- **Method Name**: Identifier for the method (camelCase convention)
- **Parameters**: Input values the method needs (optional)
- **Method Body**: The actual code that executes
- **Return Statement**: Sends a value back to the caller (except for void methods)

## Creating Simple Methods

### Method with No Parameters and No Return Value
```java
public void greet() {
    System.out.println("Hello, World!");
}
```

### Method with Parameters
```java
public void greetPerson(String name) {
    System.out.println("Hello, " + name + "!");
}
```

### Method with Return Value
```java
public int add(int a, int b) {
    return a + b;
}
```

### Method with Multiple Parameters
```java
public double calculateArea(double length, double width) {
    return length * width;
}
```

## Method Examples

### Basic Calculator Methods
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero!");
            return 0;
        }
        return a / b;
    }
    
    public void displayResult(String operation, double result) {
        System.out.println(operation + " = " + result);
    }
}
```

### String Utility Methods
```java
public class StringUtils {
    public String reverse(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }
    
    public boolean isPalindrome(String str) {
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(reverse(cleaned));
    }
    
    public int countVowels(String str) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : str.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
    
    public String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
```

## Method Overloading

Method overloading allows you to define multiple methods with the same name but different parameters.

### Overloading Examples
```java
public class MathUtils {
    // Add two integers
    public int add(int a, int b) {
        return a + b;
    }
    
    // Add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Add two doubles
    public double add(double a, double b) {
        return a + b;
    }
    
    // Add an array of integers
    public int add(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
}
```

### Constructor Overloading
```java
public class Rectangle {
    private double length;
    private double width;
    
    // Default constructor
    public Rectangle() {
        this.length = 1.0;
        this.width = 1.0;
    }
    
    // Constructor with one parameter (square)
    public Rectangle(double side) {
        this.length = side;
        this.width = side;
    }
    
    // Constructor with two parameters
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    public double getArea() {
        return length * width;
    }
    
    public double getPerimeter() {
        return 2 * (length + width);
    }
}
```

## Static Methods

Static methods belong to the class rather than instances of the class.

### Static Method Examples
```java
public class MathHelper {
    public static final double PI = 3.14159;
    
    public static double calculateCircleArea(double radius) {
        return PI * radius * radius;
    }
    
    public static double calculateCircleCircumference(double radius) {
        return 2 * PI * radius;
    }
    
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
```

## Recursive Methods

Recursive methods call themselves to solve problems.

### Recursion Examples
```java
public class RecursionExamples {
    // Calculate factorial recursively
    public static int factorial(int n) {
        if (n <= 1) {
            return 1; // Base case
        }
        return n * factorial(n - 1); // Recursive case
    }
    
    // Calculate Fibonacci number recursively
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n; // Base case
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // Recursive case
    }
    
    // Calculate power recursively
    public static double power(double base, int exponent) {
        if (exponent == 0) {
            return 1; // Base case
        }
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        return base * power(base, exponent - 1); // Recursive case
    }
    
    // Reverse string recursively
    public static String reverseString(String str) {
        if (str.length() <= 1) {
            return str; // Base case
        }
        return str.charAt(str.length() - 1) + reverseString(str.substring(0, str.length() - 1));
    }
}
```

## Method Parameters and Arguments

### Parameter Types
```java
public class ParameterExamples {
    // Primitive parameters (pass by value)
    public void modifyPrimitive(int number) {
        number = 100; // This doesn't affect the original value
        System.out.println("Inside method: " + number);
    }
    
    // Object parameters (pass by reference)
    public void modifyArray(int[] array) {
        array[0] = 100; // This affects the original array
        System.out.println("Inside method: " + array[0]);
    }
    
    // Variable number of arguments (varargs)
    public int sum(int... numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }
    
    // Method with mixed parameters
    public void printInfo(String name, int age, String... hobbies) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Hobbies:");
        for (String hobby : hobbies) {
            System.out.println("- " + hobby);
        }
    }
}
```

## Utility Methods

### Common Utility Methods
```java
public class UtilityMethods {
    // Find maximum value in array
    public static int findMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    
    // Find minimum value in array
    public static int findMin(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
    
    // Calculate average of array
    public static double calculateAverage(int[] array) {
        if (array.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return (double) sum / array.length;
    }
    
    // Check if array is sorted
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    // Generate random number in range
    public static int randomInRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
```

## Exercises

### Exercise 1: Basic Methods
Create methods to:
- Calculate the area of a circle
- Convert temperature from Celsius to Fahrenheit
- Check if a number is even or odd
- Find the greatest common divisor of two numbers

### Exercise 2: String Methods
Create methods to:
- Count the number of words in a string
- Remove all vowels from a string
- Check if a string contains only digits
- Convert a string to title case

### Exercise 3: Array Methods
Create methods to:
- Find the second largest number in an array
- Remove duplicates from an array
- Rotate an array by a given number of positions
- Check if two arrays are equal

### Exercise 4: Mathematical Methods
Create methods to:
- Calculate the sum of digits of a number
- Check if a number is a perfect square
- Generate the first n prime numbers
- Calculate the least common multiple of two numbers

## Practice Files
- `MethodsDemo.java` - Basic method examples
- `MethodOverloading.java` - Overloading examples
- `RecursionDemo.java` - Recursive method examples
- `UtilityMethods.java` - Common utility methods
- `MethodsExercises.java` - Practice problems

## Key Takeaways
1. Methods help organize and reuse code
2. Use meaningful method names that describe what the method does
3. Keep methods small and focused on a single task
4. Use method overloading for similar operations with different parameters
5. Static methods belong to the class, not instances
6. Recursive methods can solve complex problems elegantly
7. Always validate input parameters when necessary

## Next Steps
After completing this lesson, move on to:
- Arrays and collections
- Object-oriented programming
- Exception handling
- File I/O operations

## Additional Resources
- [Java Methods Tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Method Overloading](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html#overloadingMethods)