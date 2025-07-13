# Lesson 5: Inheritance and Polymorphism

## Learning Objectives
- Understand the concept of inheritance in object-oriented programming
- Learn how to create class hierarchies
- Master method overriding and the `super` keyword
- Understand polymorphism and dynamic method dispatch
- Practice creating abstract classes and interfaces

## What is Inheritance?
Inheritance is a mechanism that allows a class to inherit properties and methods from another class. It promotes code reuse and establishes a relationship between classes.

## Basic Inheritance

### Inheritance Syntax
```java
// Parent class (superclass)
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
}

// Child class (subclass)
public class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // Call parent constructor
        this.breed = breed;
    }
    
    public void bark() {
        System.out.println(name + " is barking.");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating dog food.");
    }
}
```

### Inheritance Example
```java
public class InheritanceDemo {
    public static void main(String[] args) {
        Animal animal = new Animal("Generic Animal", 5);
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        
        animal.eat();  // Generic Animal is eating.
        animal.sleep(); // Generic Animal is sleeping.
        
        dog.eat();     // Buddy is eating dog food.
        dog.sleep();   // Buddy is sleeping.
        dog.bark();    // Buddy is barking.
    }
}
```

## Access Modifiers in Inheritance

### Access Levels
```java
public class Parent {
    public String publicField = "Public";
    protected String protectedField = "Protected";
    String defaultField = "Default";
    private String privateField = "Private";
    
    public void publicMethod() {
        System.out.println("Public method");
    }
    
    protected void protectedMethod() {
        System.out.println("Protected method");
    }
    
    void defaultMethod() {
        System.out.println("Default method");
    }
    
    private void privateMethod() {
        System.out.println("Private method");
    }
}

public class Child extends Parent {
    public void testAccess() {
        // Can access public, protected, and default
        System.out.println(publicField);
        System.out.println(protectedField);
        System.out.println(defaultField);
        // System.out.println(privateField); // Error: private not accessible
        
        publicMethod();
        protectedMethod();
        defaultMethod();
        // privateMethod(); // Error: private not accessible
    }
}
```

## Method Overriding

### Overriding Rules
```java
public class Animal {
    public void makeSound() {
        System.out.println("Some animal sound");
    }
    
    public void move() {
        System.out.println("Animal is moving");
    }
    
    // Final method cannot be overridden
    public final void breathe() {
        System.out.println("Animal is breathing");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
    
    @Override
    public void move() {
        System.out.println("Dog is running");
    }
    
    // Cannot override final method
    // public void breathe() { } // Error
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow! Meow!");
    }
    
    @Override
    public void move() {
        System.out.println("Cat is walking gracefully");
    }
}
```

### The `super` Keyword
```java
public class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }
}

public class Car extends Vehicle {
    private int numDoors;
    
    public Car(String brand, String model, int year, int numDoors) {
        super(brand, model, year);  // Call parent constructor
        this.numDoors = numDoors;
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method
        System.out.println("Number of doors: " + numDoors);
    }
    
    public void startEngine() {
        System.out.println(brand + " " + model + " engine is starting");
    }
}
```

## Polymorphism

### Method Overriding and Polymorphism
```java
public class PolymorphismDemo {
    public static void main(String[] args) {
        // Polymorphism: same interface, different implementations
        Animal[] animals = {
            new Dog("Buddy", 3, "Golden Retriever"),
            new Cat("Whiskers", 2),
            new Bird("Tweety", 1)
        };
        
        for (Animal animal : animals) {
            animal.makeSound();  // Different sounds for each animal
        }
    }
}

public class Bird extends Animal {
    private boolean canFly;
    
    public Bird(String name, int age) {
        super(name, age);
        this.canFly = true;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " is chirping.");
    }
    
    @Override
    public void move() {
        if (canFly) {
            System.out.println(name + " is flying.");
        } else {
            System.out.println(name + " is walking.");
        }
    }
}
```

### Runtime Polymorphism
```java
public class Shape {
    public double calculateArea() {
        return 0.0;
    }
    
    public double calculatePerimeter() {
        return 0.0;
    }
}

public class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}

public class ShapeCalculator {
    public static void printShapeInfo(Shape shape) {
        System.out.println("Area: " + shape.calculateArea());
        System.out.println("Perimeter: " + shape.calculatePerimeter());
    }
    
    public static void main(String[] args) {
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        
        printShapeInfo(circle);     // Calls Circle's methods
        printShapeInfo(rectangle);  // Calls Rectangle's methods
    }
}
```

## Method Overloading vs Overriding

