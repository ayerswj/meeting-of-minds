# Module 1: Intermediate JavaScript

## Learning Objectives
- Manipulate the DOM using JavaScript
- Handle events and create custom events
- Work with asynchronous JavaScript (callbacks, promises, async/await)
- Handle errors effectively
- Use modules and modern tooling

---

## 1. DOM Manipulation
- Selecting elements: `getElementById`, `querySelector`, etc.
- Modifying content and attributes
- Creating and removing elements

```js
const heading = document.querySelector('h1');
heading.textContent = 'Hello, DOM!';
```

---

## 2. Events
- Adding event listeners
- Event delegation
- Creating custom events

```js
document.getElementById('btn').addEventListener('click', () => {
  alert('Button clicked!');
});
```

---

## 3. Asynchronous JavaScript
- Callbacks
- Promises
- Async/await
- Fetch API

```js
fetch('https://api.example.com/data')
  .then(response => response.json())
  .then(data => console.log(data));

async function getData() {
  const response = await fetch('https://api.example.com/data');
  const data = await response.json();
  console.log(data);
}
```

---

## 4. Error Handling
- try/catch
- Custom errors

```js
try {
  throw new Error('Something went wrong');
} catch (e) {
  console.error(e.message);
}
```

---

## 5. Modules & Tooling
- ES modules: `import`/`export`
- CommonJS: `require`/`module.exports`
- Bundlers: Webpack, Vite (overview)

```js
// ES Module
import { add } from './math.js';
export function multiply(a, b) { return a * b; }
```

---

## 6. Exercises
- Manipulate the DOM to change page content
- Add event listeners to buttons
- Fetch data from an API and display it
- Handle errors in asynchronous code

See the `exercises/` folder for starter files.

---
[Next: Intermediate TypeScript](../02-intermediate-typescript/README.md)