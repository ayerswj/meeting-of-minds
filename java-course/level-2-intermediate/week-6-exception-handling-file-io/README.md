# Week 6: Exception Handling and File I/O

## Learning Objectives
- Understand exception handling mechanisms in Java
- Master try-catch-finally blocks
- Learn to create custom exceptions
- Understand file I/O operations (reading/writing)
- Work with different file formats (text, binary)
- Apply exception handling in real-world scenarios

## Theory

### 1. Exception Handling

#### Exception Hierarchy
```
Throwable
├── Error (unchecked)
└── Exception
    ├── RuntimeException (unchecked)
    └── Checked Exceptions
        ├── IOException
        ├── SQLException
        └── ClassNotFoundException
```

#### Try-Catch-Finally Block
```java
try {
    // Code that might throw an exception
} catch (ExceptionType1 e1) {
    // Handle ExceptionType1
} catch (ExceptionType2 e2) {
    // Handle ExceptionType2
} finally {
    // Always executed (cleanup code)
}
```

#### Checked vs Unchecked Exceptions
- **Checked**: Must be handled or declared (IOException, SQLException)
- **Unchecked**: Don't need explicit handling (RuntimeException, NullPointerException)

### 2. File I/O

#### File Operations
- Reading and writing text files
- Binary file operations
- Directory operations
- File metadata

#### Streams
- **InputStream/OutputStream**: Binary data
- **Reader/Writer**: Character data
- **BufferedReader/BufferedWriter**: Efficient text processing

## Code Examples

### Example 1: Basic Exception Handling
```java
import java.util.Scanner;

public class ExceptionHandlingDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter a number: ");
            String input = scanner.nextLine();
            
            int number = Integer.parseInt(input);
            System.out.println("You entered: " + number);
            
            int result = 100 / number;
            System.out.println("100 / " + number + " = " + result);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
            System.out.println("Exception details: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("This block always executes.");
            scanner.close();
        }
    }
}
```

### Example 2: Custom Exception
```java
// Custom exception class
public class InsufficientFundsException extends Exception {
    private double amount;
    private double balance;
    
    public InsufficientFundsException(double amount, double balance) {
        super("Insufficient funds. Required: $" + amount + ", Available: $" + balance);
        this.amount = amount;
        this.balance = balance;
    }
    
    public double getAmount() { return amount; }
    public double getBalance() { return balance; }
}

// Bank account class using custom exception
public class BankAccount {
    private String accountNumber;
    private double balance;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("Deposited: $" + amount + ", New balance: $" + balance);
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        
        balance -= amount;
        System.out.println("Withdrawn: $" + amount + ", New balance: $" + balance);
    }
    
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
}

// Main class to demonstrate custom exceptions
public class CustomExceptionDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", 1000.0);
        
        try {
            account.deposit(500.0);
            account.withdraw(200.0);
            account.withdraw(2000.0); // This will throw InsufficientFundsException
            
        } catch (InsufficientFundsException e) {
            System.out.println("Bank Error: " + e.getMessage());
            System.out.println("Required: $" + e.getAmount());
            System.out.println("Available: $" + e.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
```

### Example 3: File Reading and Writing
```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIODemo {
    
    // Write text to file
    public static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Content written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Read text from file
    public static String readFromFile(String filename) {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return content.toString();
    }
    
    // Write list of strings to file
    public static void writeLinesToFile(String filename, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Lines written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Read lines from file into list
    public static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        return lines;
    }
    
    public static void main(String[] args) {
        // Write text to file
        writeToFile("sample.txt", "Hello, World!\nThis is a sample file.\nJava File I/O is awesome!");
        
        // Read text from file
        String content = readFromFile("sample.txt");
        System.out.println("File content:\n" + content);
        
        // Write list of lines
        List<String> lines = List.of(
            "Line 1: Java Programming",
            "Line 2: Exception Handling",
            "Line 3: File I/O Operations",
            "Line 4: Advanced Concepts"
        );
        writeLinesToFile("lines.txt", lines);
        
        // Read lines from file
        List<String> readLines = readLinesFromFile("lines.txt");
        System.out.println("Read lines:");
        for (String line : readLines) {
            System.out.println(line);
        }
    }
}
```

