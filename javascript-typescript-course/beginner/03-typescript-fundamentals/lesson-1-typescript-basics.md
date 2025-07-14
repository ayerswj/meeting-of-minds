# Lesson 1: TypeScript Basics

## What is TypeScript?

TypeScript is a superset of JavaScript that adds static typing to the language. It was developed by Microsoft and compiles down to JavaScript.

### Why Use TypeScript?

1. **Catch Errors Early**: TypeScript catches type-related errors at compile time
2. **Better IDE Support**: Enhanced autocomplete, refactoring, and navigation
3. **Improved Code Quality**: Self-documenting code with type annotations
4. **Better Team Collaboration**: Clear interfaces and contracts
5. **Safer Refactoring**: Confidence when making changes

### TypeScript vs JavaScript

```js
// JavaScript
function add(a, b) {
    return a + b;
}

add(5, 3); // 8
add("5", 3); // "53" (unexpected result)
add(5, "3"); // "53" (unexpected result)
```

```ts
// TypeScript
function add(a: number, b: number): number {
    return a + b;
}

add(5, 3); // 8
// add("5", 3); // Error: Argument of type 'string' is not assignable to parameter of type 'number'
// add(5, "3"); // Error: Argument of type 'string' is not assignable to parameter of type 'number'
```

## Basic Types

TypeScript provides several basic types that you can use to annotate variables, parameters, and return values.

### Primitive Types

```ts
// String
let name: string = "John";
let message: string = `Hello, ${name}!`;

// Number
let age: number = 30;
let price: number = 19.99;
let binary: number = 0b1010; // Binary
let octal: number = 0o744;   // Octal
let hex: number = 0xf00d;    // Hexadecimal

// Boolean
let isActive: boolean = true;
let isLoggedIn: boolean = false;

// Null and Undefined
let nullValue: null = null;
let undefinedValue: undefined = undefined;

// Symbol
let symbol: symbol = Symbol("description");
```

### Array Types

```ts
// Array of numbers
let numbers: number[] = [1, 2, 3, 4, 5];
let scores: Array<number> = [90, 85, 78, 92];

// Array of strings
let fruits: string[] = ["apple", "banana", "orange"];
let names: Array<string> = ["John", "Jane", "Bob"];

// Array of mixed types
let mixed: (string | number)[] = ["hello", 42, "world", 100];
let mixedArray: Array<string | number> = ["hello", 42, "world", 100];
```

### Tuple Types

Tuples allow you to express an array with a fixed number of elements whose types are known.

```ts
// Tuple with two elements
let coordinates: [number, number] = [10, 20];

// Tuple with different types
let person: [string, number, boolean] = ["John", 30, true];

// Tuple with optional elements
let optionalTuple: [string, number?] = ["hello"];
let optionalTuple2: [string, number?] = ["hello", 42];

// Tuple with rest elements
let tupleWithRest: [string, ...number[]] = ["hello", 1, 2, 3, 4, 5];
```

### Object Types

```ts
// Object type annotation
let person: { name: string; age: number; city?: string } = {
    name: "John",
    age: 30
};

// Object with optional properties
let user: {
    name: string;
    email: string;
    phone?: string;
} = {
    name: "Jane",
    email: "jane@example.com"
    // phone is optional, so we can omit it
};
```

### Any Type

The `any` type allows you to opt out of type checking.

```ts
let anything: any = 4;
anything = "hello";
anything = true;
anything = [1, 2, 3];

// Use sparingly - defeats the purpose of TypeScript
```

### Unknown Type

The `unknown` type is a safer alternative to `any`.

```ts
let unknownValue: unknown = 4;
unknownValue = "hello";
unknownValue = true;

// You can't use unknown values without type checking
// console.log(unknownValue.toUpperCase()); // Error

if (typeof unknownValue === "string") {
    console.log(unknownValue.toUpperCase()); // OK
}
```

### Void Type

The `void` type represents the absence of any type, commonly used for functions that don't return a value.

```ts
function logMessage(message: string): void {
    console.log(message);
    // No return statement
}

function doSomething(): void {
    // This function doesn't return anything
}
```

### Never Type

The `never` type represents values that never occur.

```ts
// Function that never returns (throws an error)
function throwError(message: string): never {
    throw new Error(message);
}

// Function with infinite loop
function infiniteLoop(): never {
    while (true) {
        // This function never returns
    }
}

// Function that always throws
function fail(): never {
    throw new Error("Something went wrong");
}
```

## Type Annotations

### Variable Type Annotations

