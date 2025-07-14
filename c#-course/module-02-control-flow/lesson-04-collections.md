# Lesson 4: Introduction to Collections (Dictionary, HashSet, Queue, Stack)

## Objectives
- Learn about common collection types in C#
- Understand when to use each collection

## Explanation
### Dictionary
A collection of key-value pairs.
```csharp
using System.Collections.Generic;
Dictionary<string, int> ages = new Dictionary<string, int>();
ages["Alice"] = 30;
ages["Bob"] = 25;
Console.WriteLine(ages["Alice"]); // Output: 30
```

### HashSet
A collection of unique elements.
```csharp
HashSet<int> uniqueNumbers = new HashSet<int>();
uniqueNumbers.Add(1);
uniqueNumbers.Add(2);
uniqueNumbers.Add(1); // Duplicate, will not be added
```

### Queue
First-in, first-out (FIFO) collection.
```csharp
Queue<string> queue = new Queue<string>();
queue.Enqueue("first");
queue.Enqueue("second");
Console.WriteLine(queue.Dequeue()); // Output: first
```

### Stack
Last-in, first-out (LIFO) collection.
```csharp
Stack<string> stack = new Stack<string>();
stack.Push("bottom");
stack.Push("top");
Console.WriteLine(stack.Pop()); // Output: top
```

## Practice Exercises
1. Create a dictionary to store the names and ages of three people, then print each name and age.
2. Use a queue to simulate a line of customers and print the order in which they are served.