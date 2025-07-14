# Lesson 4: Objects and Arrays

## Objects

Objects are collections of key-value pairs that represent real-world entities.

### Creating Objects

```js
// Object literal syntax
const person = {
    name: "John",
    age: 30,
    city: "New York"
};

// Constructor function
function Person(name, age, city) {
    this.name = name;
    this.age = age;
    this.city = city;
}

const person2 = new Person("Jane", 25, "Los Angeles");

// Object.create()
const person3 = Object.create(null);
person3.name = "Bob";
person3.age = 35;
```

### Accessing Object Properties

```js
const person = {
    name: "John",
    age: 30,
    city: "New York"
};

// Dot notation
console.log(person.name); // "John"

// Bracket notation
console.log(person["age"]); // 30

// Dynamic property access
const propertyName = "city";
console.log(person[propertyName]); // "New York"
```

### Modifying Objects

```js
const person = {
    name: "John",
    age: 30
};

// Adding properties
person.city = "New York";
person["email"] = "john@example.com";

// Modifying properties
person.age = 31;

// Deleting properties
delete person.email;

console.log(person); // { name: "John", age: 31, city: "New York" }
```

### Object Methods

```js
const person = {
    name: "John",
    age: 30,
    greet: function() {
        return `Hello, my name is ${this.name}`;
    },
    
    // Shorthand method syntax (ES6)
    introduce() {
        return `I'm ${this.name} and I'm ${this.age} years old`;
    }
};

console.log(person.greet()); // "Hello, my name is John"
console.log(person.introduce()); // "I'm John and I'm 30 years old"
```

### Object Destructuring

```js
const person = {
    name: "John",
    age: 30,
    city: "New York",
    email: "john@example.com"
};

// Basic destructuring
const { name, age } = person;
console.log(name); // "John"
console.log(age); // 30

// Destructuring with new variable names
const { name: personName, age: personAge } = person;
console.log(personName); // "John"

// Destructuring with default values
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
```

## Arrays

Arrays are ordered collections of values.

### Creating Arrays

```js
// Array literal
const fruits = ["apple", "banana", "orange"];

// Array constructor
const numbers = new Array(1, 2, 3, 4, 5);

// Array.from()
const arrayFromString = Array.from("hello");
console.log(arrayFromString); // ["h", "e", "l", "l", "o"]

// Array.of()
const arrayOfNumbers = Array.of(1, 2, 3);
```

### Accessing Array Elements

```js
const fruits = ["apple", "banana", "orange", "grape"];

console.log(fruits[0]); // "apple"
console.log(fruits[2]); // "orange"
console.log(fruits.length); // 4

// Accessing the last element
console.log(fruits[fruits.length - 1]); // "grape"
console.log(fruits.at(-1)); // "grape" (ES2022)
```

### Modifying Arrays

```js
const fruits = ["apple", "banana"];

// Adding elements
fruits.push("orange"); // Add to end
fruits.unshift("grape"); // Add to beginning

// Removing elements
fruits.pop(); // Remove from end
fruits.shift(); // Remove from beginning

// Inserting/removing at specific index
fruits.splice(1, 0, "mango"); // Insert at index 1
fruits.splice(2, 1); // Remove 1 element at index 2

console.log(fruits); // ["apple", "mango", "orange"]
```

### Array Methods

#### Adding/Removing Elements

```js
const fruits = ["apple", "banana"];

// push() - add to end
fruits.push("orange");
console.log(fruits); // ["apple", "banana", "orange"]

// pop() - remove from end
const lastFruit = fruits.pop();
console.log(lastFruit); // "orange"

// unshift() - add to beginning
fruits.unshift("grape");
console.log(fruits); // ["grape", "apple", "banana"]

// shift() - remove from beginning
const firstFruit = fruits.shift();
console.log(firstFruit); // "grape"
```

#### Finding Elements

```js
const fruits = ["apple", "banana", "orange", "apple"];

// indexOf() - find first occurrence
console.log(fruits.indexOf("apple")); // 0
console.log(fruits.indexOf("grape")); // -1

// lastIndexOf() - find last occurrence
console.log(fruits.lastIndexOf("apple")); // 3

// includes() - check if element exists
console.log(fruits.includes("banana")); // true
console.log(fruits.includes("grape")); // false

// find() - find first element that satisfies condition
const numbers = [1, 2, 3, 4, 5];
const firstEven = numbers.find(num => num % 2 === 0);
console.log(firstEven); // 2

// findIndex() - find index of first element that satisfies condition
const firstEvenIndex = numbers.findIndex(num => num % 2 === 0);
console.log(firstEvenIndex); // 1
```

