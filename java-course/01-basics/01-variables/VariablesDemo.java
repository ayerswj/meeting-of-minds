/**
 * VariablesDemo.java
 * Demonstrates basic variable declaration and usage in Java
 */
public class VariablesDemo {
    public static void main(String[] args) {
        System.out.println("=== Java Variables Demo ===\n");
        
        // 1. Integer Variables
        System.out.println("1. Integer Variables:");
        int age = 25;
        int temperature = -5;
        long worldPopulation = 7800000000L;
        
        System.out.println("Age: " + age);
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("World Population: " + worldPopulation);
        
        // 2. Floating-Point Variables
        System.out.println("\n2. Floating-Point Variables:");
        double price = 19.99;
        float pi = 3.14f;
        
        System.out.println("Price: $" + price);
        System.out.println("Pi: " + pi);
        
        // 3. Character Variables
        System.out.println("\n3. Character Variables:");
        char grade = 'A';
        char symbol = '@';
        
        System.out.println("Grade: " + grade);
        System.out.println("Symbol: " + symbol);
        
        // 4. Boolean Variables
        System.out.println("\n4. Boolean Variables:");
        boolean isStudent = true;
        boolean isWorking = false;
        
        System.out.println("Is Student: " + isStudent);
        System.out.println("Is Working: " + isWorking);
        
        // 5. Multiple Variable Declaration
        System.out.println("\n5. Multiple Variable Declaration:");
        int x = 10, y = 20, z = 30;
        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
        
        // 6. Variable Reassignment
        System.out.println("\n6. Variable Reassignment:");
        int count = 5;
        System.out.println("Initial count: " + count);
        
        count = 10;
        System.out.println("After reassignment: " + count);
        
        // 7. Using Variables in Calculations
        System.out.println("\n7. Using Variables in Calculations:");
        int length = 10;
        int width = 5;
        int area = length * width;
        
        System.out.println("Length: " + length);
        System.out.println("Width: " + width);
        System.out.println("Area: " + area);
        
        // 8. String Variables (Reference Type)
        System.out.println("\n8. String Variables:");
        String name = "John Doe";
        String message = "Hello, " + name + "!";
        
        System.out.println("Name: " + name);
        System.out.println("Message: " + message);
        
        System.out.println("\n=== Demo Complete ===");
    }
}