# Week 17: Design Patterns and SOLID Principles

## 🎯 Learning Objectives
- Understand and implement all major design patterns
- Master SOLID principles and their application
- Learn when and how to use each pattern
- Recognize anti-patterns and avoid them
- Apply patterns to solve real-world problems
- Understand the trade-offs of different patterns

## 📚 Theory

### What are Design Patterns?
Design patterns are proven solutions to common software design problems. They provide a shared vocabulary for developers and help create maintainable, flexible, and reusable code.

### SOLID Principles

#### Single Responsibility Principle (SRP)
A class should have only one reason to change.

```java
// Bad: Multiple responsibilities
public class UserManager {
    public void createUser(String username, String email) { /* ... */ }
    public void sendEmail(String to, String subject, String body) { /* ... */ }
    public void saveToDatabase(User user) { /* ... */ }
    public void validateEmail(String email) { /* ... */ }
}

// Good: Single responsibility
public class UserManager {
    public void createUser(String username, String email) { /* ... */ }
}

public class EmailService {
    public void sendEmail(String to, String subject, String body) { /* ... */ }
}

public class UserRepository {
    public void save(User user) { /* ... */ }
}

public class EmailValidator {
    public boolean isValid(String email) { /* ... */ }
}
```

#### Open/Closed Principle (OCP)
Software entities should be open for extension but closed for modification.

```java
// Bad: Need to modify existing code
public class PaymentProcessor {
    public void processPayment(String type, double amount) {
        if ("credit".equals(type)) {
            // Process credit card
        } else if ("debit".equals(type)) {
            // Process debit card
        }
        // Need to modify this method for new payment types
    }
}

// Good: Open for extension
public interface PaymentMethod {
    void process(double amount);
}

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void process(double amount) { /* ... */ }
}

public class DebitCardPayment implements PaymentMethod {
    @Override
    public void process(double amount) { /* ... */ }
}

public class PaymentProcessor {
    public void processPayment(PaymentMethod payment, double amount) {
        payment.process(amount);
    }
}
```

#### Liskov Substitution Principle (LSP)
Subtypes must be substitutable for their base types.

```java
// Bad: Violates LSP
public class Rectangle {
    protected int width, height;
    
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public int getArea() { return width * height; }
}

public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width; // Changes height too!
    }
    
    @Override
    public void setHeight(int height) {
        this.height = height;
        this.width = height; // Changes width too!
    }
}

// Good: Follows LSP
public interface Shape {
    int getArea();
}

public class Rectangle implements Shape {
    private int width, height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public int getArea() { return width * height; }
}

public class Square implements Shape {
    private int side;
    
    public Square(int side) {
        this.side = side;
    }
    
    @Override
    public int getArea() { return side * side; }
}
```

#### Interface Segregation Principle (ISP)
Clients should not be forced to depend on interfaces they don't use.

```java
// Bad: Fat interface
public interface Worker {
    void work();
    void eat();
    void sleep();
}

public class Robot implements Worker {
    public void work() { /* ... */ }
    public void eat() { /* Robot doesn't eat! */ }
    public void sleep() { /* Robot doesn't sleep! */ }
}

// Good: Segregated interfaces
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public interface Sleepable {
    void sleep();
}

public class Human implements Workable, Eatable, Sleepable {
    public void work() { /* ... */ }
    public void eat() { /* ... */ }
    public void sleep() { /* ... */ }
}

public class Robot implements Workable {
    public void work() { /* ... */ }
}
```

#### Dependency Inversion Principle (DIP)
High-level modules should not depend on low-level modules. Both should depend on abstractions.

```java
// Bad: High-level depends on low-level
public class ReportGenerator {
    private MySQLDatabase database;
    
    public ReportGenerator() {
        this.database = new MySQLDatabase();
    }
    
    public void generateReport() {
        String data = database.getData();
        // Generate report
    }
}

// Good: Both depend on abstraction
public interface Database {
    String getData();
}

public class MySQLDatabase implements Database {
    @Override
    public String getData() { /* ... */ }
}

public class ReportGenerator {
    private Database database;
    
    public ReportGenerator(Database database) {
        this.database = database;
    }
    
    public void generateReport() {
        String data = database.getData();
        // Generate report
    }
}
```

## 🏗️ Creational Patterns

### Singleton Pattern
Ensures a class has only one instance and provides global access to it.

