# Week 3: Functions and Methods

## 🎯 Learning Objectives
- Understand the concept of functions and methods
- Learn method declaration, parameters, and return types
- Master method overloading and recursion
- Practice variable scope and access modifiers
- Build modular and reusable code

## 📚 Theory

### What are Functions/Methods?
Functions (called methods in Java) are blocks of code that perform specific tasks. They help organize code, make it reusable, and improve readability.

### Method Declaration Syntax
```java
accessModifier returnType methodName(parameterList) {
    // Method body
    return value; // if returnType is not void
}
```

### Method Components

#### Access Modifiers
- `public`: Accessible from anywhere
- `private`: Accessible only within the class
- `protected`: Accessible within package and subclasses
- `default` (no modifier): Accessible within package only

#### Return Types
- `void`: Method doesn't return anything
- `int`, `double`, `String`, etc.: Method returns a value of that type
- Any class type: Method returns an object of that class

#### Parameters
- Input values that the method receives
- Can be primitive types or objects
- Multiple parameters separated by commas
- Parameters are local variables within the method

### Method Types

#### Void Methods (No Return Value)
```java
public void printMessage(String message) {
    System.out.println("Message: " + message);
}
```

#### Methods with Return Values
```java
public int add(int a, int b) {
    return a + b;
}

public String getFullName(String firstName, String lastName) {
    return firstName + " " + lastName;
}
```

#### Static Methods
```java
public static double calculateArea(double radius) {
    return Math.PI * radius * radius;
}
```

### Method Overloading
Multiple methods with the same name but different parameters:

```java
public int add(int a, int b) {
    return a + b;
}

public int add(int a, int b, int c) {
    return a + b + c;
}

public double add(double a, double b) {
    return a + b;
}
```

### Recursion
A method calling itself:

```java
public int factorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}
```

### Variable Scope
- **Local variables**: Declared inside a method, accessible only within that method
- **Instance variables**: Declared in a class, accessible throughout the class
- **Static variables**: Shared across all instances of a class

## 💻 Practice Examples

### Example 1: Basic Calculator Methods
```java
public class Calculator {
    
    // Addition method
    public static int add(int a, int b) {
        return a + b;
    }
    
    // Subtraction method
    public static int subtract(int a, int b) {
        return a - b;
    }
    
    // Multiplication method
    public static int multiply(int a, int b) {
        return a * b;
    }
    
    // Division method with error handling
    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero!");
        }
        return a / b;
    }
    
    // Method overloading for different data types
    public static double add(double a, double b) {
        return a + b;
    }
    
    public static void main(String[] args) {
        System.out.println("Calculator Demo:");
        System.out.println("5 + 3 = " + add(5, 3));
        System.out.println("10 - 4 = " + subtract(10, 4));
        System.out.println("6 * 7 = " + multiply(6, 7));
        System.out.println("15 / 3 = " + divide(15, 3));
        System.out.println("5.5 + 3.2 = " + add(5.5, 3.2));
    }
}
```

### Example 2: String Utility Methods
```java
public class StringUtils {
    
    // Count vowels in a string
    public static int countVowels(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        String vowels = "aeiouAEIOU";
        
        for (char c : str.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
    
    // Reverse a string
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }
    
    // Check if string is palindrome
    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        
        String cleaned = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        return cleaned.equals(reverse(cleaned));
    }
    
    // Convert to title case
    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        
        String[] words = str.toLowerCase().split(" ");
        StringBuilder titleCase = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                titleCase.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        
        return titleCase.toString().trim();
    }
    
    public static void main(String[] args) {
        String testString = "Hello World!";
        
        System.out.println("Original: " + testString);
        System.out.println("Vowels: " + countVowels(testString));
        System.out.println("Reversed: " + reverse(testString));
        System.out.println("Is palindrome: " + isPalindrome("racecar"));
        System.out.println("Title case: " + toTitleCase("hello world java"));
    }
}
```

