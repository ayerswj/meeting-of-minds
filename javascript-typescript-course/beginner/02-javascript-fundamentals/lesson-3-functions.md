# Lesson 3: Functions

## Function Basics

Functions are reusable blocks of code that can be called to perform specific tasks.

### Function Declaration

```js
function greet(name) {
    return `Hello, ${name}!`;
}

console.log(greet("John")); // "Hello, John!"
```

### Function Expression

```js
const greet = function(name) {
    return `Hello, ${name}!`;
};

console.log(greet("John")); // "Hello, John!"
```

### Arrow Functions (ES6)

```js
// Basic arrow function
const greet = (name) => {
    return `Hello, ${name}!`;
};

// Shorthand for single expression
const greet = name => `Hello, ${name}!`;

// Multiple parameters
const add = (a, b) => a + b;

// No parameters
const sayHello = () => "Hello!";

// Multiple lines
const processUser = (name, age) => {
    const greeting = `Hello, ${name}!`;
    const status = age >= 18 ? "adult" : "minor";
    return `${greeting} You are an ${status}.`;
};
```

## Parameters and Arguments

### Default Parameters

```js
function greet(name = "Guest") {
    return `Hello, ${name}!`;
}

console.log(greet()); // "Hello, Guest!"
console.log(greet("John")); // "Hello, John!"

// Arrow function with default parameters
const greet = (name = "Guest") => `Hello, ${name}!`;
```

### Rest Parameters

```js
function sum(...numbers) {
    return numbers.reduce((total, num) => total + num, 0);
}

console.log(sum(1, 2, 3, 4, 5)); // 15

// Arrow function with rest parameters
const sum = (...numbers) => numbers.reduce((total, num) => total + num, 0);
```

### Destructuring Parameters

```js
function processUser({ name, age, city = "Unknown" }) {
    return `${name} is ${age} years old and lives in ${city}.`;
}

const user = { name: "John", age: 30 };
console.log(processUser(user)); // "John is 30 years old and lives in Unknown."

// Arrow function with destructuring
const processUser = ({ name, age, city = "Unknown" }) => 
    `${name} is ${age} years old and lives in ${city}.`;
```

## Return Values

```js
// Explicit return
function add(a, b) {
    return a + b;
}

// Implicit return (arrow functions)
const add = (a, b) => a + b;

// Functions that don't return anything
function logMessage(message) {
    console.log(message);
    // No return statement - returns undefined
}

// Multiple return statements
function getGrade(score) {
    if (score >= 90) return "A";
    if (score >= 80) return "B";
    if (score >= 70) return "C";
    if (score >= 60) return "D";
    return "F";
}
```

## Scope

### Global Scope

```js
let globalVariable = "I'm global";

function testFunction() {
    console.log(globalVariable); // Can access global variable
}

testFunction(); // "I'm global"
```

### Function Scope

```js
function testFunction() {
    let localVariable = "I'm local";
    console.log(localVariable); // Can access local variable
}

testFunction(); // "I'm local"
// console.log(localVariable); // Error: localVariable is not defined
```

### Block Scope (let and const)

```js
if (true) {
    let blockVariable = "I'm in a block";
    const anotherBlockVariable = "I'm also in a block";
    console.log(blockVariable); // Can access
}

// console.log(blockVariable); // Error: blockVariable is not defined
```

### Hoisting

```js
// Function declarations are hoisted
sayHello(); // "Hello!" (works)

function sayHello() {
    console.log("Hello!");
}

// Function expressions are not hoisted
// sayGoodbye(); // Error: sayGoodbye is not a function

const sayGoodbye = function() {
    console.log("Goodbye!");
};
```

## Closures

A closure is a function that has access to variables in its outer (enclosing) scope.

### Basic Closure

```js
function outerFunction(x) {
    return function(y) {
        return x + y; // x is from outer scope
    };
}

const addFive = outerFunction(5);
console.log(addFive(3)); // 8
```

### Practical Closure Example

```js
function createCounter() {
    let count = 0;
    
    return {
        increment: function() {
            count++;
            return count;
        },
        decrement: function() {
            count--;
            return count;
        },
        getCount: function() {
            return count;
        }
    };
}

const counter = createCounter();
console.log(counter.getCount()); // 0
console.log(counter.increment()); // 1
console.log(counter.increment()); // 2
console.log(counter.decrement()); // 1
```

### Closure with Arrow Functions

```js
function createMultiplier(factor) {
    return (number) => number * factor;
}

const double = createMultiplier(2);
const triple = createMultiplier(3);

console.log(double(5)); // 10
console.log(triple(5)); // 15
```

## Higher-Order Functions

Functions that take other functions as arguments or return functions.

```js
// Function as parameter
function processArray(arr, callback) {
    const result = [];
    for (let item of arr) {
        result.push(callback(item));
    }
    return result;
}

const numbers = [1, 2, 3, 4, 5];
const doubled = processArray(numbers, x => x * 2);
console.log(doubled); // [2, 4, 6, 8, 10]

// Function that returns a function
function multiplyBy(factor) {
    return function(number) {
        return number * factor;
    };
}

const multiplyByTwo = multiplyBy(2);
const multiplyByTen = multiplyBy(10);

console.log(multiplyByTwo(5)); // 10
console.log(multiplyByTen(5)); // 50
```

## Immediately Invoked Function Expression (IIFE)

```js
(function() {
    console.log("This function runs immediately!");
})();

// With parameters
(function(name) {
    console.log(`Hello, ${name}!`);
})("John");

// Arrow function IIFE
(() => {
    console.log("Arrow function IIFE");
})();
```

## Exercises

1. **Basic Function**: Create a function that calculates the area of a rectangle
2. **Arrow Function**: Convert the rectangle function to an arrow function
3. **Default Parameters**: Create a function with default parameters
4. **Closure**: Create a function that generates unique IDs
5. **Higher-Order Function**: Create a function that filters an array

## Practice Code

```js
// Exercise 1: Basic Function
function calculateArea(width, height) {
    return width * height;
}

console.log(calculateArea(5, 3)); // 15

// Exercise 2: Arrow Function
const calculateArea = (width, height) => width * height;

// Exercise 3: Default Parameters
const greet = (name = "Guest", greeting = "Hello") => 
    `${greeting}, ${name}!`;

console.log(greet()); // "Hello, Guest!"
console.log(greet("John", "Hi")); // "Hi, John!"

// Exercise 4: Closure
function createIdGenerator() {
    let id = 0;
    return function() {
        return ++id;
    };
}

const generateId = createIdGenerator();
console.log(generateId()); // 1
console.log(generateId()); // 2

// Exercise 5: Higher-Order Function
function filterArray(arr, predicate) {
    const result = [];
    for (let item of arr) {
        if (predicate(item)) {
            result.push(item);
        }
    }
    return result;
}

const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
const evens = filterArray(numbers, x => x % 2 === 0);
console.log(evens); // [2, 4, 6, 8, 10]
```

## Key Takeaways

- Use function declarations for hoisting, function expressions for flexibility
- Arrow functions provide concise syntax and lexical `this` binding
- Closures allow functions to access variables from outer scopes
- Higher-order functions enable functional programming patterns
- Always consider scope when working with variables
- Use default and rest parameters for flexible function signatures