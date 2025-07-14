# Lesson 2: Control Flow

## Conditionals

Control flow allows you to make decisions in your code based on conditions.

### If Statements

```js
let age = 18;

if (age >= 18) {
    console.log("You are an adult");
}
```

### If-Else Statements

```js
let age = 16;

if (age >= 18) {
    console.log("You are an adult");
} else {
    console.log("You are a minor");
}
```

### If-Else If-Else Statements

```js
let score = 85;

if (score >= 90) {
    console.log("Grade: A");
} else if (score >= 80) {
    console.log("Grade: B");
} else if (score >= 70) {
    console.log("Grade: C");
} else {
    console.log("Grade: F");
}
```

### Switch Statements

```js
let day = "Monday";

switch (day) {
    case "Monday":
        console.log("Start of the work week");
        break;
    case "Friday":
        console.log("TGIF!");
        break;
    case "Saturday":
    case "Sunday":
        console.log("Weekend!");
        break;
    default:
        console.log("Regular work day");
}
```

### Ternary Operator

```js
let age = 20;
let status = age >= 18 ? "adult" : "minor";
console.log(status); // "adult"

// Nested ternary
let score = 85;
let grade = score >= 90 ? "A" : 
           score >= 80 ? "B" : 
           score >= 70 ? "C" : "F";
```

## Loops

Loops allow you to execute code multiple times.

### For Loop

```js
// Basic for loop
for (let i = 0; i < 5; i++) {
    console.log(i); // 0, 1, 2, 3, 4
}

// Loop through array
let fruits = ["apple", "banana", "orange"];
for (let i = 0; i < fruits.length; i++) {
    console.log(fruits[i]);
}
```

### While Loop

```js
let count = 0;
while (count < 5) {
    console.log(count);
    count++;
}
```

### Do-While Loop

```js
let count = 0;
do {
    console.log(count);
    count++;
} while (count < 5);
```

### For...Of Loop (ES6)

```js
let fruits = ["apple", "banana", "orange"];
for (let fruit of fruits) {
    console.log(fruit);
}

// Works with strings too
let message = "Hello";
for (let char of message) {
    console.log(char);
}
```

### For...In Loop

```js
let person = {
    name: "John",
    age: 30,
    city: "New York"
};

for (let key in person) {
    console.log(`${key}: ${person[key]}`);
}
```

### Array Methods for Iteration

```js
let numbers = [1, 2, 3, 4, 5];

// forEach
numbers.forEach(function(number) {
    console.log(number);
});

// Arrow function syntax
numbers.forEach(number => console.log(number));
```

## Error Handling

### Try-Catch Statements

```js
try {
    // Code that might throw an error
    let result = 10 / 0;
    console.log(result);
} catch (error) {
    // Handle the error
    console.error("An error occurred:", error.message);
} finally {
    // Code that always runs
    console.log("This always executes");
}
```

### Throwing Errors

```js
function divide(a, b) {
    if (b === 0) {
        throw new Error("Division by zero is not allowed");
    }
    return a / b;
}

try {
    let result = divide(10, 0);
} catch (error) {
    console.error(error.message);
}
```

### Custom Error Classes

```js
class ValidationError extends Error {
    constructor(message) {
        super(message);
        this.name = "ValidationError";
    }
}

function validateAge(age) {
    if (age < 0) {
        throw new ValidationError("Age cannot be negative");
    }
    if (age > 150) {
        throw new ValidationError("Age seems unrealistic");
    }
    return true;
}
```

## Break and Continue

### Break Statement

```js
// Break out of a loop
for (let i = 0; i < 10; i++) {
    if (i === 5) {
        break; // Exit the loop when i equals 5
    }
    console.log(i);
}

// Break in switch statements
let day = "Monday";
switch (day) {
    case "Monday":
        console.log("Start of week");
        break; // Exit the switch
    case "Friday":
        console.log("End of week");
        break;
}
```

### Continue Statement

```js
// Skip the current iteration
for (let i = 0; i < 10; i++) {
    if (i % 2 === 0) {
        continue; // Skip even numbers
    }
    console.log(i); // Only odd numbers
}
```

## Exercises

1. **Grade Calculator**: Write a function that takes a score and returns a grade
2. **Number Guessing**: Create a simple number guessing game
3. **Array Processing**: Loop through an array and perform operations
4. **Error Handling**: Create functions with proper error handling

## Practice Code

```js
// Exercise 1: Grade Calculator
function calculateGrade(score) {
    if (score >= 90) return "A";
    if (score >= 80) return "B";
    if (score >= 70) return "C";
    if (score >= 60) return "D";
    return "F";
}

console.log(calculateGrade(85)); // "B"

// Exercise 2: Number Guessing
let secretNumber = 7;
let guess = 5;

if (guess === secretNumber) {
    console.log("Correct!");
} else if (guess < secretNumber) {
    console.log("Too low!");
} else {
    console.log("Too high!");
}

// Exercise 3: Array Processing
let numbers = [1, 2, 3, 4, 5];
let sum = 0;

for (let number of numbers) {
    sum += number;
}
console.log("Sum:", sum);

// Exercise 4: Error Handling
function safeDivide(a, b) {
    try {
        if (b === 0) {
            throw new Error("Division by zero");
        }
        return a / b;
    } catch (error) {
        console.error("Error:", error.message);
        return null;
    }
}

console.log(safeDivide(10, 2)); // 5
console.log(safeDivide(10, 0)); // null
```

## Key Takeaways

- Use `if-else` for simple conditions, `switch` for multiple values
- `for...of` is great for arrays, `for...in` for object properties
- Always handle potential errors with try-catch
- Use `break` to exit loops early, `continue` to skip iterations
- Ternary operators provide concise conditional expressions