# Lesson 1: Variables, Data Types, and Operators

## Variables

Variables are containers for storing data values. In JavaScript, you can declare variables using `var`, `let`, or `const`.

### Declaration Keywords

**`var`** (function-scoped, older syntax):
```js
var name = "John";
var age = 25;
```

**`let`** (block-scoped, modern syntax):
```js
let name = "John";
let age = 25;
```

**`const`** (block-scoped, cannot be reassigned):
```js
const PI = 3.14159;
const name = "John";
```

### Naming Conventions

- Use camelCase for variable names: `firstName`, `userAge`
- Start with a letter, underscore, or dollar sign
- Cannot use reserved keywords
- Case-sensitive

## Data Types

JavaScript has several primitive data types:

### 1. String
```js
let message = "Hello, World!";
let name = 'John';
let template = `Hello, ${name}!`; // Template literal
```

### 2. Number
```js
let age = 25;
let price = 19.99;
let infinity = Infinity;
let notANumber = NaN;
```

### 3. Boolean
```js
let isActive = true;
let isLoggedIn = false;
```

### 4. Undefined
```js
let notDefined;
console.log(notDefined); // undefined
```

### 5. Null
```js
let emptyValue = null;
```

### 6. Symbol (ES6)
```js
let symbol = Symbol("description");
```

### 7. Object
```js
let person = {
    name: "John",
    age: 25
};
```

### 8. Array
```js
let colors = ["red", "green", "blue"];
let numbers = [1, 2, 3, 4, 5];
```

## Operators

### Arithmetic Operators
```js
let a = 10;
let b = 5;

console.log(a + b);  // 15 (addition)
console.log(a - b);  // 5 (subtraction)
console.log(a * b);  // 50 (multiplication)
console.log(a / b);  // 2 (division)
console.log(a % b);  // 0 (modulus)
console.log(a ** b); // 100000 (exponentiation)
```

### Assignment Operators
```js
let x = 10;
x += 5;  // x = x + 5
x -= 3;  // x = x - 3
x *= 2;  // x = x * 2
x /= 4;  // x = x / 4
```

### Comparison Operators
```js
let a = 5;
let b = "5";

console.log(a == b);   // true (loose equality)
console.log(a === b);  // false (strict equality)
console.log(a != b);   // false
console.log(a !== b);  // true
console.log(a > 3);    // true
console.log(a < 10);   // true
console.log(a >= 5);   // true
console.log(a <= 4);   // false
```

### Logical Operators
```js
let isLoggedIn = true;
let hasPermission = false;

console.log(isLoggedIn && hasPermission);  // false (AND)
console.log(isLoggedIn || hasPermission);  // true (OR)
console.log(!isLoggedIn);                  // false (NOT)
```

## Type Checking

You can check the type of a variable using `typeof`:
```js
console.log(typeof "Hello");     // "string"
console.log(typeof 42);          // "number"
console.log(typeof true);        // "boolean"
console.log(typeof undefined);   // "undefined"
console.log(typeof null);        // "object" (this is a known bug)
console.log(typeof {});          // "object"
console.log(typeof []);          // "object"
console.log(typeof function(){}); // "function"
```

## Exercises

1. **Variable Declaration**: Create variables for your name, age, and favorite color
2. **Type Conversion**: Convert a string number to an actual number
3. **Template Literals**: Use template literals to create a greeting message
4. **Operators**: Perform various arithmetic and comparison operations

## Practice Code

```js
// Exercise 1: Variable Declaration
let myName = "Your Name";
let myAge = 25;
let favoriteColor = "blue";

// Exercise 2: Type Conversion
let stringNumber = "42";
let actualNumber = parseInt(stringNumber);
console.log(typeof actualNumber); // "number"

// Exercise 3: Template Literals
let greeting = `Hello, my name is ${myName} and I am ${myAge} years old.`;
console.log(greeting);

// Exercise 4: Operators
let num1 = 10;
let num2 = 5;
console.log(num1 + num2);  // 15
console.log(num1 > num2);  // true
console.log(num1 === 10);  // true
```

## Key Takeaways

- Use `let` and `const` instead of `var` for modern JavaScript
- JavaScript is dynamically typed
- Use `===` for strict equality comparisons
- Template literals provide a cleaner way to create strings with variables
- Always check types when needed using `typeof`