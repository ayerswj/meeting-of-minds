# Module 3: TypeScript Fundamentals

## Learning Objectives
- Understand what TypeScript is and why to use it
- Use basic TypeScript types and type annotations
- Write and use functions and objects with types
- Use interfaces, type aliases, and advanced types

---

## 1. Why TypeScript?
- TypeScript is a superset of JavaScript that adds static typing.
- Helps catch errors early, improves code quality, and enables better tooling.

---

## 2. Basic Types
- `number`, `string`, `boolean`, `null`, `undefined`, `any`, `void`, `never`, `object`, `array`, `tuple`, `enum`

```ts
let age: number = 30;
let name: string = 'Alice';
let isActive: boolean = true;
let scores: number[] = [90, 80, 70];
let tuple: [string, number] = ['score', 100];
enum Color { Red, Green, Blue }
```

---

## 3. Type Inference & Annotations
- TypeScript can infer types, but you can also annotate explicitly.

```ts
let city = 'London'; // inferred as string
let count: number = 5; // explicit annotation
```

---

## 4. Functions & Objects
- Typing function parameters and return values
- Interfaces and type aliases

```ts
function add(a: number, b: number): number {
  return a + b;
}

interface Person {
  name: string;
  age: number;
}

const user: Person = { name: 'Bob', age: 22 };

type Point = { x: number; y: number };
```

---

## 5. Union, Intersection, Literal Types
- Union: `string | number`
- Intersection: `A & B`
- Literal: `'yes' | 'no'`

```ts
function printId(id: string | number) {
  console.log('ID:', id);
}

type Admin = { role: 'admin' };
type User = { role: 'user' };
type PersonRole = Admin | User;
```

---

## 6. Exercises
- Declare variables with different types
- Write a function with typed parameters and return value
- Create an interface for a car
- Use a union type in a function

See the `exercises/` folder for starter files.

---
[Continue to Intermediate Level](../../intermediate/README.md)