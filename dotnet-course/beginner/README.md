# Beginner Level: .NET Course

Welcome to the Beginner Level of the .NET Course! This section is designed for those with little or no programming experience. By the end, you'll be comfortable with the basics of C# and .NET.

---

## 1. Introduction to .NET
### Lesson 1: What is .NET?
- **Description:** Learn about the .NET platform, its purpose, and its ecosystem.
- **Key Points:**
  - .NET is a free, cross-platform, open-source developer platform for building many kinds of applications.
  - Supports languages like C#, F#, and Visual Basic.
  - Used for web, desktop, mobile, cloud, gaming, IoT, and AI applications.
- **Tips:**
  - Visit the [official .NET website](https://dotnet.microsoft.com/) for news and resources.
- **Exercise:**
  - Research and list 3 companies or products that use .NET. Share your findings with a peer or online community.

### Lesson 2: History and Evolution
- **Description:** Explore the history of .NET, from .NET Framework to .NET Core and .NET 5/6/7+.
- **Key Points:**
  - .NET Framework (Windows-only, 2002)
  - .NET Core (cross-platform, 2016)
  - .NET 5+ (unified platform, 2020+)
- **Exercise:**
  - Create a timeline of .NET releases and major features. Use a diagram tool or draw it by hand.

### Lesson 3: .NET Core vs .NET Framework vs .NET 5/6/7+
- **Description:** Compare the different versions and their use cases.
- **Key Points:**
  - .NET Framework: Windows, legacy apps
  - .NET Core: Cross-platform, modern apps
  - .NET 5/6/7+: Unified, recommended for new projects
- **Exercise:**
  - Identify which .NET version you would use for a cross-platform web app and explain why.

---

## 2. Setting Up Your Environment
### Lesson 1: Installing .NET SDK
- **Description:** Step-by-step guide to installing the .NET SDK on Windows, macOS, and Linux.
- **Instructions:**
  - [Official .NET Download Page](https://dotnet.microsoft.com/download)
  - Verify installation: `dotnet --version`
- **Tips:**
  - Use the LTS (Long Term Support) version for stability.
- **Exercise:**
  - Install the .NET SDK and run `dotnet --info` in your terminal. Paste the output here.

### Lesson 2: Using Visual Studio, VS Code, and CLI
- **Description:** Overview of popular development tools for .NET.
- **Key Points:**
  - Visual Studio: Full-featured IDE (Windows, Mac)
  - Visual Studio Code: Lightweight, cross-platform editor
  - .NET CLI: Command-line interface for building and running .NET apps
- **Tips:**
  - Install the C# extension in VS Code for best experience.
- **Exercise:**
  - Install Visual Studio Code and the C# extension. Open a terminal and run `dotnet new console -o HelloWorld` to create a new project.

### Lesson 3: Your First "Hello World" Application
- **Description:** Create, build, and run your first .NET console application.
- **Example Code:**
  ```csharp
  using System;

  namespace HelloWorld
  {
      class Program
      {
          static void Main(string[] args)
          {
              Console.WriteLine("Hello, World!");
          }
      }
  }
  ```
- **Instructions:**
  1. Open a terminal in your project folder.
  2. Run `dotnet run` to execute the application.
- **Exercise:**
  - Modify the program to print your name below "Hello, World!" and run it again.
  - Try changing the message to something else and observe the result.

---

## 3. C# Fundamentals
### Lesson 1: Variables, Data Types, and Operators
- **Description:** Learn about variables, primitive data types, and operators in C#.
- **Key Points:**
  - Declaring variables: `int age = 25;`
  - Data types: int, double, string, bool, char, etc.
  - Operators: +, -, *, /, %, ==, !=, >, <, &&, ||
- **Example Code:**
  ```csharp
  int age = 30;
  double price = 19.99;
  string name = "Alice";
  bool isActive = true;
  Console.WriteLine($"Name: {name}, Age: {age}, Price: {price}, Active: {isActive}");
  ```
- **Tips:**
  - Use `var` for implicit typing, but know the type!
- **Exercise:**
  - Declare variables for your name, age, and favorite number. Print them in a sentence.
  - Try changing the data type and see what errors you get.

### Lesson 2: Control Structures (if, switch, loops)
- **Description:** Use conditional statements and loops to control program flow.
- **Key Points:**
  - if/else statements
  - switch statements
  - for, while, and do-while loops
- **Example Code:**
  ```csharp
  int number = 5;
  if (number > 0)
      Console.WriteLine("Positive number");
  else
      Console.WriteLine("Non-positive number");

  for (int i = 0; i < 3; i++)
      Console.WriteLine(i);
  ```
- **Exercise:**
  - Write a program that prints numbers 1 to 10 using a loop.
  - Write a program that checks if a number is even or odd.
  - Use a switch statement to print a message for different days of the week.

### Lesson 3: Methods and Parameters
- **Description:** Learn how to write reusable code with methods.
- **Key Points:**
  - Defining and calling methods
  - Method parameters and return values
- **Example Code:**
  ```csharp
  static int Add(int a, int b)
  {
      return a + b;
  }
  // Usage:
  int sum = Add(3, 4);
  Console.WriteLine(sum);
  ```
- **Exercise:**
  - Write a method that takes two numbers and returns their product.
  - Write a method that prints your name.
  - Try writing a method that returns the larger of two numbers.

### Lesson 4: Error Handling (try/catch)
- **Description:** Handle errors gracefully using try/catch blocks.
- **Key Points:**
  - try, catch, finally
  - Exception types
- **Example Code:**
  ```csharp
  try
  {
      int x = int.Parse("abc");
  }
  catch (FormatException ex)
  {
      Console.WriteLine($"Error: {ex.Message}");
  }
  ```
- **Exercise:**
  - Write a program that asks the user for a number and handles invalid input using try/catch.
  - Add a finally block that prints "Done!" after the try/catch.

---

## 4. Object-Oriented Programming in C#
### Lesson 1: Classes and Objects
- **Description:** Understand how to define and use classes and objects.
- **Key Points:**
  - Class definition, fields, properties, methods
  - Creating objects (instances)
- **Example Code:**
  ```csharp
  class Person
  {
      public string Name { get; set; }
      public int Age { get; set; }
  }
  // Usage:
  Person p = new Person { Name = "Bob", Age = 28 };
  Console.WriteLine($"{p.Name} is {p.Age} years old.");
  ```
- **Exercise:**
  - Define a class `Car` with properties for make, model, and year. Create an object and print its details.
  - Add a method to the class that prints a custom message.

### Lesson 2: Inheritance, Polymorphism, Encapsulation, Abstraction
- **Description:** Learn the four pillars of OOP in C#.
- **Key Points:**
  - Inheritance: `class Dog : Animal {}`
  - Polymorphism: method overriding, virtual/override
  - Encapsulation: private fields, public properties
  - Abstraction: abstract classes and methods
- **Example Code:**
  ```csharp
  abstract class Animal
  {
      public abstract void Speak();
  }
  class Dog : Animal
  {
      public override void Speak() => Console.WriteLine("Woof!");
  }
  // Usage:
  Animal myDog = new Dog();
  myDog.Speak();
  ```
- **Exercise:**
  - Create a base class `Shape` and derived classes `Circle` and `Square`. Implement a method to calculate area for each.
  - Try making a method virtual in the base class and override it in the derived class.

### Lesson 3: Interfaces and Abstract Classes
- **Description:** Use interfaces and abstract classes to define contracts and shared behavior.
- **Key Points:**
  - Interface definition: `interface IDrive { void Drive(); }`
  - Implementing interfaces
  - Abstract classes vs interfaces
- **Example Code:**
  ```csharp
  interface IDrive
  {
      void Drive();
  }
  class Car : IDrive
  {
      public void Drive() => Console.WriteLine("Driving...");
  }
  // Usage:
  IDrive myCar = new Car();
  myCar.Drive();
  ```
- **Exercise:**
  - Define an interface `IFlyable` with a method `Fly()`. Implement it in a class `Bird`.
  - Create an abstract class with at least one abstract method and implement it in a derived class.

---

## 5. Working with Collections and LINQ
### Lesson 1: Arrays and Lists
- **Description:** Learn how to store and manipulate groups of data using arrays and lists.
- **Key Points:**
  - Declaring and initializing arrays: `int[] numbers = {1, 2, 3};`
  - Using `List<T>`: `List<string> names = new List<string>();`
  - Adding, removing, and accessing elements
- **Example Code:**
  ```csharp
  int[] numbers = { 1, 2, 3, 4 };
  foreach (int n in numbers)
      Console.WriteLine(n);

  List<string> fruits = new List<string> { "Apple", "Banana" };
  fruits.Add("Cherry");
  Console.WriteLine(fruits[1]); // Banana
  ```
- **Exercise:**
  - Create a list of your three favorite movies and print them.
  - Write a program that sums all numbers in an array.
  - Try removing an item from a list and print the result.

### Lesson 2: Dictionaries
- **Description:** Store key-value pairs using dictionaries.
- **Key Points:**
  - Declaring and using `Dictionary<TKey, TValue>`
  - Adding, removing, and accessing values by key
- **Example Code:**
  ```csharp
  Dictionary<string, int> ages = new Dictionary<string, int>();
  ages["Alice"] = 30;
  ages["Bob"] = 25;
  foreach (var pair in ages)
      Console.WriteLine($"{pair.Key}: {pair.Value}");
  ```
- **Exercise:**
  - Create a dictionary mapping country names to capitals and print each pair.
  - Add a new country-capital pair and print the updated dictionary.

### Lesson 3: Introduction to LINQ
- **Description:** Use LINQ (Language Integrated Query) to query and manipulate collections.
- **Key Points:**
  - LINQ query syntax and method syntax
  - Filtering, selecting, and ordering data
- **Example Code:**
  ```csharp
  List<int> numbers = new List<int> { 1, 2, 3, 4, 5 };
  var evenNumbers = numbers.Where(n => n % 2 == 0).ToList();
  foreach (var n in evenNumbers)
      Console.WriteLine(n);
  ```
- **Exercise:**
  - Given a list of numbers, use LINQ to find all numbers greater than 10.
  - Use LINQ to select the names of all people over 18 from a list of `Person` objects.
  - Try sorting a list of strings alphabetically using LINQ.

---

Congratulations! You have completed the Beginner Level. Move on to the Intermediate Level for more advanced topics like file I/O, serialization, and unit testing.