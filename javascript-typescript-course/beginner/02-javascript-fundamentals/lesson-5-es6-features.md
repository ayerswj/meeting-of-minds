# Lesson 5: ES6+ Features

## Let and Const

ES6 introduced `let` and `const` as alternatives to `var` for variable declaration.

### Let (Block-Scoped Variables)

```js
// Block scope
if (true) {
    let x = 10;
    console.log(x); // 10
}
// console.log(x); // Error: x is not defined

// No hoisting
// console.log(y); // Error: Cannot access 'y' before initialization
let y = 20;

// Can be reassigned
let count = 1;
count = 2;
console.log(count); // 2
```

### Const (Block-Scoped Constants)

```js
// Cannot be reassigned
const PI = 3.14159;
// PI = 3.14; // Error: Assignment to constant variable

// But objects and arrays can be modified
const person = { name: "John", age: 30 };
person.age = 31; // This works
console.log(person); // { name: "John", age: 31 }

const numbers = [1, 2, 3];
numbers.push(4); // This works
console.log(numbers); // [1, 2, 3, 4]

// To make objects truly immutable
const immutablePerson = Object.freeze({ name: "John", age: 30 });
// immutablePerson.age = 31; // This won't work in strict mode
```

## Template Literals

Template literals provide an easy way to create multiline strings and embed expressions.

### Basic Template Literals

```js
const name = "John";
const age = 30;

// Traditional string concatenation
const message1 = "Hello, my name is " + name + " and I am " + age + " years old.";

// Template literal
const message2 = `Hello, my name is ${name} and I am ${age} years old.`;

console.log(message1); // "Hello, my name is John and I am 30 years old."
console.log(message2); // "Hello, my name is John and I am 30 years old."
```

### Multiline Strings

```js
// Traditional way (with escape characters)
const multiline1 = "This is a\nmultiline\nstring";

// Template literal
const multiline2 = `
    This is a
    multiline
    string
`;

console.log(multiline1);
console.log(multiline2);
```

### Expressions in Template Literals

```js
const a = 5;
const b = 10;

const result = `${a} + ${b} = ${a + b}`;
console.log(result); // "5 + 10 = 15"

// Function calls
function getGreeting(name) {
    return `Hello, ${name}!`;
}

const greeting = `${getGreeting("John")} How are you today?`;
console.log(greeting); // "Hello, John! How are you today?"

// Conditional expressions
const isLoggedIn = true;
const welcomeMessage = `Welcome ${isLoggedIn ? "back" : "guest"}!`;
console.log(welcomeMessage); // "Welcome back!"
```

## Default Parameters

Default parameters allow you to specify default values for function parameters.

### Basic Default Parameters

```js
function greet(name = "Guest") {
    return `Hello, ${name}!`;
}

console.log(greet()); // "Hello, Guest!"
console.log(greet("John")); // "Hello, John!"

// Multiple default parameters
function createUser(name = "Anonymous", age = 18, city = "Unknown") {
    return { name, age, city };
}

console.log(createUser()); // { name: "Anonymous", age: 18, city: "Unknown" }
console.log(createUser("John", 30)); // { name: "John", age: 30, city: "Unknown" }
```

### Default Parameters with Expressions

```js
function multiply(a, b = a * 2) {
    return a * b;
}

console.log(multiply(5)); // 50 (b defaults to 10)
console.log(multiply(5, 3)); // 15

// Using function calls as defaults
function getRandomNumber() {
    return Math.floor(Math.random() * 100);
}

function createRandomUser(name = "User", id = getRandomNumber()) {
    return { name, id };
}

console.log(createRandomUser()); // { name: "User", id: 42 } (random)
```

## Rest Parameters

Rest parameters allow you to represent an indefinite number of arguments as an array.

### Basic Rest Parameters

```js
function sum(...numbers) {
    return numbers.reduce((total, num) => total + num, 0);
}

console.log(sum(1, 2, 3, 4, 5)); // 15
console.log(sum(10, 20)); // 30
console.log(sum()); // 0

// Rest parameters must be last
function processUser(name, age, ...hobbies) {
    return {
        name,
        age,
        hobbies
    };
}

const user = processUser("John", 30, "reading", "swimming", "coding");
console.log(user); // { name: "John", age: 30, hobbies: ["reading", "swimming", "coding"] }
```

### Rest Parameters with Arrow Functions

```js
const multiply = (...numbers) => {
    return numbers.reduce((product, num) => product * num, 1);
};

console.log(multiply(2, 3, 4)); // 24
```

## Spread Operator

The spread operator allows you to expand elements from arrays or objects.

