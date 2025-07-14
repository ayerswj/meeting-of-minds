# 02. React Fundamentals

## Overview
This module introduces the core concepts of React, including JSX, components, props, state, and rendering. You’ll build your first interactive UIs and understand how React updates the DOM efficiently.

---

## What is JSX?
JSX stands for JavaScript XML. It lets you write HTML-like syntax in your JavaScript code, which React transforms into JavaScript objects.

**Example:**
```jsx
const element = <h1>Hello, world!</h1>;
```

**Why use JSX?**
- Makes UI code more readable and expressive
- Lets you embed JavaScript expressions inside curly braces: `{}`

**Exercise:**
Convert this HTML to JSX:
```html
<ul>
  <li>Apple</li>
  <li>Banana</li>
</ul>
```

---

## Rendering Elements
React elements are the smallest building blocks. They describe what you want to see on the screen.

**Example:**
```jsx
const element = <h1>Hello, React!</h1>;
ReactDOM.render(element, document.getElementById('root'));
```

---

## Components (Function vs Class)
- **Function Components:** The modern standard. Simpler, use hooks for state and side effects.
- **Class Components:** Older, use lifecycle methods and `this.state`.

**Function Component Example:**
```jsx
function Welcome(props) {
  return <h1>Hello, {props.name}!</h1>;
}
```

**Class Component Example:**
```jsx
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}!</h1>;
  }
}
```

**Checkpoint:**
Create a `Greeting` component that takes a `name` prop and displays “Hello, [name]!”.

---

## Props and State
- **Props:** Inputs to components, passed from parent to child. Immutable inside the child.
- **State:** Data managed within a component. Changes over time, triggers re-render.

**Example:**
```jsx
function Counter(props) {
  const [count, setCount] = React.useState(0);
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
```

**Exercise:**
- Add a “Decrement” button to the Counter.

---

## Handling Events
React events are named using camelCase (`onClick`, `onChange`).

**Example:**
```jsx
<button onClick={handleClick}>Click me</button>
```

**Checkpoint:**
- Create a button that toggles text between “ON” and “OFF”.

---

## Conditional Rendering
Show or hide elements based on state or props.

**Example:**
```jsx
{isLoggedIn ? <LogoutButton /> : <LoginButton />}
```

---

## Lists and Keys
Render lists using `map()`. Each child in a list should have a unique `key` prop.

**Example:**
```jsx
const items = ['Apple', 'Banana', 'Orange'];
<ul>
  {items.map(item => <li key={item}>{item}</li>)}
</ul>
```

---

## Basic Styling
- Use regular CSS, CSS Modules, or CSS-in-JS libraries (styled-components, emotion).
- Inline styles: `<div style={{ color: 'red' }}>Hello</div>`

---

## Mini-Project: To-Do List
Build a simple to-do list app:
- Add new tasks
- Mark tasks as complete
- Remove tasks

**Stretch:** Save tasks to localStorage.

---

## Best Practices
- Use function components and hooks for new code
- Keep components small and focused
- Use unique keys for list items
- Avoid mutating state directly

---

## Glossary
- **JSX:** Syntax extension for JavaScript, looks like HTML
- **Component:** Reusable piece of UI
- **Props:** Data passed to components
- **State:** Data managed within a component
- **Key:** Unique identifier for list items

---

**Next:** [Intermediate React](../03-intermediate/README.md)