# Lesson 7: Exception Handling

## Learning Objectives
- Understand the concept of exceptions and error handling
- Learn about checked vs unchecked exceptions
- Master try-catch blocks and exception propagation
- Understand modern exception handling with try-with-resources
- Practice creating custom exceptions and handling complex scenarios

## What are Exceptions?
Exceptions are events that occur during the execution of a program that disrupt the normal flow of instructions. They represent error conditions or unexpected situations that need to be handled.

## Exception Hierarchy

### Java Exception Classes
```
Throwable
├── Error (unchecked)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
└── Exception (checked)
    ├── RuntimeException (unchecked)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── ClassCastException
    │   ├── IllegalArgumentException
    │   └── NumberFormatException
    └── IOException (checked)
        ├── FileNotFoundException
        ├── EOFException
        └── InterruptedIOException
```

## Types of Exceptions

### Checked Exceptions
Checked exceptions must be handled at compile time. They represent recoverable conditions.

```java
import java.io.FileReader;
import java.io.IOException;

public class CheckedExceptionDemo {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("nonexistent.txt");
            int character = reader.read();
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
```

### Unchecked Exceptions
Unchecked exceptions (RuntimeException and its subclasses) don't need to be explicitly handled.

```java
public class UncheckedExceptionDemo {
    public static void main(String[] args) {
        // These exceptions can occur but don't require explicit handling
        String str = null;
        try {
            int length = str.length(); // NullPointerException
        } catch (NullPointerException e) {
            System.err.println("String is null: " + e.getMessage());
        }
        
        int[] array = {1, 2, 3};
        try {
            int value = array[10]; // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid array index: " + e.getMessage());
        }
    }
}
```

## Basic Exception Handling

### Try-Catch Block
```java
public class BasicExceptionHandling {
    public static void main(String[] args) {
        try {
            // Code that might throw an exception
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            // Handle the exception
            System.err.println("Division by zero: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other exceptions
            System.err.println("Unexpected error: " + e.getMessage());
        } finally {
            // Always executed, regardless of exception
            System.out.println("Cleanup code executed");
        }
    }
    
    public static int divide(int a, int b) {
        return a / b;
    }
}
```

### Multiple Exception Handling
```java
public class MultipleExceptionHandling {
    public static void main(String[] args) {
        try {
            // Multiple operations that might throw different exceptions
            String input = "abc";
            int number = Integer.parseInt(input);
            int[] array = {1, 2, 3};
            int value = array[number];
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Array index out of bounds: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
```

## Modern Exception Handling

