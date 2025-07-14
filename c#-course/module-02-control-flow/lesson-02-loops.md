# Lesson 2: Loops (for, while, do-while, foreach)

## Objectives
- Understand how to repeat actions using loops
- Learn the syntax for for, while, do-while, and foreach loops in C#

## Explanation
Loops allow you to execute a block of code multiple times.

### for loop
```csharp
for (int i = 0; i < 5; i++)
{
    Console.WriteLine($"i = {i}");
}
```

### while loop
```csharp
int count = 0;
while (count < 3)
{
    Console.WriteLine("Counting: " + count);
    count++;
}
```

### do-while loop
```csharp
int number;
do
{
    Console.WriteLine("Enter a number (0 to exit):");
    number = int.Parse(Console.ReadLine());
} while (number != 0);
```

### foreach loop
```csharp
string[] colors = { "red", "green", "blue" };
foreach (string color in colors)
{
    Console.WriteLine(color);
}
```

## Practice Exercises
1. Write a program that prints numbers from 1 to 10 using a for loop.
2. Write a program that asks the user to enter numbers until they enter 0, then prints the sum of all entered numbers.