### Method Overloading (Compile-time Polymorphism)
```java
public class Calculator {
    // Overloading: same method name, different parameters
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
```

### Method Overriding (Runtime Polymorphism)
```java
public class Animal {
    public void makeSound() {
        System.out.println("Animal sound");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}
```

## Abstract Classes

### Abstract Class Example
```java
public abstract class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract method: must be implemented by subclasses
    public abstract double calculateArea();
    
    // Concrete method: can be used by subclasses
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}

public class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    public double getRadius() {
        return radius;
    }
}

public class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
}
```

## Interfaces

### Interface Example
```java
public interface Drawable {
    void draw();
    String getColor();
}

public interface Movable {
    void move(int x, int y);
    int getX();
    int getY();
}

public class Circle implements Drawable, Movable {
    private String color;
    private int x, y;
    private double radius;
    
    public Circle(String color, int x, int y, double radius) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle at (" + x + ", " + y + ") with radius " + radius);
    }
    
    @Override
    public String getColor() {
        return color;
    }
    
    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int getX() {
        return x;
    }
    
    @Override
    public int getY() {
        return y;
    }
}
```

## Multiple Inheritance with Interfaces

### Interface Inheritance
```java
public interface Animal {
    void eat();
    void sleep();
}

public interface Pet {
    void play();
    void beFriendly();
}

public interface Domesticated {
    void obeyCommands();
}

// Multiple interface inheritance
public interface DomesticPet extends Animal, Pet, Domesticated {
    void provideCompanionship();
}

public class Dog implements DomesticPet {
    private String name;
    
    public Dog(String name) {
        this.name = name;
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating dog food");
    }
    
    @Override
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    @Override
    public void play() {
        System.out.println(name + " is playing fetch");
    }
    
    @Override
    public void beFriendly() {
        System.out.println(name + " is wagging tail");
    }
    
    @Override
    public void obeyCommands() {
        System.out.println(name + " is sitting");
    }
    
    @Override
    public void provideCompanionship() {
        System.out.println(name + " is providing companionship");
    }
}
```

## Final Classes and Methods

### Final Keyword
```java
// Final class cannot be inherited
public final class StringUtils {
    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}

// Cannot extend final class
// public class ExtendedStringUtils extends StringUtils { } // Error

public class Parent {
    // Final method cannot be overridden
    public final void importantMethod() {
        System.out.println("This method cannot be overridden");
    }
    
    public void normalMethod() {
        System.out.println("This method can be overridden");
    }
}

public class Child extends Parent {
    // Cannot override final method
    // public void importantMethod() { } // Error
    
    @Override
    public void normalMethod() {
        System.out.println("Overridden method");
    }
}
```

## Exercises

### Exercise 1: Basic Inheritance
Create a class hierarchy for vehicles:
- Create a base `Vehicle` class with common properties
- Create `Car`, `Motorcycle`, and `Truck` classes that extend `Vehicle`
- Implement appropriate methods for each class

### Exercise 2: Method Overriding
Create a hierarchy for employees:
- Base `Employee` class with salary calculation
- `Manager`, `Developer`, and `Salesperson` classes
- Override salary calculation based on role

### Exercise 3: Abstract Classes
Create an abstract `BankAccount` class:
- Abstract methods for deposit and withdrawal
- Concrete methods for common operations
- Implement `SavingsAccount` and `CheckingAccount`

### Exercise 4: Interfaces
Create interfaces for different behaviors:
- `Payable` interface for payment processing
- `Comparable` interface for sorting
- Implement these interfaces in your classes

## Practice Files
- `InheritanceDemo.java` - Basic inheritance examples
- `PolymorphismDemo.java` - Polymorphism examples
- `AbstractClassesDemo.java` - Abstract class examples
- `InterfacesDemo.java` - Interface examples
- `InheritanceExercises.java` - Practice problems

## Key Takeaways
1. Inheritance promotes code reuse and establishes relationships between classes
2. Use `extends` for class inheritance and `implements` for interfaces
3. Method overriding enables runtime polymorphism
4. Use `super` to access parent class members
5. Abstract classes can have both abstract and concrete methods
6. Interfaces define contracts that classes must implement
7. Java supports multiple interface inheritance but not multiple class inheritance
8. Use `final` to prevent inheritance or method overriding

## Next Steps
After completing this lesson, move on to:
- Collections Framework
- Exception handling
- File I/O operations
- Advanced OOP concepts

## Additional Resources
- [Java Inheritance Tutorial](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)
- [Java Interfaces Tutorial](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)