/**
 * Exercises.java
 * Practice exercises for Java variables and data types
 */
public class Exercises {
    public static void main(String[] args) {
        System.out.println("=== Java Variables Exercises ===\n");
        
        // Exercise 1: Variable Declaration
        System.out.println("Exercise 1: Variable Declaration");
        System.out.println("Create variables for personal information:\n");
        
        // TODO: Declare variables for your personal information
        // Replace the placeholder values with your actual information
        
        int myAge = 25;                    // Your age
        double myHeight = 1.75;            // Your height in meters
        char myFavoriteLetter = 'J';       // Your favorite letter
        boolean iLikeProgramming = true;   // Whether you like programming
        int currentYear = 2024;            // Current year
        
        System.out.println("My age: " + myAge);
        System.out.println("My height: " + myHeight + " meters");
        System.out.println("My favorite letter: " + myFavoriteLetter);
        System.out.println("I like programming: " + iLikeProgramming);
        System.out.println("Current year: " + currentYear);
        
        // Exercise 2: Type Conversion
        System.out.println("\nExercise 2: Type Conversion");
        System.out.println("Practice implicit and explicit casting:\n");
        
        // TODO: Practice type conversions
        int originalInt = 42;
        double intToDouble = originalInt;  // Implicit casting
        int doubleToInt = (int) 42.7;      // Explicit casting
        char charToInt = 'A';
        int asciiValue = charToInt;        // Implicit casting
        
        System.out.println("Original int: " + originalInt);
        System.out.println("Int to double: " + intToDouble);
        System.out.println("Double to int: " + doubleToInt);
        System.out.println("Character 'A' ASCII value: " + asciiValue);
        
        // Exercise 3: Calculations
        System.out.println("\nExercise 3: Calculations");
        System.out.println("Perform various calculations:\n");
        
        // TODO: Perform calculations
        double radius = 5.0;
        double pi = 3.14159;
        double circleArea = pi * radius * radius;
        
        double celsius = 25.0;
        double fahrenheit = (celsius * 9/5) + 32;
        
        int ageInYears = 25;
        int ageInDays = ageInYears * 365;
        
        System.out.println("Circle with radius " + radius + " has area: " + circleArea);
        System.out.println(celsius + "°C = " + fahrenheit + "°F");
        System.out.println("Age " + ageInYears + " years = " + ageInDays + " days");
        
        // Exercise 4: String Manipulation
        System.out.println("\nExercise 4: String Manipulation");
        System.out.println("Work with String variables:\n");
        
        // TODO: String operations
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;
        
        int birthYear = 1999;
        String birthYearString = String.valueOf(birthYear);
        
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Full name: " + fullName);
        System.out.println("Birth year as string: " + birthYearString);
        
        // Exercise 5: Mixed Operations
        System.out.println("\nExercise 5: Mixed Operations");
        System.out.println("Combine different data types:\n");
        
        // TODO: Mixed type operations
        String productName = "Laptop";
        double price = 999.99;
        int quantity = 2;
        double totalCost = price * quantity;
        
        System.out.println("Product: " + productName);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total cost: $" + totalCost);
        
        // Exercise 6: Challenge Problem
        System.out.println("\nExercise 6: Challenge Problem");
        System.out.println("Calculate compound interest:\n");
        
        // TODO: Compound interest calculation
        double principal = 1000.0;     // Initial amount
        double rate = 0.05;            // 5% interest rate
        int time = 3;                  // 3 years
        double compoundInterest = principal * Math.pow(1 + rate, time) - principal;
        double finalAmount = principal + compoundInterest;
        
        System.out.println("Principal: $" + principal);
        System.out.println("Interest rate: " + (rate * 100) + "%");
        System.out.println("Time: " + time + " years");
        System.out.println("Compound interest: $" + compoundInterest);
        System.out.println("Final amount: $" + finalAmount);
        
        System.out.println("\n=== Exercises Complete ===");
        System.out.println("Great job! You've practiced:");
        System.out.println("✓ Variable declaration and initialization");
        System.out.println("✓ Type conversion and casting");
        System.out.println("✓ Mathematical calculations");
        System.out.println("✓ String manipulation");
        System.out.println("✓ Mixed type operations");
        System.out.println("✓ Complex problem solving");
    }
}