```java
public class Singleton {
    // Thread-safe singleton with double-checked locking
    private static volatile Singleton instance;
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

// Enum singleton (recommended approach)
public enum EnumSingleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("Singleton method called");
    }
}

// Application example
public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;
    
    private DatabaseConnection() {
        // Initialize database connection
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
}
```

### Factory Pattern
Creates objects without specifying their exact classes.

```java
// Simple Factory
public interface Animal {
    void makeSound();
}

public class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

public class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

public class AnimalFactory {
    public static Animal createAnimal(String type) {
        switch (type.toLowerCase()) {
            case "dog": return new Dog();
            case "cat": return new Cat();
            default: throw new IllegalArgumentException("Unknown animal type");
        }
    }
}

// Factory Method Pattern
public abstract class AnimalCreator {
    abstract Animal createAnimal();
    
    public void someOperation() {
        Animal animal = createAnimal();
        animal.makeSound();
    }
}

public class DogCreator extends AnimalCreator {
    @Override
    Animal createAnimal() {
        return new Dog();
    }
}

// Abstract Factory Pattern
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

public class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

public class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

### Builder Pattern
Constructs complex objects step by step.

```java
public class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
    }
    
    public static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        
        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        
        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }
        
        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }
        
        public Computer build() {
            if (cpu == null || ram == null) {
                throw new IllegalStateException("CPU and RAM are required");
            }
            return new Computer(this);
        }
    }
}

// Usage
Computer computer = new Computer.Builder()
    .cpu("Intel i7")
    .ram("16GB")
    .storage("512GB SSD")
    .gpu("RTX 3080")
    .build();
```

## 🔗 Structural Patterns

### Adapter Pattern
Allows incompatible interfaces to work together.

```java
// Legacy system
public class LegacyPaymentSystem {
    public void processPayment(String cardNumber, String expiry, double amount) {
        System.out.println("Processing payment with legacy system");
    }
}

// New system interface
public interface PaymentProcessor {
    void process(PaymentRequest request);
}

public class PaymentRequest {
    private String cardNumber;
    private String expiry;
    private double amount;
    
    // Constructor, getters, setters
}

// Adapter
public class LegacyPaymentAdapter implements PaymentProcessor {
    private LegacyPaymentSystem legacySystem;
    
    public LegacyPaymentAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }
    
    @Override
    public void process(PaymentRequest request) {
        legacySystem.processPayment(
            request.getCardNumber(),
            request.getExpiry(),
            request.getAmount()
        );
    }
}
```

### Decorator Pattern
Adds behavior to objects dynamically.

```java
public interface Coffee {
    double getCost();
    String getDescription();
}

public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 2.0;
    }
    
    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    @Override
    public double getCost() {
        return coffee.getCost();
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
}

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + ", milk";
    }
}

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + ", sugar";
    }
}

// Usage
Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);
System.out.println(coffee.getDescription() + ": $" + coffee.getCost());
```

### Facade Pattern
Provides a simplified interface to a complex subsystem.

```java
// Complex subsystem
public class CPU {
    public void freeze() { System.out.println("CPU freeze"); }
    public void jump(long position) { System.out.println("CPU jump to " + position); }
    public void execute() { System.out.println("CPU execute"); }
}

public class Memory {
    public void load(long position, byte[] data) { 
        System.out.println("Memory load at " + position); 
    }
}

public class HardDrive {
    public byte[] read(long lba, int size) { 
        System.out.println("HardDrive read " + size + " bytes"); 
        return new byte[size];
    }
}

// Facade
public class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void start() {
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
    }
}

// Usage
ComputerFacade computer = new ComputerFacade();
computer.start();
```

## 🎭 Behavioral Patterns

### Observer Pattern
Defines a one-to-many dependency between objects.

```java
public interface Observer {
    void update(String message);
}

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

public class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String news;
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
    
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
}

public class NewsChannel implements Observer {
    private String name;
    
    public NewsChannel(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}

// Usage
NewsAgency agency = new NewsAgency();
NewsChannel channel1 = new NewsChannel("CNN");
NewsChannel channel2 = new NewsChannel("BBC");

agency.registerObserver(channel1);
agency.registerObserver(channel2);
agency.setNews("Breaking news!");
```

### Strategy Pattern
Defines a family of algorithms and makes them interchangeable.

```java
public interface PaymentStrategy {
    void pay(double amount);
}

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cvv;
    
    public CreditCardPayment(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using credit card");
    }
}

public class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal");
    }
}

public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}

// Usage
ShoppingCart cart = new ShoppingCart();
cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "123"));
cart.checkout(100.0);

cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
cart.checkout(50.0);
```

### Command Pattern
Encapsulates a request as an object.

```java
public interface Command {
    void execute();
    void undo();
}

public class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }
    
    public void turnOff() {
        System.out.println("Light is off");
    }
}

public class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOn();
    }
    
    @Override
    public void undo() {
        light.turnOff();
    }
}

public class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOff();
    }
    
    @Override
    public void undo() {
        light.turnOn();
    }
}

public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;
    
    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
    }
    
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }
    
    public void onButtonPressed(int slot) {
        if (onCommands[slot] != null) {
            onCommands[slot].execute();
            undoCommand = onCommands[slot];
        }
    }
    
    public void offButtonPressed(int slot) {
        if (offCommands[slot] != null) {
            offCommands[slot].execute();
            undoCommand = offCommands[slot];
        }
    }
    
    public void undoButtonPressed() {
        if (undoCommand != null) {
            undoCommand.undo();
        }
    }
}
```

## 🚫 Anti-Patterns

### God Object
A class that knows too much or does too much.

```java
// Anti-pattern: God Object
public class GodObject {
    public void processUser() { /* ... */ }
    public void sendEmail() { /* ... */ }
    public void saveToDatabase() { /* ... */ }
    public void generateReport() { /* ... */ }
    public void validateData() { /* ... */ }
    public void calculateTaxes() { /* ... */ }
    public void processPayment() { /* ... */ }
    // ... many more methods
}

// Solution: Break into focused classes
public class UserService {
    public void processUser() { /* ... */ }
}

public class EmailService {
    public void sendEmail() { /* ... */ }
}

public class DatabaseService {
    public void saveToDatabase() { /* ... */ }
}
```

### Anemic Domain Model
Objects that are just data containers without behavior.

```java
// Anti-pattern: Anemic Domain Model
public class Order {
    private String id;
    private List<OrderItem> items;
    private double total;
    private String status;
    
    // Only getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    // ... more getters and setters
}

public class OrderService {
    public void calculateTotal(Order order) {
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }
        order.setTotal(total);
    }
    
    public void processOrder(Order order) {
        // Business logic in service instead of domain object
    }
}

// Solution: Rich Domain Model
public class Order {
    private String id;
    private List<OrderItem> items;
    private double total;
    private OrderStatus status;
    
    public void addItem(OrderItem item) {
        items.add(item);
        calculateTotal();
    }
    
    public void process() {
        if (canBeProcessed()) {
            status = OrderStatus.PROCESSING;
            // Business logic here
        }
    }
    
    private void calculateTotal() {
        total = items.stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
    }
    
    private boolean canBeProcessed() {
        return status == OrderStatus.PENDING && !items.isEmpty();
    }
}
```

## 🏋️ Exercises

### Exercise 1: E-commerce System
Design an e-commerce system using multiple patterns:
- Use Factory pattern for creating different types of products
- Use Strategy pattern for different payment methods
- Use Observer pattern for order notifications
- Use Decorator pattern for product features
- Use Command pattern for order operations

### Exercise 2: Logging Framework
Create a logging framework using:
- Singleton pattern for logger instance
- Strategy pattern for different log levels
- Decorator pattern for log formatting
- Observer pattern for log listeners
- Factory pattern for creating loggers

### Exercise 3: Configuration Management
Build a configuration management system using:
- Builder pattern for complex configurations
- Factory pattern for different config sources
- Adapter pattern for legacy config formats
- Observer pattern for config changes
- Strategy pattern for different config validators

### Exercise 4: Game Engine
Design a simple game engine using:
- Command pattern for game actions
- Observer pattern for game events
- Strategy pattern for AI behaviors
- Factory pattern for creating game objects
- Decorator pattern for game object enhancements

## 📖 Best Practices

1. **Don't over-engineer** - Use patterns only when they solve a real problem
2. **Keep it simple** - Prefer composition over inheritance
3. **Follow SOLID principles** - They guide good design decisions
4. **Document your patterns** - Make the intent clear to other developers
5. **Consider performance** - Some patterns have overhead
6. **Test your patterns** - Ensure they work correctly
7. **Refactor existing code** - Apply patterns gradually
8. **Learn from mistakes** - Recognize when patterns don't fit

## 🎯 Next Steps
- Practice implementing patterns in real projects
- Study framework implementations to see patterns in action
- Learn about architectural patterns (MVC, MVP, MVVM)
- Explore enterprise patterns and integration patterns