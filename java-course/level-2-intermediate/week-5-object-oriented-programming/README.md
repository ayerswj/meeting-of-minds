# Week 5: Object-Oriented Programming Fundamentals

## Learning Objectives
- Understand the core principles of OOP (Encapsulation, Inheritance, Polymorphism, Abstraction)
- Master class and object creation in Java
- Learn inheritance and method overriding
- Understand interfaces and abstract classes
- Apply OOP principles in real-world scenarios

## Theory

### 1. Classes and Objects

#### Class Definition
```java
public class ClassName {
    // Fields (attributes)
    private String name;
    private int age;
    
    // Constructor
    public ClassName(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Methods
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

#### Object Creation
```java
ClassName obj = new ClassName("John", 25);
obj.displayInfo();
```

### 2. Encapsulation
- Bundling data and methods that operate on that data within a single unit
- Controlling access to data through access modifiers
- Providing public methods to interact with private data

### 3. Inheritance
- Creating new classes based on existing classes
- Reusing code and establishing relationships between classes
- Using `extends` keyword for class inheritance

### 4. Polymorphism
- Same interface, different implementations
- Method overriding and method overloading
- Runtime polymorphism through inheritance

### 5. Abstraction
- Hiding complex implementation details
- Showing only necessary features
- Using abstract classes and interfaces

## Code Examples

### Example 1: Basic Class and Object
```java
public class Student {
    // Private fields (encapsulation)
    private String name;
    private int studentId;
    private double gpa;
    
    // Constructor
    public Student(String name, int studentId, double gpa) {
        this.name = name;
        this.studentId = studentId;
        this.gpa = gpa;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { 
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        }
    }
    
    // Methods
    public void displayInfo() {
        System.out.println("Student: " + name + " (ID: " + studentId + ")");
        System.out.println("GPA: " + gpa);
    }
    
    public boolean isHonorStudent() {
        return gpa >= 3.5;
    }
    
    public static void main(String[] args) {
        Student student1 = new Student("Alice", 1001, 3.8);
        Student student2 = new Student("Bob", 1002, 3.2);
        
        student1.displayInfo();
        System.out.println("Honor student: " + student1.isHonorStudent());
        
        student2.displayInfo();
        System.out.println("Honor student: " + student2.isHonorStudent());
    }
}
```

### Example 2: Inheritance
```java
// Base class
public class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void eat() {
        System.out.println(name + " is eating.");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
    
    public void makeSound() {
        System.out.println(name + " makes a sound.");
    }
}

// Derived class
public class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age); // Call parent constructor
        this.breed = breed;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
    
    public void fetch() {
        System.out.println(name + " is fetching the ball.");
    }
    
    public void displayInfo() {
        System.out.println("Dog: " + name + " (Age: " + age + ", Breed: " + breed + ")");
    }
}

// Another derived class
public class Cat extends Animal {
    private boolean isIndoor;
    
    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow!");
    }
    
    public void climb() {
        System.out.println(name + " is climbing.");
    }
    
    public void displayInfo() {
        System.out.println("Cat: " + name + " (Age: " + age + ", Indoor: " + isIndoor + ")");
    }
}

// Main class to demonstrate inheritance
public class InheritanceDemo {
    public static void main(String[] args) {
        Animal animal = new Animal("Generic Animal", 5);
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        Cat cat = new Cat("Whiskers", 2, true);
        
        // Polymorphism - same method, different behavior
        animal.makeSound();
        dog.makeSound();
        cat.makeSound();
        
        // Specific methods
        dog.fetch();
        cat.climb();
        
        // Display information
        dog.displayInfo();
        cat.displayInfo();
    }
}
```

### Example 3: Interfaces and Abstract Classes
```java
// Interface
public interface Drawable {
    void draw();
    double getArea();
}

// Abstract class
public abstract class Shape implements Drawable {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    // Abstract method - must be implemented by subclasses
    public abstract double getPerimeter();
}

