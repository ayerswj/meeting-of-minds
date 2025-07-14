# Lesson 1: Conditional Statements (if, else, switch)

## Objectives
- Understand how to use conditional statements to control program flow
- Learn the syntax for if, else if, else, and switch statements in C#

## Explanation
Conditional statements allow your program to make decisions based on conditions.

### if, else if, else
```csharp
int age = 18;
if (age >= 18)
{
    Console.WriteLine("You are an adult.");
}
else
{
    Console.WriteLine("You are not an adult.");
}
```

### switch
```csharp
string day = "Monday";
switch (day)
{
    case "Monday":
        Console.WriteLine("Start of the week!");
        break;
    case "Friday":
        Console.WriteLine("Almost weekend!");
        break;
    default:
        Console.WriteLine("Just another day.");
        break;
}
```

## Practice Exercises
1. Write a program that checks if a number is positive, negative, or zero.
2. Write a program that prints a message based on the value of a variable representing the day of the week (use switch).