# Level 2: Week 5 - Object-Oriented Programming

## 🎯 Learning Objectives
By the end of this week, you will be able to:
- Understand OOP principles (encapsulation, inheritance, polymorphism)
- Create classes and objects
- Use constructors and methods
- Implement inheritance and method overriding
- Design object-oriented solutions

## 📚 Theory

### What is Object-Oriented Programming?
OOP is a programming paradigm that organizes code into objects that contain data and code. It's based on the concept of "objects" that can contain data and code that manipulates that data.

### Core OOP Principles
1. **Encapsulation**: Bundling data and methods that work on that data within a single unit
2. **Inheritance**: Creating new classes that are built upon existing classes
3. **Polymorphism**: The ability to present the same interface for different underlying forms
4. **Abstraction**: Hiding complex implementation details

## 🏗️ Classes and Objects

### Creating a Simple Class
```python
class Dog:
    def __init__(self, name, age):
        self.name = name
        self.age = age
    
    def bark(self):
        print(f"{self.name} says: Woof!")
    
    def get_info(self):
        return f"{self.name} is {self.age} years old"

# Creating objects (instances)
my_dog = Dog("Buddy", 3)
my_dog.bark()
print(my_dog.get_info())
```

### Understanding `self`
`self` refers to the instance of the class. It's used to access instance variables and methods.

```python
class Person:
    def __init__(self, name):
        self.name = name  # self.name is an instance variable
    
    def greet(self):
        print(f"Hello, I'm {self.name}")  # self.name accesses the instance variable
```

## 🔧 Constructors and Methods

### The `__init__` Method
This is the constructor that runs when an object is created:

```python
class Car:
    def __init__(self, brand, model, year):
        self.brand = brand
        self.model = model
        self.year = year
        self.is_running = False
    
    def start_engine(self):
        self.is_running = True
        print(f"{self.brand} {self.model} engine started")
    
    def stop_engine(self):
        self.is_running = False
        print(f"{self.brand} {self.model} engine stopped")

# Creating a car object
my_car = Car("Toyota", "Camry", 2020)
my_car.start_engine()
```

### Instance Methods vs Class Methods
```python
class Student:
    school_name = "Python Academy"  # Class variable
    
    def __init__(self, name, grade):
        self.name = name      # Instance variable
        self.grade = grade    # Instance variable
    
    def get_info(self):       # Instance method
        return f"{self.name} is in grade {self.grade}"
    
    @classmethod              # Class method
    def get_school_name(cls):
        return cls.school_name
    
    @staticmethod             # Static method
    def is_passing_grade(grade):
        return grade >= 60

# Using different method types
student = Student("Alice", 85)
print(student.get_info())           # Instance method
print(Student.get_school_name())    # Class method
print(Student.is_passing_grade(85)) # Static method
```

## 🧬 Inheritance

### Basic Inheritance
```python
class Animal:
    def __init__(self, name):
        self.name = name
    
    def speak(self):
        pass

class Dog(Animal):
    def speak(self):
        return f"{self.name} says Woof!"

class Cat(Animal):
    def speak(self):
        return f"{self.name} says Meow!"

# Using inheritance
dog = Dog("Buddy")
cat = Cat("Whiskers")
print(dog.speak())
print(cat.speak())
```

### Multiple Inheritance
```python
class Flyable:
    def fly(self):
        return "I can fly!"

class Swimmable:
    def swim(self):
        return "I can swim!"

class Duck(Flyable, Swimmable):
    def __init__(self, name):
        self.name = name
    
    def quack(self):
        return f"{self.name} says Quack!"

duck = Duck("Donald")
print(duck.fly())
print(duck.swim())
print(duck.quack())
```

## 🔒 Encapsulation