### Example 3: Array Utility Methods
```java
public class ArrayUtils {
    
    // Find maximum value in array
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    // Find minimum value in array
    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }
    
    // Calculate average of array elements
    public static double calculateAverage(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }
    
    // Sort array using bubble sort
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // Print array elements
    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("Array is null");
            return;
        }
        
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("Original array:");
        printArray(numbers);
        
        System.out.println("Maximum: " + findMax(numbers));
        System.out.println("Minimum: " + findMin(numbers));
        System.out.println("Average: " + calculateAverage(numbers));
        
        bubbleSort(numbers);
        System.out.println("Sorted array:");
        printArray(numbers);
    }
}
```

### Example 4: Recursive Methods
```java
public class RecursionExamples {
    
    // Calculate factorial recursively
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial not defined for negative numbers");
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    // Calculate Fibonacci number recursively
    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci not defined for negative numbers");
        }
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Calculate greatest common divisor recursively
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    // Check if number is prime recursively
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        return isPrimeHelper(n, 2);
    }
    
    private static boolean isPrimeHelper(int n, int divisor) {
        if (divisor * divisor > n) {
            return true;
        }
        if (n % divisor == 0) {
            return false;
        }
        return isPrimeHelper(n, divisor + 1);
    }
    
    public static void main(String[] args) {
        System.out.println("Recursion Examples:");
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Fibonacci(7): " + fibonacci(7));
        System.out.println("GCD of 48 and 18: " + gcd(48, 18));
        System.out.println("Is 17 prime? " + isPrime(17));
        System.out.println("Is 24 prime? " + isPrime(24));
    }
}
```

## 🏋️ Exercises

### Exercise 1: Math Utility Class
Create a class with methods for:
- Power calculation (both iterative and recursive)
- Square root approximation
- Prime number generation
- Number formatting (add commas to large numbers)

### Exercise 2: Date Utility Methods
Create methods to:
- Calculate days between two dates
- Add/subtract days from a date
- Check if a year is leap year
- Get day of week for a given date

### Exercise 3: Text Analysis Methods
Create methods to:
- Count words in a text
- Find most frequent word
- Check if text contains specific keywords
- Generate word frequency report

### Exercise 4: Number Game Methods
Create methods for a number game:
- Generate random number in range
- Check if guess is correct
- Provide hints (higher/lower)
- Calculate score based on attempts

## 🔧 Practice Files

### PracticeFile1.java
```java
public class PracticeFile1 {
    
    // TODO: Create a method to calculate compound interest
    // Parameters: principal, rate, time, compound frequency
    // Return: final amount
    
    // TODO: Create a method to convert temperature between scales
    // Parameters: temperature, from scale, to scale
    // Return: converted temperature
    
    // TODO: Create a method to find all factors of a number
    // Parameters: number
    // Return: array of factors
    
    public static void main(String[] args) {
        // Test your methods here
    }
}
```

### PracticeFile2.java
```java
public class PracticeFile2 {
    
    // TODO: Create a method to validate email format
    // Use regex pattern matching
    
    // TODO: Create a method to generate password
    // Parameters: length, include symbols, include numbers
    
    // TODO: Create a method to format phone number
    // Input: "1234567890" -> Output: "(123) 456-7890"
    
    public static void main(String[] args) {
        // Test your methods here
    }
}
```

## 📝 Quiz Questions

1. What is the difference between a void method and a method with a return type?
2. What is method overloading and when would you use it?
3. What is the difference between static and non-static methods?
4. What is recursion and what are its advantages/disadvantages?
5. What is the scope of a local variable?
6. What happens if you don't return a value from a non-void method?
7. What is the difference between parameters and arguments?
8. How do you handle exceptions in methods?
9. What is the purpose of the `this` keyword in methods?
10. When would you use a private method?

## 🎯 Key Takeaways

- Methods help organize and reuse code
- Choose appropriate return types and parameters
- Use method overloading for similar functionality with different inputs
- Be careful with recursion to avoid stack overflow
- Understand variable scope to avoid conflicts
- Use meaningful method names and documentation
- Handle exceptions appropriately

## 📚 Additional Resources

- [Java Methods Tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Method Overloading](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html#overloadingMethods)
- [Recursion in Java](https://www.geeksforgeeks.org/recursion-in-java/)

## 🚀 Next Steps

In the next lesson, we'll explore arrays and collections to work with multiple values efficiently.