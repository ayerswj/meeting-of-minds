# Module 1: Advanced JavaScript

## Learning Objectives
- Master OOP in JavaScript (prototypes, inheritance, classes)
- Apply functional programming concepts
- Understand memory management and performance
- Write and run unit tests

---

## 1. OOP in JavaScript
- Prototypes and inheritance
- ES6 classes and encapsulation

```js
class Animal {
  constructor(name) {
    this.name = name;
  }
  speak() {
    console.log(`${this.name} makes a sound.`);
  }
}

class Dog extends Animal {
  speak() {
    console.log(`${this.name} barks.`);
  }
}
```

---

## 2. Functional Programming
- Pure functions, higher-order functions
- Immutability
- Array methods: `map`, `filter`, `reduce`

```js
const numbers = [1, 2, 3];
const doubled = numbers.map(n => n * 2);
```

---

## 3. Memory Management & Performance
- Garbage collection
- Performance optimization tips

---

## 4. Testing
- Unit testing with Jest
- Test-driven development (TDD)

---

## 5. Exercises
- Implement a class hierarchy
- Use functional programming to process data
- Optimize a slow function
- Write unit tests for a function

See the `exercises/` folder for starter files.

---
[Next: Advanced TypeScript](../02-advanced-typescript/README.md)