// Concrete class implementing the abstract class
public class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle with radius " + radius);
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

// Another concrete class
public class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle with width " + width + " and height " + height);
    }
    
    @Override
    public double getArea() {
        return width * height;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

// Main class to demonstrate interfaces and abstract classes
public class ShapeDemo {
    public static void main(String[] args) {
        Circle circle = new Circle("Red", 5.0);
        Rectangle rectangle = new Rectangle("Blue", 4.0, 6.0);
        
        // Using interface methods
        circle.draw();
        System.out.println("Circle area: " + circle.getArea());
        System.out.println("Circle perimeter: " + circle.getPerimeter());
        
        rectangle.draw();
        System.out.println("Rectangle area: " + rectangle.getArea());
        System.out.println("Rectangle perimeter: " + rectangle.getPerimeter());
        
        // Polymorphism with interface
        Drawable[] shapes = {circle, rectangle};
        for (Drawable shape : shapes) {
            shape.draw();
            System.out.println("Area: " + shape.getArea());
        }
    }
}
```

### Example 4: Method Overloading and Overriding
```java
public class Calculator {
    // Method overloading - same name, different parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
}

public class AdvancedCalculator extends Calculator {
    // Method overriding - same signature as parent class
    @Override
    public int add(int a, int b) {
        System.out.println("Advanced addition: " + a + " + " + b);
        return super.add(a, b); // Call parent method
    }
    
    // New methods
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public double divide(double a, double b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }
}

public class OverloadingOverridingDemo {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        AdvancedCalculator advCalc = new AdvancedCalculator();
        
        // Method overloading examples
        System.out.println("Calculator:");
        System.out.println("add(5, 3): " + calc.add(5, 3));
        System.out.println("add(5.5, 3.2): " + calc.add(5.5, 3.2));
        System.out.println("add(1, 2, 3): " + calc.add(1, 2, 3));
        System.out.println("add(\"Hello \", \"World\"): " + calc.add("Hello ", "World"));
        
        // Method overriding example
        System.out.println("\nAdvanced Calculator:");
        System.out.println("add(10, 5): " + advCalc.add(10, 5));
        System.out.println("multiply(4, 6): " + advCalc.multiply(4, 6));
        System.out.println("divide(15.0, 3.0): " + advCalc.divide(15.0, 3.0));
    }
}
```

## Practice Exercises

### Exercise 1: Bank Account System
Create a bank account system with classes for different account types (Savings, Checking) using inheritance.

### Exercise 2: Employee Management
Build an employee management system with different employee types (Manager, Developer, Intern).

### Exercise 3: Vehicle Hierarchy
Create a vehicle hierarchy with cars, motorcycles, and trucks using interfaces and abstract classes.

### Exercise 4: Library System
Implement a library system with books, members, and transactions using OOP principles.

### Exercise 5: Shape Calculator
Create a shape calculator that can calculate area and perimeter for different shapes.

## Mini-Project: Online Shopping System

Create a comprehensive online shopping system:

```java
import java.util.*;

// Product class
class Product {
    private String id;
    private String name;
    private double price;
    private int stock;
    
    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    @Override
    public String toString() {
        return name + " - $" + price + " (Stock: " + stock + ")";
    }
}

// Customer class
class Customer {
    private String id;
    private String name;
    private String email;
    private List<Order> orderHistory;
    
    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orderHistory = new ArrayList<>();
    }
    
    public void addOrder(Order order) {
        orderHistory.add(order);
    }
    
    public void displayOrderHistory() {
        System.out.println("Order history for " + name + ":");
        for (Order order : orderHistory) {
            System.out.println(order);
        }
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

// Order class
class Order {
    private String orderId;
    private Customer customer;
    private List<Product> items;
    private double totalAmount;
    private String status;
    
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        this.status = "Pending";
    }
    
    public void addItem(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            for (int i = 0; i < quantity; i++) {
                items.add(product);
            }
            product.setStock(product.getStock() - quantity);
            calculateTotal();
        } else {
            System.out.println("Insufficient stock for " + product.getName());
        }
    }
    
    private void calculateTotal() {
        totalAmount = 0.0;
        for (Product item : items) {
            totalAmount += item.getPrice();
        }
    }
    
    public void processOrder() {
        status = "Processed";
        System.out.println("Order " + orderId + " processed successfully!");
    }
    
    @Override
    public String toString() {
        return "Order " + orderId + " - Total: $" + String.format("%.2f", totalAmount) + " - Status: " + status;
    }
    
    // Getters
    public String getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
}

