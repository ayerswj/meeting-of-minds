# Lesson 3: Classes and Objects

## Learning Objectives
- Understand the concept of Object-Oriented Programming (OOP)
- Learn how to create classes and objects in Java
- Master constructors and method creation
- Understand encapsulation with private fields and public methods
- Practice creating getters and setters

## What is Object-Oriented Programming?
Object-Oriented Programming is a programming paradigm that organizes code into objects that contain both data and code. It's based on four main principles:
1. **Encapsulation**: Bundling data and methods that operate on that data
2. **Inheritance**: Creating new classes from existing ones
3. **Polymorphism**: Using a single interface for different types
4. **Abstraction**: Hiding complex implementation details

## Classes and Objects

### What is a Class?
A class is a blueprint or template for creating objects. It defines:
- **Attributes** (fields/properties): Data that objects will have
- **Methods**: Functions that objects can perform
- **Constructors**: Special methods to initialize objects

### What is an Object?
An object is an instance of a class. It's a concrete entity created from the class blueprint.

## Class Structure

### Basic Class Syntax
```java
public class ClassName {
    // Fields (attributes)
    private dataType fieldName;
    
    // Constructor
    public ClassName(parameters) {
        // initialization code
    }
    
    // Methods
    public returnType methodName(parameters) {
        // method body
    }
}
```

## Creating a Simple Class

### Example: Car Class
```java
public class Car {
    // Fields (attributes)
    private String brand;
    private String model;
    private int year;
    private double price;
    
    // Constructor
    public Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }
    
    // Methods
    public void start() {
        System.out.println("The " + brand + " " + model + " is starting.");
    }
    
    public void stop() {
        System.out.println("The " + brand + " " + model + " is stopping.");
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Price: $" + price);
    }
}
```

## Constructors

### Default Constructor
If you don't define a constructor, Java provides a default one:
```java
public class Student {
    private String name;
    private int age;
    
    // Default constructor (automatically provided)
    public Student() {
        // Fields get default values (null, 0, false)
    }
}
```

### Parameterized Constructor
```java
public class Student {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

### Multiple Constructors (Constructor Overloading)
```java
public class Student {
    private String name;
    private int age;
    private String major;
    
    // Constructor 1
    public Student() {
        this.name = "Unknown";
        this.age = 18;
        this.major = "Undecided";
    }
    
    // Constructor 2
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.major = "Undecided";
    }
    
    // Constructor 3
    public Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }
}
```

## Encapsulation

### Private Fields
Fields should be private to prevent direct access:
```java
public class BankAccount {
    private double balance;  // Private field
    private String accountNumber;
}
```

### Public Methods (Getters and Setters)
Provide controlled access to private fields:
```java
public class BankAccount {
    private double balance;
    private String accountNumber;
    
    // Getter for balance
    public double getBalance() {
        return balance;
    }
    
    // Setter for balance
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Balance cannot be negative");
        }
    }
    
    // Getter for account number
    public String getAccountNumber() {
        return accountNumber;
    }
    
    // Setter for account number
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
```

## Creating and Using Objects

### Creating Objects
```java
// Using the Car class
Car myCar = new Car("Toyota", "Camry", 2020, 25000.0);
Car anotherCar = new Car("Honda", "Civic", 2019, 22000.0);
```

### Calling Methods
```java
myCar.start();
myCar.displayInfo();
myCar.stop();
```

### Accessing Fields (through getters)
```java
String brand = myCar.getBrand();
double price = myCar.getPrice();
```

## The `this` Keyword
`this` refers to the current object instance:
```java
public class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;  // this.name refers to the field
        this.age = age;    // name refers to the parameter
    }
    
    public void setName(String name) {
        this.name = name;  // Distinguishes field from parameter
    }
}
```

## Static vs Instance Members

### Instance Members
Belong to individual objects:
```java
public class Student {
    private String name;  // Instance field
    private int age;      // Instance field
    
    public void study() {  // Instance method
        System.out.println(name + " is studying");
    }
}
```

### Static Members
Belong to the class itself:
```java
public class Student {
    private String name;
    private int age;
    public static int totalStudents = 0;  // Static field
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        totalStudents++;  // Increment static field
    }
    
    public static void displayTotalStudents() {  // Static method
        System.out.println("Total students: " + totalStudents);
    }
}
```

## Examples

### Bank Account Example
```java
public class BankAccount {
    private String accountNumber;
    private double balance;
    private String ownerName;
    
    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount");
            return false;
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
}
```

## Exercises

### Exercise 1: Student Class
Create a `Student` class with:
- Fields: name, studentId, major, gpa
- Constructor to initialize all fields
- Methods: study(), takeExam(), displayInfo()
- Getters and setters for all fields

### Exercise 2: Rectangle Class
Create a `Rectangle` class with:
- Fields: length, width
- Constructor
- Methods: calculateArea(), calculatePerimeter(), isSquare()
- Getters and setters

### Exercise 3: Book Class
Create a `Book` class with:
- Fields: title, author, isbn, price, available
- Constructor
- Methods: borrow(), return(), displayInfo()
- Getters and setters

### Exercise 4: Calculator Class
Create a `Calculator` class with:
- Fields: result
- Constructor
- Methods: add(), subtract(), multiply(), divide(), clear()
- Getter for result

## Practice Files
- `Car.java` - Complete Car class example
- `BankAccount.java` - Bank account implementation
- `Student.java` - Student class with methods
- `ClassesObjectsDemo.java` - Demonstration of creating and using objects

## Key Takeaways
1. Classes are blueprints, objects are instances
2. Use constructors to initialize objects
3. Encapsulate data with private fields and public methods
4. Use getters and setters for controlled access
5. The `this` keyword refers to the current object
6. Static members belong to the class, instance members belong to objects

## Next Steps
After completing this lesson, move on to:
- Inheritance and method overriding
- Polymorphism and interfaces
- Abstract classes and methods

## Additional Resources
- [Java Classes and Objects](https://docs.oracle.com/javase/tutorial/java/javaOO/)
- [Java Constructors](https://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html)