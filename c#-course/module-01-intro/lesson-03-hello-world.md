# Lesson 3: Your First C# Program: Hello World

## Objectives
- Write and run your first C# program
- Understand the structure of a basic C# application

## Code Example
```csharp
using System;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("Hello World!");
    }
}
```

## Explanation
- `using System;` imports the System namespace, which contains basic classes like Console.
- `class Program` defines a class named Program.
- `static void Main(string[] args)` is the entry point of the application.
- `Console.WriteLine("Hello World!");` prints text to the console.

## Practice Exercise
- Modify the program to print your name and your favorite programming language.
- Example output:
  ```
  Hello World!
  My name is Alice.
  My favorite programming language is C#.
  ```