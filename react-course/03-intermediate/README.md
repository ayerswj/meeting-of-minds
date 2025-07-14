# 03. Intermediate React

## Overview
This module deepens your understanding of React by introducing hooks, component lifecycle, forms, and context. You’ll learn to manage more complex state and build more interactive UIs.

---

## Component Lifecycle
React components go through a lifecycle: mounting, updating, and unmounting.
- **Class Components:** Use lifecycle methods like `componentDidMount`, `componentDidUpdate`, `componentWillUnmount`.
- **Function Components:** Use hooks like `useEffect` to handle side effects.

**Example (useEffect):**
```jsx
import React, { useEffect } from 'react';

function Timer() {
  useEffect(() => {
    const id = setInterval(() => {
      console.log('Tick');
    }, 1000);
    return () => clearInterval(id); // Cleanup
  }, []); // Empty array = run once
  return <div>Timer running (check console)</div>;
}
```

---

## useState, useEffect, useRef
- **useState:** Add state to function components.
- **useEffect:** Run side effects (fetching data, timers, subscriptions).
- **useRef:** Access DOM nodes or persist values across renders.

**Example (useRef):**
```jsx
import React, { useRef } from 'react';

function FocusInput() {
  const inputRef = useRef();
  return (
    <>
      <input ref={inputRef} />
      <button onClick={() => inputRef.current.focus()}>Focus</button>
    </>
  );
}
```

---

## Lifting State Up
When multiple components need to share state, move the state to their closest common ancestor.

**Example:**
```jsx
function Parent() {
  const [value, setValue] = React.useState('');
  return (
    <>
      <Input value={value} setValue={setValue} />
      <Display value={value} />
    </>
  );
}
```

---

## Controlled vs Uncontrolled Components
- **Controlled:** Form data is handled by React state.
- **Uncontrolled:** Form data is handled by the DOM (useRef).

**Controlled Example:**
```jsx
function ControlledInput() {
  const [value, setValue] = React.useState('');
  return <input value={value} onChange={e => setValue(e.target.value)} />;
}
```

**Uncontrolled Example:**
```jsx
function UncontrolledInput() {
  const inputRef = React.useRef();
  const handleSubmit = e => {
    e.preventDefault();
    alert(inputRef.current.value);
  };
  return (
    <form onSubmit={handleSubmit}>
      <input ref={inputRef} />
      <button type="submit">Submit</button>
    </form>
  );
}
```

---

## Forms in React
- Use `onChange` to update state as the user types.
- Validate input before submitting.

**Exercise:**
- Build a form that collects a user’s name and email, and displays them on submit.

---

## Context API (Basic)
Context lets you pass data deeply without prop drilling.

**Example:**
```jsx
const ThemeContext = React.createContext('light');

function App() {
  return (
    <ThemeContext.Provider value="dark">
      <Toolbar />
    </ThemeContext.Provider>
  );
}

function Toolbar() {
  return <ThemedButton />;
}

function ThemedButton() {
  const theme = React.useContext(ThemeContext);
  return <button className={theme}>I am {theme} themed!</button>;
}
```

---

## Mini-Project: User Profile Form
- Build a form to collect and display user profile info (name, email, bio).
- Use controlled components and validate input.
- Use context to manage theme (light/dark).

---

## Best Practices
- Use hooks instead of class components for new code
- Keep effects clean (return a cleanup function in useEffect)
- Avoid prop drilling by using context
- Validate and sanitize user input

---

## Glossary
- **Lifecycle:** The phases a component goes through (mount, update, unmount)
- **Hook:** Function that lets you use React features in function components
- **Controlled Component:** Form element controlled by React state
- **Context:** Way to pass data through the component tree without props

---

**Next:** [Advanced React](../04-advanced/README.md)