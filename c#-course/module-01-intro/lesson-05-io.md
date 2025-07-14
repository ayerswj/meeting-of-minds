# Lesson 5: Input/Output

## Objectives
- Read input from the user
- Output information to the console

## Explanation
### Output
Use `Console.WriteLine()` to print information:
```csharp
Console.WriteLine("Hello, world!");
```

### Input
Use `Console.ReadLine()` to read a line of text from the user:
```csharp
Console.WriteLine("What is your name?");
string name = Console.ReadLine();
Console.WriteLine($"Hello, {name}!");
```

## Practice Exercises
1. Write a program that asks the user for their favorite color and prints a message using their answer.
2. Write a program that asks the user for two numbers, adds them, and prints the result.