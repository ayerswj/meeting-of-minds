/**
 * LoopsDemo.java
 * Demonstrates different types of loops in Java
 */
public class LoopsDemo {
    public static void main(String[] args) {
        System.out.println("=== Java Loops Demo ===\n");
        
        // 1. For Loop
        System.out.println("1. For Loop - Counting from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Count: " + i);
        }
        
        // 2. For Loop with Different Step
        System.out.println("\n2. For Loop - Counting by 2s:");
        for (int i = 0; i <= 10; i += 2) {
            System.out.println("Even number: " + i);
        }
        
        // 3. For Loop Counting Down
        System.out.println("\n3. For Loop - Counting Down:");
        for (int i = 5; i >= 1; i--) {
            System.out.println("Countdown: " + i);
        }
        System.out.println("Blast off!");
        
        // 4. While Loop
        System.out.println("\n4. While Loop - Guessing Game:");
        int secretNumber = 7;
        int guess = 1;
        
        while (guess != secretNumber) {
            System.out.println("Guess: " + guess + " (Wrong!)");
            guess++;
        }
        System.out.println("Guess: " + guess + " (Correct!)");
        
        // 5. Do-While Loop
        System.out.println("\n5. Do-While Loop - Menu Example:");
        int choice = 0;
        do {
            System.out.println("Menu:");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Exit");
            System.out.println("Current choice: " + choice);
            choice++;
        } while (choice <= 3);
        
        // 6. Nested Loops
        System.out.println("\n6. Nested Loops - Multiplication Table:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println(); // New line after each row
        }
        
        // 7. Enhanced For Loop (For-Each)
        System.out.println("\n7. Enhanced For Loop - Array Iteration:");
        String[] fruits = {"Apple", "Banana", "Orange", "Grape"};
        
        for (String fruit : fruits) {
            System.out.println("I like " + fruit);
        }
        
        // 8. Break Statement
        System.out.println("\n8. Break Statement - Finding First Even Number:");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println("First even number found: " + i);
                break; // Exit the loop
            }
        }
        
        // 9. Continue Statement
        System.out.println("\n9. Continue Statement - Skipping Odd Numbers:");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                continue; // Skip odd numbers
            }
            System.out.println("Even number: " + i);
        }
        
        // 10. Loop with User Input Simulation
        System.out.println("\n10. Loop with Condition - Password Attempts:");
        String correctPassword = "secret123";
        String[] attempts = {"wrong1", "wrong2", "secret123", "wrong3"};
        int attemptsCount = 0;
        
        for (String attempt : attempts) {
            attemptsCount++;
            if (attempt.equals(correctPassword)) {
                System.out.println("Password correct on attempt " + attemptsCount);
                break;
            } else {
                System.out.println("Attempt " + attemptsCount + " failed");
            }
        }
        
        // 11. Practical Example: Number Statistics
        System.out.println("\n11. Practical Example: Number Statistics");
        int[] numbers = {12, 5, 8, 15, 3, 9, 7, 20, 1, 14};
        int sum = 0;
        int max = numbers[0];
        int min = numbers[0];
        
        for (int number : numbers) {
            sum += number;
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
        }
        
        double average = (double) sum / numbers.length;
        
        System.out.println("Numbers: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
        
        // 12. Pattern Printing
        System.out.println("\n12. Pattern Printing - Triangle:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        // 13. While Loop with Complex Condition
        System.out.println("\n13. While Loop - Fibonacci Sequence:");
        int n = 10;
        int first = 0, second = 1;
        int count = 0;
        
        System.out.print("Fibonacci sequence: ");
        while (count < n) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
            count++;
        }
        System.out.println();
        
        // 14. Loop Performance Example
        System.out.println("\n14. Loop Performance - Finding Prime Numbers:");
        System.out.print("Prime numbers up to 20: ");
        for (int i = 2; i <= 20; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        
        System.out.println("\n=== Loops Demo Complete ===");
    }
}