### Private and Protected Members
```python
class BankAccount:
    def __init__(self, account_holder, balance):
        self.account_holder = account_holder  # Public
        self._balance = balance               # Protected (single underscore)
        self.__account_number = "12345"       # Private (double underscore)
    
    def get_balance(self):
        return self._balance
    
    def deposit(self, amount):
        if amount > 0:
            self._balance += amount
            return True
        return False
    
    def withdraw(self, amount):
        if 0 < amount <= self._balance:
            self._balance -= amount
            return True
        return False

account = BankAccount("John", 1000)
print(account.get_balance())
account.deposit(500)
print(account.get_balance())
```

## 🎯 Practice Exercises

### Exercise 1: Rectangle Class
Create a Rectangle class with methods to calculate area and perimeter.

### Exercise 2: Student Management System
Create classes for Student, Course, and School with relationships.

### Exercise 3: Bank Account System
Create a complete banking system with multiple account types.

## 🚀 Mini-Project: Library Management System

```python
class Book:
    def __init__(self, title, author, isbn):
        self.title = title
        self.author = author
        self.isbn = isbn
        self.is_borrowed = False
    
    def borrow(self):
        if not self.is_borrowed:
            self.is_borrowed = True
            return True
        return False
    
    def return_book(self):
        if self.is_borrowed:
            self.is_borrowed = False
            return True
        return False

class Library:
    def __init__(self):
        self.books = []
    
    def add_book(self, book):
        self.books.append(book)
        print(f"Added: {book.title}")
    
    def find_book(self, title):
        for book in self.books:
            if book.title.lower() == title.lower():
                return book
        return None
    
    def list_books(self):
        for book in self.books:
            status = "Borrowed" if book.is_borrowed else "Available"
            print(f"{book.title} by {book.author} - {status}")

# Using the library system
library = Library()
book1 = Book("Python Programming", "John Doe", "123456")
book2 = Book("Data Science", "Jane Smith", "789012")

library.add_book(book1)
library.add_book(book2)
library.list_books()

# Borrowing a book
found_book = library.find_book("Python Programming")
if found_book:
    found_book.borrow()
    library.list_books()
```

## 🎯 Advanced Concepts

### Property Decorators
```python
class Circle:
    def __init__(self, radius):
        self._radius = radius
    
    @property
    def radius(self):
        return self._radius
    
    @radius.setter
    def radius(self, value):
        if value < 0:
            raise ValueError("Radius cannot be negative")
        self._radius = value
    
    @property
    def area(self):
        return 3.14159 * self._radius ** 2

circle = Circle(5)
print(circle.area)
circle.radius = 10
print(circle.area)
```

### Abstract Classes
```python
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self):
        pass
    
    @abstractmethod
    def perimeter(self):
        pass

class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = width
        self.height = height
    
    def area(self):
        return self.width * self.height
    
    def perimeter(self):
        return 2 * (self.width + self.height)

# This would work
rect = Rectangle(5, 3)
print(rect.area())

# This would raise an error
# shape = Shape()  # Can't instantiate abstract class
```

## 📖 Additional Resources

- [Python Classes](https://docs.python.org/3/tutorial/classes.html)
- [OOP in Python](https://realpython.com/python3-object-oriented-programming/)
- [Python Inheritance](https://docs.python.org/3/tutorial/classes.html#inheritance)

## 🎯 Weekly Challenge

Create a complete e-commerce system with:
1. Product class with inventory management
2. Customer class with shopping cart
3. Order class to process purchases
4. Store class to manage everything
5. Implement proper encapsulation and inheritance

## ✅ Checklist

- [ ] Understand OOP principles
- [ ] Can create classes and objects
- [ ] Know how to use constructors
- [ ] Understand instance vs class methods
- [ ] Can implement inheritance
- [ ] Know about encapsulation
- [ ] Can use property decorators
- [ ] Completed all practice exercises
- [ ] Finished mini-project
- [ ] Attempted weekly challenge

---

**Next Week**: [Week 6 - File I/O & Error Handling](./../week6/README.md)