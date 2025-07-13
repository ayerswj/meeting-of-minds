/**
 * ConditionalsDemo.java
 * Demonstrates conditional statements in Java
 */
public class ConditionalsDemo {
    public static void main(String[] args) {
        System.out.println("=== Java Conditionals Demo ===\n");
        
        // 1. Simple If Statement
        System.out.println("1. Simple If Statement:");
        int age = 18;
        
        if (age >= 18) {
            System.out.println("You are an adult.");
        }
        
        // 2. If-Else Statement
        System.out.println("\n2. If-Else Statement:");
        int temperature = 25;
        
        if (temperature > 30) {
            System.out.println("It's hot outside!");
        } else {
            System.out.println("It's not too hot.");
        }
        
        // 3. If-Else If-Else Statement
        System.out.println("\n3. If-Else If-Else Statement:");
        int score = 85;
        
        if (score >= 90) {
            System.out.println("Grade: A (Excellent!)");
        } else if (score >= 80) {
            System.out.println("Grade: B (Good job!)");
        } else if (score >= 70) {
            System.out.println("Grade: C (Satisfactory)");
        } else if (score >= 60) {
            System.out.println("Grade: D (Needs improvement)");
        } else {
            System.out.println("Grade: F (Failed)");
        }
        
        // 4. Nested If Statements
        System.out.println("\n4. Nested If Statements:");
        boolean isStudent = true;
        boolean hasID = true;
        
        if (isStudent) {
            if (hasID) {
                System.out.println("Student discount applied!");
            } else {
                System.out.println("Please show your student ID.");
            }
        } else {
            System.out.println("Regular price applies.");
        }
        
        // 5. Switch Statement
        System.out.println("\n5. Switch Statement:");
        int dayOfWeek = 3;
        
        switch (dayOfWeek) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
            case 7:
                System.out.println("Weekend!");
                break;
            default:
                System.out.println("Invalid day");
                break;
        }
        
        // 6. Switch with Strings (Java 7+)
        System.out.println("\n6. Switch with Strings:");
        String fruit = "apple";
        
        switch (fruit.toLowerCase()) {
            case "apple":
                System.out.println("An apple a day keeps the doctor away!");
                break;
            case "banana":
                System.out.println("Bananas are rich in potassium.");
                break;
            case "orange":
                System.out.println("Oranges are full of vitamin C.");
                break;
            default:
                System.out.println("I don't know much about " + fruit);
                break;
        }
        
        // 7. Logical Operators
        System.out.println("\n7. Logical Operators:");
        boolean isSunny = true;
        boolean isWarm = true;
        boolean isWeekend = false;
        
        // AND operator
        if (isSunny && isWarm) {
            System.out.println("Perfect weather for a picnic!");
        }
        
        // OR operator
        if (isSunny || isWeekend) {
            System.out.println("Good day to go outside!");
        }
        
        // NOT operator
        if (!isWeekend) {
            System.out.println("It's a weekday.");
        }
        
        // 8. Complex Conditions
        System.out.println("\n8. Complex Conditions:");
        int userAge = 25;
        boolean hasLicense = true;
        boolean hasInsurance = false;
        
        if (userAge >= 18 && hasLicense && hasInsurance) {
            System.out.println("You can drive legally.");
        } else if (userAge >= 18 && hasLicense && !hasInsurance) {
            System.out.println("You can drive but need insurance.");
        } else if (userAge >= 18 && !hasLicense) {
            System.out.println("You need a driver's license.");
        } else {
            System.out.println("You're too young to drive.");
        }
        
        // 9. Comparison Operators
        System.out.println("\n9. Comparison Operators:");
        int a = 10;
        int b = 20;
        
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a <= b: " + (a <= b));
        System.out.println("a >= b: " + (a >= b));
        
        // 10. Ternary Operator
        System.out.println("\n10. Ternary Operator:");
        int number = 7;
        String result = (number % 2 == 0) ? "Even" : "Odd";
        System.out.println(number + " is " + result);
        
        // 11. Practical Example: Discount Calculator
        System.out.println("\n11. Practical Example: Discount Calculator:");
        double purchaseAmount = 150.0;
        boolean isVIP = true;
        boolean isFirstTime = false;
        
        double discount = 0.0;
        
        if (isVIP) {
            discount = 0.20; // 20% discount for VIP
        } else if (isFirstTime) {
            discount = 0.10; // 10% discount for first-time customers
        } else if (purchaseAmount > 100) {
            discount = 0.05; // 5% discount for purchases over $100
        }
        
        double finalAmount = purchaseAmount * (1 - discount);
        System.out.println("Purchase amount: $" + purchaseAmount);
        System.out.println("Discount: " + (discount * 100) + "%");
        System.out.println("Final amount: $" + finalAmount);
        
        System.out.println("\n=== Conditionals Demo Complete ===");
    }
}