### Spreading Arrays

```js
const fruits = ["apple", "banana"];
const vegetables = ["carrot", "lettuce"];

// Combining arrays
const food = [...fruits, ...vegetables];
console.log(food); // ["apple", "banana", "carrot", "lettuce"]

// Copying arrays
const fruitsCopy = [...fruits];
console.log(fruitsCopy); // ["apple", "banana"]

// Adding elements
const moreFruits = [...fruits, "orange", "grape"];
console.log(moreFruits); // ["apple", "banana", "orange", "grape"]

// Spreading in function calls
function sum(a, b, c) {
    return a + b + c;
}

const numbers = [1, 2, 3];
console.log(sum(...numbers)); // 6
```

### Spreading Objects

```js
const person = { name: "John", age: 30 };

// Copying objects
const personCopy = { ...person };
console.log(personCopy); // { name: "John", age: 30 }

// Adding properties
const personWithCity = { ...person, city: "New York" };
console.log(personWithCity); // { name: "John", age: 30, city: "New York" }

// Merging objects
const details = { job: "Developer", salary: 50000 };
const fullProfile = { ...person, ...details };
console.log(fullProfile); // { name: "John", age: 30, job: "Developer", salary: 50000 }

// Overriding properties
const updatedPerson = { ...person, age: 31 };
console.log(updatedPerson); // { name: "John", age: 31 }
```

## Destructuring

Destructuring allows you to extract values from arrays or objects into distinct variables.

### Array Destructuring

```js
const fruits = ["apple", "banana", "orange", "grape"];

// Basic destructuring
const [first, second] = fruits;
console.log(first); // "apple"
console.log(second); // "banana"

// Skipping elements
const [first, , third] = fruits;
console.log(first); // "apple"
console.log(third); // "orange"

// Rest operator
const [first, second, ...rest] = fruits;
console.log(first); // "apple"
console.log(rest); // ["orange", "grape"]

// Default values
const [first, second, third, fourth, fifth = "mango"] = fruits;
console.log(fifth); // "mango"

// Swapping variables
let a = 1, b = 2;
[a, b] = [b, a];
console.log(a); // 2
console.log(b); // 1
```

### Object Destructuring

```js
const person = {
    name: "John",
    age: 30,
    city: "New York",
    job: "Developer"
};

// Basic destructuring
const { name, age } = person;
console.log(name); // "John"
console.log(age); // 30

// Destructuring with new variable names
const { name: personName, age: personAge } = person;
console.log(personName); // "John"
console.log(personAge); // 30

// Default values
const { name, age, country = "USA" } = person;
console.log(country); // "USA"

// Nested destructuring
const user = {
    name: "John",
    address: {
        street: "123 Main St",
        city: "New York"
    }
};

const { name, address: { city } } = user;
console.log(city); // "New York"

// Rest operator with objects
const { name, ...otherProps } = person;
console.log(otherProps); // { age: 30, city: "New York", job: "Developer" }
```

### Function Parameter Destructuring

```js
// Object destructuring in parameters
function processUser({ name, age, city = "Unknown" }) {
    return `${name} is ${age} years old and lives in ${city}.`;
}

const user = { name: "John", age: 30 };
console.log(processUser(user)); // "John is 30 years old and lives in Unknown."

// Array destructuring in parameters
function processCoordinates([x, y, z = 0]) {
    return `Position: (${x}, ${y}, ${z})`;
}

const coords = [10, 20];
console.log(processCoordinates(coords)); // "Position: (10, 20, 0)"
```

## Modules

ES6 modules provide a way to organize and share code between files.

### Exporting

```js
// math.js
export const PI = 3.14159;

export function add(a, b) {
    return a + b;
}

export function multiply(a, b) {
    return a * b;
}

// Default export
export default function divide(a, b) {
    return a / b;
}

// Named exports can also be done at the end
const subtract = (a, b) => a - b;
export { subtract };
```

### Importing

```js
// main.js
// Default import
import divide from './math.js';

// Named imports
import { add, multiply, PI } from './math.js';

// Renaming imports
import { add as addNumbers } from './math.js';

// Importing everything as a namespace
import * as MathUtils from './math.js';

console.log(add(5, 3)); // 8
console.log(multiply(4, 2)); // 8
console.log(PI); // 3.14159
console.log(divide(10, 2)); // 5
console.log(MathUtils.add(1, 2)); // 3
```

## Classes

ES6 classes provide a cleaner syntax for creating objects and implementing inheritance.

### Basic Class

