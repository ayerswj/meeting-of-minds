# Lesson 3: Arrays and Lists

## Objectives
- Understand how to store multiple values using arrays and lists
- Learn the syntax for declaring and using arrays and lists in C#

## Explanation
### Arrays
Arrays are fixed-size collections of elements of the same type.
```csharp
int[] numbers = new int[3];
numbers[0] = 10;
numbers[1] = 20;
numbers[2] = 30;
Console.WriteLine(numbers[1]); // Output: 20
```
You can also initialize an array directly:
```csharp
string[] fruits = { "apple", "banana", "cherry" };
```

### Lists
Lists are dynamic collections that can grow or shrink in size.
```csharp
using System.Collections.Generic;
List<string> names = new List<string>();
names.Add("Alice");
names.Add("Bob");
Console.WriteLine(names.Count); // Output: 2
```

## Practice Exercises
1. Create an array of 5 integers and print each value.
2. Create a list of your three favorite movies and print them using a loop.