// Shopping Cart class
class ShoppingCart {
    private Customer customer;
    private Map<Product, Integer> items;
    
    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.items = new HashMap<>();
    }
    
    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }
    
    public void removeItem(Product product) {
        items.remove(product);
    }
    
    public double getTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
    
    public void displayCart() {
        System.out.println("Shopping Cart for " + customer.getName() + ":");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getName() + " x" + quantity + " = $" + (product.getPrice() * quantity));
        }
        System.out.println("Total: $" + String.format("%.2f", getTotal()));
    }
    
    public Order checkout() {
        Order order = new Order("ORD" + System.currentTimeMillis(), customer);
        
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            order.addItem(product, quantity);
        }
        
        order.processOrder();
        customer.addOrder(order);
        items.clear();
        
        return order;
    }
}

// Main shopping system
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        // Create products
        Product laptop = new Product("P001", "Laptop", 999.99, 10);
        Product phone = new Product("P002", "Smartphone", 599.99, 15);
        Product headphones = new Product("P003", "Headphones", 99.99, 20);
        
        // Create customer
        Customer customer = new Customer("C001", "John Doe", "john@example.com");
        
        // Create shopping cart
        ShoppingCart cart = new ShoppingCart(customer);
        
        // Add items to cart
        cart.addItem(laptop, 1);
        cart.addItem(phone, 1);
        cart.addItem(headphones, 2);
        
        // Display cart
        cart.displayCart();
        
        // Checkout
        Order order = cart.checkout();
        
        // Display order history
        customer.displayOrderHistory();
    }
}
```

## Advanced Concepts

### 1. Composition vs Inheritance
```java
// Inheritance example
class Car extends Vehicle {
    // Car-specific methods
}

// Composition example
class Car {
    private Engine engine;
    private Wheel[] wheels;
    
    public Car() {
        this.engine = new Engine();
        this.wheels = new Wheel[4];
        for (int i = 0; i < 4; i++) {
            wheels[i] = new Wheel();
        }
    }
}
```

### 2. Final Classes and Methods
```java
public final class UtilityClass {
    // Cannot be inherited
    
    public static final double PI = 3.14159;
    
    public final void utilityMethod() {
        // Cannot be overridden
    }
}
```

### 3. Static Members
```java
public class BankAccount {
    private static int accountCounter = 0;
    private String accountNumber;
    private double balance;
    
    public BankAccount(double initialBalance) {
        this.accountNumber = "ACC" + (++accountCounter);
        this.balance = initialBalance;
    }
    
    public static int getTotalAccounts() {
        return accountCounter;
    }
}
```

## Weekly Challenge: Game Character System

Create a role-playing game character system with:
- Base character class with inheritance
- Different character types (Warrior, Mage, Archer)
- Equipment and inventory system
- Combat mechanics
- Level progression system

## Assessment Criteria
- [ ] Proper class and object creation
- [ ] Effective use of inheritance and polymorphism
- [ ] Correct implementation of interfaces and abstract classes
- [ ] Encapsulation principles applied
- [ ] Method overloading and overriding
- [ ] Code organization and design patterns

## Resources
- [Java OOP Tutorial](https://docs.oracle.com/javase/tutorial/java/concepts/)
- [Java Inheritance](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)
- [Java Interfaces](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)
- [Java Abstract Classes](https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html)