### Example 4: Binary File Operations
```java
import java.io.*;
import java.util.Arrays;

public class BinaryFileDemo {
    
    // Write integers to binary file
    public static void writeIntegersToFile(String filename, int[] numbers) {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(filename)))) {
            
            dos.writeInt(numbers.length); // Write array length
            for (int number : numbers) {
                dos.writeInt(number); // Write each integer
            }
            System.out.println("Integers written to " + filename);
            
        } catch (IOException e) {
            System.err.println("Error writing to binary file: " + e.getMessage());
        }
    }
    
    // Read integers from binary file
    public static int[] readIntegersFromFile(String filename) {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(filename)))) {
            
            int length = dis.readInt(); // Read array length
            int[] numbers = new int[length];
            
            for (int i = 0; i < length; i++) {
                numbers[i] = dis.readInt(); // Read each integer
            }
            
            System.out.println("Integers read from " + filename);
            return numbers;
            
        } catch (IOException e) {
            System.err.println("Error reading from binary file: " + e.getMessage());
            return new int[0];
        }
    }
    
    // Copy file
    public static void copyFile(String sourceFile, String destFile) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile))) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            
            System.out.println("File copied from " + sourceFile + " to " + destFile);
            
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        // Write integers to binary file
        int[] numbers = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        writeIntegersToFile("numbers.bin", numbers);
        
        // Read integers from binary file
        int[] readNumbers = readIntegersFromFile("numbers.bin");
        System.out.println("Read numbers: " + Arrays.toString(readNumbers));
        
        // Copy text file
        copyFile("sample.txt", "sample_copy.txt");
    }
}
```

### Example 5: File and Directory Operations
```java
import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileOperationsDemo {
    
    // Check if file exists
    public static boolean fileExists(String filename) {
        return Files.exists(Paths.get(filename));
    }
    
    // Get file information
    public static void getFileInfo(String filename) {
        try {
            Path path = Paths.get(filename);
            if (Files.exists(path)) {
                System.out.println("File: " + filename);
                System.out.println("Size: " + Files.size(path) + " bytes");
                System.out.println("Last modified: " + Files.getLastModifiedTime(path));
                System.out.println("Is readable: " + Files.isReadable(path));
                System.out.println("Is writable: " + Files.isWritable(path));
            } else {
                System.out.println("File does not exist: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Error getting file info: " + e.getMessage());
        }
    }
    
    // Create directory
    public static void createDirectory(String dirName) {
        try {
            Path path = Paths.get(dirName);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
                System.out.println("Directory created: " + dirName);
            } else {
                System.out.println("Directory already exists: " + dirName);
            }
        } catch (IOException e) {
            System.err.println("Error creating directory: " + e.getMessage());
        }
    }
    
    // List files in directory
    public static void listFiles(String dirName) {
        try {
            Path path = Paths.get(dirName);
            if (Files.exists(path) && Files.isDirectory(path)) {
                System.out.println("Files in " + dirName + ":");
                try (Stream<Path> paths = Files.list(path)) {
                    paths.forEach(p -> {
                        try {
                            String type = Files.isDirectory(p) ? "DIR" : "FILE";
                            System.out.println(type + " - " + p.getFileName());
                        } catch (IOException e) {
                            System.err.println("Error processing path: " + e.getMessage());
                        }
                    });
                }
            } else {
                System.out.println("Directory does not exist: " + dirName);
            }
        } catch (IOException e) {
            System.err.println("Error listing files: " + e.getMessage());
        }
    }
    
    // Delete file
    public static void deleteFile(String filename) {
        try {
            Path path = Paths.get(filename);
            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("File deleted: " + filename);
            } else {
                System.out.println("File does not exist: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Error deleting file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        // Create a test directory
        createDirectory("testdir");
        
        // Create a test file
        try {
            Files.write(Paths.get("testdir/test.txt"), "Hello, World!".getBytes());
        } catch (IOException e) {
            System.err.println("Error creating test file: " + e.getMessage());
        }
        
        // Get file information
        getFileInfo("testdir/test.txt");
        
        // List files in directory
        listFiles("testdir");
        
        // Check if file exists
        System.out.println("File exists: " + fileExists("testdir/test.txt"));
        
        // Delete file
        deleteFile("testdir/test.txt");
        
        // List files again
        listFiles("testdir");
    }
}
```

## Practice Exercises

### Exercise 1: Calculator with Exception Handling
Create a calculator that handles division by zero and invalid input exceptions.

### Exercise 2: Student Grade File Manager
Build a system that reads/writes student grades to/from files with proper exception handling.

### Exercise 3: Configuration File Parser
Create a program that reads configuration files and handles missing or malformed entries.