#### Transforming Arrays

```js
const numbers = [1, 2, 3, 4, 5];

// map() - transform each element
const doubled = numbers.map(num => num * 2);
console.log(doubled); // [2, 4, 6, 8, 10]

// filter() - create new array with elements that pass test
const evens = numbers.filter(num => num % 2 === 0);
console.log(evens); // [2, 4]

// reduce() - reduce array to single value
const sum = numbers.reduce((total, num) => total + num, 0);
console.log(sum); // 15

// forEach() - execute function for each element
numbers.forEach(num => console.log(num * 2));
```

#### Sorting and Reversing

```js
const fruits = ["banana", "apple", "orange"];

// sort() - sort alphabetically
fruits.sort();
console.log(fruits); // ["apple", "banana", "orange"]

// reverse() - reverse order
fruits.reverse();
console.log(fruits); // ["orange", "banana", "apple"]

// Custom sorting
const numbers = [10, 5, 8, 1, 9];
numbers.sort((a, b) => a - b); // Numeric sort
console.log(numbers); // [1, 5, 8, 9, 10]
```

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
console.log(second); // "banana"
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

## Spread and Rest Operators

### Spread Operator (...)

```js
// Spreading arrays
const fruits = ["apple", "banana"];
const moreFruits = [...fruits, "orange", "grape"];
console.log(moreFruits); // ["apple", "banana", "orange", "grape"]

// Spreading objects
const person = { name: "John", age: 30 };
const personWithCity = { ...person, city: "New York" };
console.log(personWithCity); // { name: "John", age: 30, city: "New York" }

// Copying arrays and objects
const fruitsCopy = [...fruits];
const personCopy = { ...person };

// Merging arrays
const array1 = [1, 2, 3];
const array2 = [4, 5, 6];
const merged = [...array1, ...array2];
console.log(merged); // [1, 2, 3, 4, 5, 6]

// Merging objects
const obj1 = { a: 1, b: 2 };
const obj2 = { c: 3, d: 4 };
const mergedObj = { ...obj1, ...obj2 };
console.log(mergedObj); // { a: 1, b: 2, c: 3, d: 4}
```

### Rest Operator

```js
// In function parameters
function sum(...numbers) {
    return numbers.reduce((total, num) => total + num, 0);
}

console.log(sum(1, 2, 3, 4, 5)); // 15

// In destructuring
const [first, second, ...rest] = [1, 2, 3, 4, 5];
console.log(rest); // [3, 4, 5]

const { name, ...otherProps } = { name: "John", age: 30, city: "NY" };
console.log(otherProps); // { age: 30, city: "NY" }
```

## Exercises

1. **Object Creation**: Create an object representing a book
2. **Array Methods**: Use map, filter, and reduce on an array of numbers
3. **Destructuring**: Extract specific properties from objects and arrays
4. **Spread Operator**: Merge arrays and objects
5. **Object Methods**: Create an object with methods

## Practice Code

```js
// Exercise 1: Object Creation
const book = {
    title: "JavaScript Guide",
    author: "John Doe",
    year: 2023,
    pages: 300,
    isAvailable: true
};

// Exercise 2: Array Methods
const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

const doubled = numbers.map(num => num * 2);
const evens = numbers.filter(num => num % 2 === 0);
const sum = numbers.reduce((total, num) => total + num, 0);

console.log(doubled); // [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
console.log(evens); // [2, 4, 6, 8, 10]
console.log(sum); // 55

// Exercise 3: Destructuring
const { title, author } = book;
const [first, second, ...rest] = numbers;

// Exercise 4: Spread Operator
const fruits = ["apple", "banana"];
const vegetables = ["carrot", "lettuce"];
const food = [...fruits, ...vegetables];

const person = { name: "John", age: 30 };
const personWithJob = { ...person, job: "Developer" };

// Exercise 5: Object Methods
const calculator = {
    add: function(a, b) {
        return a + b;
    },
    subtract(a, b) {
        return a - b;
    },
    multiply: (a, b) => a * b
};

console.log(calculator.add(5, 3)); // 8
console.log(calculator.subtract(10, 4)); // 6
console.log(calculator.multiply(2, 6)); // 12
```

## Key Takeaways

- Objects are collections of key-value pairs
- Arrays are ordered collections of values
- Use destructuring for cleaner code when extracting values
- Spread operator is useful for copying and merging
- Array methods like map, filter, and reduce are powerful for data transformation
- Objects can contain methods and be used to model real-world entities