### Try-With-Resources (Java 7+)
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesDemo {
    public static void main(String[] args) {
        // Automatic resource management
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        // Resources are automatically closed
    }
    
    // Multiple resources
    public static void copyFile(String source, String destination) {
        try (FileReader reader = new FileReader(source);
             FileWriter writer = new FileWriter(destination)) {
            
            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);
            }
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }
}
```

### Multi-Catch (Java 7+)
```java
public class MultiCatchDemo {
    public static void main(String[] args) {
        try {
            // Code that might throw multiple types of exceptions
            String input = "abc";
            int number = Integer.parseInt(input);
            int[] array = {1, 2, 3};
            int value = array[number];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Handle both exceptions with the same code
            System.err.println("Input error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
```

## Exception Propagation

### Method-Level Exception Handling
```java
public class ExceptionPropagation {
    public static void main(String[] args) {
        try {
            processData("invalid");
        } catch (CustomException e) {
            System.err.println("Custom exception caught: " + e.getMessage());
        }
    }
    
    public static void processData(String data) throws CustomException {
        if (data == null || data.isEmpty()) {
            throw new CustomException("Data cannot be null or empty");
        }
        
        try {
            int number = Integer.parseInt(data);
            System.out.println("Processed number: " + number);
        } catch (NumberFormatException e) {
            // Re-throw as custom exception
            throw new CustomException("Invalid number format: " + data, e);
        }
    }
}

// Custom exception class
class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
    
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

## Custom Exceptions

### Creating Custom Exception Classes
```java
public class CustomExceptions {
    
    // Custom checked exception
    public static class InsufficientFundsException extends Exception {
        private double balance;
        private double amount;
        
        public InsufficientFundsException(double balance, double amount) {
            super(String.format("Insufficient funds. Balance: %.2f, Required: %.2f", balance, amount));
            this.balance = balance;
            this.amount = amount;
        }
        
        public double getBalance() { return balance; }
        public double getAmount() { return amount; }
    }
    
    // Custom unchecked exception
    public static class InvalidAgeException extends RuntimeException {
        private int age;
        
        public InvalidAgeException(int age) {
            super("Invalid age: " + age + ". Age must be between 0 and 150.");
            this.age = age;
        }
        
        public int getAge() { return age; }
    }
    
    // Business logic with custom exceptions
    public static class BankAccount {
        private double balance;
        
        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }
        
        public void withdraw(double amount) throws InsufficientFundsException {
            if (amount > balance) {
                throw new InsufficientFundsException(balance, amount);
            }
            balance -= amount;
        }
        
        public void deposit(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Deposit amount cannot be negative");
            }
            balance += amount;
        }
        
        public double getBalance() {
            return balance;
        }
    }
    
    public static class Person {
        private String name;
        private int age;
        
        public Person(String name, int age) {
            if (age < 0 || age > 150) {
                throw new InvalidAgeException(age);
            }
            this.name = name;
            this.age = age;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
    }
}
```

## Advanced Exception Handling

### Exception Chaining
```java
public class ExceptionChaining {
    public static void main(String[] args) {
        try {
            processUserInput("invalid");
        } catch (ApplicationException e) {
            System.err.println("Application error: " + e.getMessage());
            System.err.println("Root cause: " + e.getCause().getMessage());
            e.printStackTrace();
        }
    }
    
    public static void processUserInput(String input) throws ApplicationException {
        try {
            validateInput(input);
            int number = parseInput(input);
            processNumber(number);
        } catch (NumberFormatException e) {
            throw new ApplicationException("Failed to process user input", e);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Invalid input provided", e);
        }
    }
    
    private static void validateInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
    }
    
    private static int parseInput(String input) {
        return Integer.parseInt(input);
    }
    
    private static void processNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        System.out.println("Processing number: " + number);
    }
}

class ApplicationException extends Exception {
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### Resource Management
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ResourceManagement {
    
    public static class DatabaseConnection implements AutoCloseable {
        private Connection connection;
        
        public DatabaseConnection(String url, String username, String password) throws SQLException {
            this.connection = DriverManager.getConnection(url, username, password);
        }
        
        public void executeQuery(String query) throws SQLException {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(query);
            }
        }
        
        @Override
        public void close() throws SQLException {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    
    public static void main(String[] args) {
        // Using try-with-resources for custom resource
        try (DatabaseConnection db = new DatabaseConnection("jdbc:mysql://localhost:3306/test", "user", "pass")) {
            db.executeQuery("SELECT * FROM users");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
```

## Best Practices

### Exception Handling Best Practices
```java
public class ExceptionBestPractices {
    
    // 1. Don't catch and ignore exceptions
    public static void badPractice1() {
        try {
            riskyOperation();
        } catch (Exception e) {
            // BAD: Ignoring the exception
        }
    }
    
    // 2. Don't catch generic Exception unless necessary
    public static void badPractice2() {
        try {
            riskyOperation();
        } catch (Exception e) {
            // BAD: Too broad
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    // 3. Good practice: Catch specific exceptions
    public static void goodPractice1() {
        try {
            riskyOperation();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Null reference: " + e.getMessage());
        }
    }
    
    // 4. Good practice: Log exceptions properly
    public static void goodPractice2() {
        try {
            riskyOperation();
        } catch (Exception e) {
            // Log the full stack trace for debugging
            e.printStackTrace();
            // Or use a proper logging framework
            // logger.error("Error in riskyOperation", e);
        }
    }
    
    // 5. Good practice: Clean up resources
    public static void goodPractice3() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "user", "pass");
            // Use connection
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
    
    // 6. Better practice: Use try-with-resources
    public static void bestPractice() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "user", "pass")) {
            // Use connection
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
    
    private static void riskyOperation() {
        // Simulate risky operation
        if (Math.random() > 0.5) {
            throw new IllegalArgumentException("Random error");
        }
    }
}
```

## Real-World Examples

### File Processing with Exception Handling
```java
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileProcessor {
    
    public static class FileProcessingException extends Exception {
        public FileProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    public static List<String> readFileSafely(String filename) throws FileProcessingException {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (NoSuchFileException e) {
            throw new FileProcessingException("File not found: " + filename, e);
        } catch (IOException e) {
            throw new FileProcessingException("Error reading file: " + filename, e);
        }
        
        return lines;
    }
    
    public static void writeFileSafely(String filename, List<String> content) throws FileProcessingException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error writing file: " + filename, e);
        }
    }
    
    public static void processFiles(String inputFile, String outputFile) {
        try {
            List<String> lines = readFileSafely(inputFile);
            
            // Process lines
            List<String> processedLines = new ArrayList<>();
            for (String line : lines) {
                try {
                    String processed = processLine(line);
                    processedLines.add(processed);
                } catch (LineProcessingException e) {
                    System.err.println("Skipping invalid line: " + e.getMessage());
                    // Continue processing other lines
                }
            }
            
            writeFileSafely(outputFile, processedLines);
            System.out.println("File processing completed successfully");
            
        } catch (FileProcessingException e) {
            System.err.println("File processing failed: " + e.getMessage());
        }
    }
    
    private static String processLine(String line) throws LineProcessingException {
        if (line == null || line.trim().isEmpty()) {
            throw new LineProcessingException("Empty or null line");
        }
        
        try {
            // Simulate processing that might fail
            return line.toUpperCase().trim();
        } catch (Exception e) {
            throw new LineProcessingException("Error processing line: " + line, e);
        }
    }
    
    public static class LineProcessingException extends Exception {
        public LineProcessingException(String message) {
            super(message);
        }
        
        public LineProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
```

## Exercises

### Exercise 1: Basic Exception Handling
Create methods that handle common exceptions:
- Division by zero
- Array index out of bounds
- Null pointer exceptions
- Number format exceptions

### Exercise 2: Custom Exceptions
Create custom exceptions for:
- Invalid email format
- Insufficient permissions
- Data validation errors
- Business rule violations

### Exercise 3: Resource Management
Implement proper resource management for:
- Database connections
- File operations
- Network connections
- Custom resources

### Exercise 4: Exception Propagation
Create a multi-layer application with proper exception propagation:
- Data access layer
- Business logic layer
- Presentation layer
- Proper exception translation between layers

## Practice Files
- `ExceptionHandlingDemo.java` - Basic exception handling examples
- `CustomExceptionsDemo.java` - Custom exception examples
- `ResourceManagementDemo.java` - Resource management examples
- `ExceptionBestPractices.java` - Best practices examples
- `ExceptionExercises.java` - Practice problems

## Key Takeaways
1. Always handle checked exceptions explicitly
2. Use try-with-resources for automatic resource management
3. Catch specific exceptions rather than generic Exception
4. Create meaningful custom exceptions for business logic
5. Properly log exceptions for debugging
6. Don't ignore exceptions - handle them appropriately
7. Use exception chaining to preserve the original cause
8. Clean up resources in finally blocks or use try-with-resources

## Next Steps
After completing this lesson, move on to:
- File I/O operations
- Streams API
- Generics
- Concurrency and threading

## Additional Resources
- [Java Exception Handling Tutorial](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Exception Handling Best Practices](https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html)