# Module 2: JavaScript Fundamentals

## Learning Objectives
- Understand JavaScript syntax and basic constructs
- Work with variables, data types, and operators
- Use control flow statements (if, switch, loops)
- Write and use functions
- Manipulate objects and arrays
- Use ES6+ features

---

## 1. Syntax & Basics
### Variables
- `var`, `let`, `const`
- Naming conventions

```js
let name = 'Alice';
const age = 25;
var isStudent = true;
```

### Data Types
- String, Number, Boolean, Null, Undefined, Object, Symbol

### Operators
- Arithmetic: `+`, `-`, `*`, `/`, `%`
- Assignment: `=`, `+=`, `-=`
- Comparison: `==`, `===`, `!=`, `!==`, `>`, `<`, `>=`, `<=`
- Logical: `&&`, `||`, `!`

---

## 2. Control Flow
### Conditionals
```js
if (age > 18) {
  console.log('Adult');
} else {
  console.log('Minor');
}
```

### Loops
- `for`, `while`, `do...while`, `for...of`, `for...in`

```js
for (let i = 0; i < 5; i++) {
  console.log(i);
}
```

---

## 3. Functions
- Function declaration, expression, arrow functions
- Parameters, return values
- Scope, closures

```js
function greet(name) {
  return `Hello, ${name}!`;
}

const add = (a, b) => a + b;
```

---

## 4. Objects & Arrays
### Objects
```js
const person = {
  name: 'Alice',
  age: 25
};
console.log(person.name);
```

### Arrays
```js
const numbers = [1, 2, 3, 4];
console.log(numbers[0]);
```

### Destructuring
```js
const { name, age } = person;
const [first, second] = numbers;
```

---

## 5. ES6+ Features
- `let`/`const`
- Template literals
- Default/rest/spread
- Destructuring
- Arrow functions
- Classes

```js
class Animal {
  constructor(name) {
    this.name = name;
  }
  speak() {
    console.log(`${this.name} makes a sound.`);
  }
}
```

---

## 6. Exercises
- Create variables of different types
- Write a function to add two numbers
- Use a loop to print numbers 1-10
- Create an object representing a book
- Use destructuring to extract values from an array

See the `exercises/` folder for starter files.

---
[Next: TypeScript Fundamentals](../03-typescript-fundamentals/README.md)