```ts
// Explicit type annotation
let name: string = "John";
let age: number = 30;
let isActive: boolean = true;

// TypeScript can infer types
let inferredString = "Hello"; // TypeScript infers string
let inferredNumber = 42;      // TypeScript infers number
let inferredBoolean = true;   // TypeScript infers boolean
```

### Function Type Annotations

```ts
// Parameter and return type annotations
function add(a: number, b: number): number {
    return a + b;
}

// Arrow function with type annotations
const multiply = (a: number, b: number): number => {
    return a * b;
};

// Function with no return value
function logMessage(message: string): void {
    console.log(message);
}

// Function that never returns
function throwError(message: string): never {
    throw new Error(message);
}
```

### Optional Parameters

```ts
function greet(name: string, greeting?: string): string {
    if (greeting) {
        return `${greeting}, ${name}!`;
    }
    return `Hello, ${name}!`;
}

console.log(greet("John")); // "Hello, John!"
console.log(greet("John", "Hi")); // "Hi, John!"
```

### Default Parameters

```ts
function createUser(name: string, age: number = 18, city: string = "Unknown"): object {
    return { name, age, city };
}

console.log(createUser("John")); // { name: "John", age: 18, city: "Unknown" }
console.log(createUser("Jane", 25)); // { name: "Jane", age: 25, city: "Unknown" }
console.log(createUser("Bob", 30, "New York")); // { name: "Bob", age: 30, city: "New York" }
```

### Rest Parameters

```ts
function sum(...numbers: number[]): number {
    return numbers.reduce((total, num) => total + num, 0);
}

console.log(sum(1, 2, 3, 4, 5)); // 15
console.log(sum(10, 20)); // 30
```

## Type Inference

TypeScript can often infer types automatically, reducing the need for explicit type annotations.

```ts
// TypeScript infers the type based on the value
let message = "Hello"; // TypeScript infers string
let count = 42;        // TypeScript infers number
let isActive = true;   // TypeScript infers boolean

// TypeScript infers return type
function add(a: number, b: number) {
    return a + b; // TypeScript infers number
}

// TypeScript infers array type
let numbers = [1, 2, 3, 4, 5]; // TypeScript infers number[]

// TypeScript infers object type
let person = {
    name: "John",
    age: 30
}; // TypeScript infers { name: string; age: number }
```

## Type Assertions

Type assertions allow you to tell TypeScript that you know more about a type than it does.

```ts
// Angle-bracket syntax
let someValue: any = "this is a string";
let strLength: number = (<string>someValue).length;

// as syntax (preferred)
let someValue2: any = "this is a string";
let strLength2: number = (someValue2 as string).length;

// Type assertion with unknown
let unknownValue: unknown = "hello world";
let strLength3: number = (unknownValue as string).length;
```

## Exercises

1. **Basic Types**: Create variables with different TypeScript types
2. **Function Types**: Write functions with proper type annotations
3. **Arrays and Tuples**: Work with typed arrays and tuples
4. **Type Inference**: Let TypeScript infer types where possible
5. **Type Assertions**: Use type assertions when needed

## Practice Code

```ts
// Exercise 1: Basic Types
let userName: string = "John";
let userAge: number = 30;
let isLoggedIn: boolean = true;
let userScores: number[] = [85, 90, 78, 92];
let userInfo: [string, number, boolean] = ["John", 30, true];

// Exercise 2: Function Types
function calculateArea(width: number, height: number): number {
    return width * height;
}

const greetUser = (name: string, greeting: string = "Hello"): string => {
    return `${greeting}, ${name}!`;
};

function logError(message: string): void {
    console.error(`Error: ${message}`);
}

// Exercise 3: Arrays and Tuples
let mixedArray: (string | number)[] = ["hello", 42, "world", 100];
let coordinates: [number, number] = [10, 20];
let personTuple: [string, number, string?] = ["John", 30];

// Exercise 4: Type Inference
let inferredString = "TypeScript is great!";
let inferredNumber = 42;
let inferredArray = [1, 2, 3, 4, 5];
let inferredObject = { name: "John", age: 30 };

// Exercise 5: Type Assertions
let someValue: any = "this is a string";
let strLength = (someValue as string).length;

let unknownValue: unknown = "hello world";
if (typeof unknownValue === "string") {
    console.log(unknownValue.toUpperCase());
}
```

## Key Takeaways

- TypeScript adds static typing to JavaScript
- Use explicit type annotations when TypeScript can't infer types
- TypeScript can infer many types automatically
- Use `any` sparingly; prefer `unknown` for better type safety
- Type assertions should be used carefully
- TypeScript helps catch errors at compile time
- Start with basic types and gradually add more complex types