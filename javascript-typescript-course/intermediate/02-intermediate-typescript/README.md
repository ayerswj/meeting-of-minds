# Module 2: Intermediate TypeScript

## Learning Objectives
- Use generics in functions and classes
- Apply type narrowing and guards
- Organize code with modules and namespaces
- Use TypeScript with JavaScript libraries

---

## 1. Generics
- Generic functions and classes
- Constraints

```ts
function identity<T>(value: T): T {
  return value;
}

class Box<T> {
  contents: T;
  constructor(value: T) {
    this.contents = value;
  }
}
```

---

## 2. Type Narrowing & Guards
- `typeof`, `instanceof`, custom type guards

```ts
function print(value: string | number) {
  if (typeof value === 'string') {
    console.log('String:', value);
  } else {
    console.log('Number:', value);
  }
}
```

---

## 3. Modules & Namespaces
- ES modules and TypeScript namespaces

```ts
// module.ts
export function greet(name: string) {
  return `Hello, ${name}`;
}

// usage
import { greet } from './module';
```

---

## 4. TypeScript with JavaScript Libraries
- Using DefinitelyTyped (`@types`)
- Writing declaration files

---

## 5. Exercises
- Write a generic function
- Use type guards in a function
- Organize code using modules
- Use a JavaScript library with TypeScript

See the `exercises/` folder for starter files.

---
[Continue to Advanced Level](../../advanced/README.md)