### Exercise 4: Log File Analyzer
Build a program that analyzes log files and handles various file reading exceptions.

### Exercise 5: Data Backup System
Create a backup system that copies files and handles various I/O exceptions.

## Mini-Project: Contact Management System

Create a contact management system with file persistence:

```java
import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String email;
    private String phone;
    
    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    
    @Override
    public String toString() {
        return name + "," + email + "," + phone;
    }
    
    public static Contact fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            return new Contact(parts[0], parts[1], parts[2]);
        }
        throw new IllegalArgumentException("Invalid contact format: " + line);
    }
}

class ContactManager {
    private List<Contact> contacts;
    private String filename;
    
    public ContactManager(String filename) {
        this.contacts = new ArrayList<>();
        this.filename = filename;
        loadContacts();
    }
    
    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
        System.out.println("Contact added: " + contact.getName());
    }
    
    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
        saveContacts();
        System.out.println("Contact removed: " + name);
    }
    
    public Contact findContact(String name) {
        return contacts.stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    
    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        
        System.out.println("All Contacts:");
        System.out.println("Name\t\tEmail\t\t\tPhone");
        System.out.println("----\t\t-----\t\t\t-----");
        for (Contact contact : contacts) {
            System.out.printf("%-15s\t%-20s\t%s%n", 
                contact.getName(), contact.getEmail(), contact.getPhone());
        }
    }
    
    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Contact contact = Contact.fromString(line);
                    contacts.add(contact);
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid contact: " + e.getMessage());
                }
            }
            System.out.println("Contacts loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Contact file not found. Creating new file.");
        } catch (IOException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
        }
    }
    
    private void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }
}

public class ContactManagementSystem {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager("contacts.txt");
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Contact Management System ===");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. Find Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();
                        
                        Contact newContact = new Contact(name, email, phone);
                        manager.addContact(newContact);
                        break;
                        
                    case 2:
                        System.out.print("Enter name to remove: ");
                        String removeName = scanner.nextLine();
                        manager.removeContact(removeName);
                        break;
                        
                    case 3:
                        System.out.print("Enter name to find: ");
                        String findName = scanner.nextLine();
                        Contact found = manager.findContact(findName);
                        if (found != null) {
                            System.out.println("Found: " + found.getName() + 
                                             " - " + found.getEmail() + " - " + found.getPhone());
                        } else {
                            System.out.println("Contact not found.");
                        }
                        break;
                        
                    case 4:
                        manager.displayAllContacts();
                        break;
                        
                    case 5:
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
```

## Advanced Concepts

### 1. Try-with-Resources
```java
public class TryWithResourcesDemo {
    public static void main(String[] args) {
        // Automatic resource management
        try (FileInputStream fis = new FileInputStream("input.txt");
             FileOutputStream fos = new FileOutputStream("output.txt")) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        // Resources are automatically closed
    }
}
```

### 2. Multiple Exception Handling
```java
public class MultipleExceptionDemo {
    public static void main(String[] args) {
        try {
            // Code that might throw multiple exceptions
            File file = new File("nonexistent.txt");
            FileReader reader = new FileReader(file);
            int data = reader.read();
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("General error: " + e.getMessage());
        }
    }
}
```

### 3. Exception Propagation
```java
public class ExceptionPropagationDemo {
    public static void method1() throws IOException {
        method2();
    }
    
    public static void method2() throws IOException {
        method3();
    }
    
    public static void method3() throws IOException {
        throw new IOException("Error in method3");
    }
    
    public static void main(String[] args) {
        try {
            method1();
        } catch (IOException e) {
            System.err.println("Caught in main: " + e.getMessage());
        }
    }
}
```

## Weekly Challenge: Log File Processor

Create a log file processor that:
- Reads log files with different formats
- Handles corrupted or missing log entries
- Generates summary reports
- Supports multiple log file formats
- Implements robust error handling

## Assessment Criteria
- [ ] Proper exception handling implementation
- [ ] Effective use of try-catch-finally blocks
- [ ] Custom exception creation and usage
- [ ] File I/O operations with error handling
- [ ] Resource management with try-with-resources
- [ ] Code robustness and error recovery

## Resources
- [Java Exception Handling](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Java File I/O](https://docs.oracle.com/javase/tutorial/essential/io/)
- [Java NIO.2](https://docs.oracle.com/javase/tutorial/essential/io/fileio.html)
- [Java Streams](https://docs.oracle.com/javase/tutorial/essential/io/streams.html)