```js
class Person {
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }

    greet() {
        return `Hello, my name is ${this.name}`;
    }

    getInfo() {
        return `${this.name} is ${this.age} years old`;
    }
}

const person = new Person("John", 30);
console.log(person.greet()); // "Hello, my name is John"
console.log(person.getInfo()); // "John is 30 years old"
```

### Class with Static Methods

```js
class MathUtils {
    static add(a, b) {
        return a + b;
    }

    static multiply(a, b) {
        return a * b;
    }

    static PI = 3.14159;
}

console.log(MathUtils.add(5, 3)); // 8
console.log(MathUtils.multiply(4, 2)); // 8
console.log(MathUtils.PI); // 3.14159
```

### Inheritance

```js
class Animal {
    constructor(name) {
        this.name = name;
    }

    speak() {
        return `${this.name} makes a sound`;
    }
}

class Dog extends Animal {
    constructor(name, breed) {
        super(name); // Call parent constructor
        this.breed = breed;
    }

    speak() {
        return `${this.name} barks`;
    }

    fetch() {
        return `${this.name} fetches the ball`;
    }
}

const dog = new Dog("Buddy", "Golden Retriever");
console.log(dog.speak()); // "Buddy barks"
console.log(dog.fetch()); // "Buddy fetches the ball"
```

### Getters and Setters

```js
class Circle {
    constructor(radius) {
        this._radius = radius;
    }

    get radius() {
        return this._radius;
    }

    set radius(value) {
        if (value <= 0) {
            throw new Error("Radius must be positive");
        }
        this._radius = value;
    }

    get area() {
        return Math.PI * this._radius ** 2;
    }
}

const circle = new Circle(5);
console.log(circle.radius); // 5
console.log(circle.area); // 78.54...

circle.radius = 10;
console.log(circle.area); // 314.15...

// circle.radius = -5; // Error: Radius must be positive
```

## Exercises

1. **Template Literals**: Create a function that generates HTML using template literals
2. **Destructuring**: Extract specific properties from complex objects
3. **Spread Operator**: Merge arrays and objects efficiently
4. **Classes**: Create a class hierarchy for different types of vehicles
5. **Modules**: Organize code into separate modules

## Practice Code

```js
// Exercise 1: Template Literals
function createUserCard(user) {
    return `
        <div class="user-card">
            <h2>${user.name}</h2>
            <p>Age: ${user.age}</p>
            <p>Email: ${user.email || 'Not provided'}</p>
            <p>Status: ${user.isActive ? 'Active' : 'Inactive'}</p>
        </div>
    `;
}

const user = { name: "John", age: 30, isActive: true };
console.log(createUserCard(user));

// Exercise 2: Destructuring
const product = {
    id: 1,
    name: "Laptop",
    price: 999.99,
    category: "Electronics",
    specs: {
        brand: "Dell",
        model: "XPS 13",
        year: 2023
    }
};

const { name, price, specs: { brand, model } } = product;
console.log(`${name} by ${brand} (${model}) - $${price}`);

// Exercise 3: Spread Operator
const baseConfig = { theme: "dark", language: "en" };
const userConfig = { language: "es", notifications: true };
const finalConfig = { ...baseConfig, ...userConfig };
console.log(finalConfig); // { theme: "dark", language: "es", notifications: true }

// Exercise 4: Classes
class Vehicle {
    constructor(make, model, year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    getInfo() {
        return `${this.year} ${this.make} ${this.model}`;
    }
}

class Car extends Vehicle {
    constructor(make, model, year, doors) {
        super(make, model, year);
        this.doors = doors;
    }

    getInfo() {
        return `${super.getInfo()} (${this.doors} doors)`;
    }
}

const car = new Car("Toyota", "Camry", 2023, 4);
console.log(car.getInfo()); // "2023 Toyota Camry (4 doors)"

// Exercise 5: Default and Rest Parameters
function createProfile(name, age = 18, ...hobbies) {
    return {
        name,
        age,
        hobbies: hobbies.length > 0 ? hobbies : ["reading"]
    };
}

const profile1 = createProfile("John", 30, "swimming", "coding");
const profile2 = createProfile("Jane");
console.log(profile1); // { name: "John", age: 30, hobbies: ["swimming", "coding"] }
console.log(profile2); // { name: "Jane", age: 18, hobbies: ["reading"] }
```

## Key Takeaways

- Use `let` and `const` instead of `var` for better scoping
- Template literals provide cleaner string interpolation
- Default parameters make functions more flexible
- Rest and spread operators are powerful for working with arrays and objects
- Destructuring provides clean syntax for extracting values
- Classes provide a cleaner syntax for object-oriented programming
- Modules help organize and share code between files