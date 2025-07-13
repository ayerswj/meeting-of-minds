/**
 * MethodsDemo.java
 * Demonstrates various types of methods in Java
 */
public class MethodsDemo {
    public static void main(String[] args) {
        System.out.println("=== Java Methods Demo ===\n");
        
        // Create instances of our classes
        Calculator calc = new Calculator();
        StringUtils stringUtils = new StringUtils();
        MathUtils mathUtils = new MathUtils();
        
        // 1. Basic Calculator Methods
        System.out.println("1. Basic Calculator Methods:");
        int sum = calc.add(10, 5);
        int difference = calc.subtract(10, 5);
        int product = calc.multiply(10, 5);
        double quotient = calc.divide(10.0, 5.0);
        
        calc.displayResult("10 + 5", sum);
        calc.displayResult("10 - 5", difference);
        calc.displayResult("10 * 5", product);
        calc.displayResult("10 / 5", quotient);
        
        // 2. String Utility Methods
        System.out.println("\n2. String Utility Methods:");
        String testString = "Hello World";
        String reversed = stringUtils.reverse(testString);
        boolean isPalindrome = stringUtils.isPalindrome("racecar");
        int vowelCount = stringUtils.countVowels(testString);
        String capitalized = stringUtils.capitalize("hello world");
        
        System.out.println("Original: " + testString);
        System.out.println("Reversed: " + reversed);
        System.out.println("Is 'racecar' palindrome: " + isPalindrome);
        System.out.println("Vowels in '" + testString + "': " + vowelCount);
        System.out.println("Capitalized: " + capitalized);
        
        // 3. Method Overloading
        System.out.println("\n3. Method Overloading:");
        int sum2 = mathUtils.add(5, 10);
        int sum3 = mathUtils.add(5, 10, 15);
        double sumDouble = mathUtils.add(5.5, 10.5);
        
        int[] numbers = {1, 2, 3, 4, 5};
        int arraySum = mathUtils.add(numbers);
        
        System.out.println("Add 2 ints: " + sum2);
        System.out.println("Add 3 ints: " + sum3);
        System.out.println("Add 2 doubles: " + sumDouble);
        System.out.println("Add array: " + arraySum);
        
        // 4. Static Methods
        System.out.println("\n4. Static Methods:");
        double circleArea = MathHelper.calculateCircleArea(5.0);
        double circleCircumference = MathHelper.calculateCircleCircumference(5.0);
        int factorial = MathHelper.factorial(5);
        boolean isPrime = MathHelper.isPrime(17);
        
        System.out.println("Circle area (radius 5): " + circleArea);
        System.out.println("Circle circumference (radius 5): " + circleCircumference);
        System.out.println("Factorial of 5: " + factorial);
        System.out.println("Is 17 prime: " + isPrime);
        
        // 5. Recursive Methods
        System.out.println("\n5. Recursive Methods:");
        int factorialRecursive = RecursionExamples.factorial(6);
        int fibonacci = RecursionExamples.fibonacci(8);
        double power = RecursionExamples.power(2, 8);
        String reversedRecursive = RecursionExamples.reverseString("Java");
        
        System.out.println("Factorial of 6 (recursive): " + factorialRecursive);
        System.out.println("8th Fibonacci number: " + fibonacci);
        System.out.println("2^8: " + power);
        System.out.println("Reverse 'Java': " + reversedRecursive);
        
        // 6. Parameter Examples
        System.out.println("\n6. Parameter Examples:");
        ParameterExamples paramExamples = new ParameterExamples();
        
        int originalNumber = 50;
        System.out.println("Original number: " + originalNumber);
        paramExamples.modifyPrimitive(originalNumber);
        System.out.println("After method call: " + originalNumber);
        
        int[] originalArray = {1, 2, 3, 4, 5};
        System.out.println("Original array[0]: " + originalArray[0]);
        paramExamples.modifyArray(originalArray);
        System.out.println("After method call: " + originalArray[0]);
        
        // 7. Variable Arguments (Varargs)
        System.out.println("\n7. Variable Arguments (Varargs):");
        int sumVarargs = paramExamples.sum(1, 2, 3, 4, 5);
        System.out.println("Sum of 1,2,3,4,5: " + sumVarargs);
        
        paramExamples.printInfo("John Doe", 25, "Reading", "Gaming", "Cooking");
        
        // 8. Utility Methods
        System.out.println("\n8. Utility Methods:");
        int[] testArray = {3, 7, 2, 9, 1, 8, 5};
        
        int max = UtilityMethods.findMax(testArray);
        int min = UtilityMethods.findMin(testArray);
        double average = UtilityMethods.calculateAverage(testArray);
        boolean sorted = UtilityMethods.isSorted(testArray);
        
        System.out.println("Array: ");
        for (int num : testArray) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Average: " + average);
        System.out.println("Is sorted: " + sorted);
        
        // 9. Random Number Generation
        System.out.println("\n9. Random Number Generation:");
        for (int i = 0; i < 5; i++) {
            int randomNum = UtilityMethods.randomInRange(1, 100);
            System.out.println("Random number (1-100): " + randomNum);
        }
        
        System.out.println("\n=== Methods Demo Complete ===");
    }
}

// Supporting classes for the demo
class Calculator {
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

class StringUtils {
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

class MathUtils {
    public int add(int a, int b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
}

class MathHelper {
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

class RecursionExamples {
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        return base * power(base, exponent - 1);
    }
    
    public static String reverseString(String str) {
        if (str.length() <= 1) {
            return str;
        }
        return str.charAt(str.length() - 1) + reverseString(str.substring(0, str.length() - 1));
    }
}

class ParameterExamples {
    public void modifyPrimitive(int number) {
        number = 100;
        System.out.println("Inside method: " + number);
    }
    
    public void modifyArray(int[] array) {
        array[0] = 100;
        System.out.println("Inside method: " + array[0]);
    }
    
    public int sum(int... numbers) {
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }
    
    public void printInfo(String name, int age, String... hobbies) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Hobbies:");
        for (String hobby : hobbies) {
            System.out.println("- " + hobby);
        }
    }
}

class UtilityMethods {
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
    
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static int randomInRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}