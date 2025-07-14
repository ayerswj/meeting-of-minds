# 05. State Management

## Overview
This module explores how to manage state in React, from local state to global state using Redux and modern alternatives. You’ll also learn about server state management with React Query.

---

## Local State vs Global State
- **Local State:** Managed within a single component using `useState` or `useReducer`.
- **Global State:** Shared across multiple components (e.g., user authentication, theme).

**Example (local state):**
```jsx
const [count, setCount] = React.useState(0);
```

**When to use global state?**
- When multiple components need access to the same data (e.g., user info, cart items).

---

## Redux (Toolkit)
Redux is a popular library for managing global state. Redux Toolkit is the recommended way to use Redux today.

**Key Concepts:**
- **Store:** Holds the state
- **Actions:** Describe state changes
- **Reducers:** Handle actions and update state
- **Selectors:** Extract data from the state

**Example (Redux Toolkit slice):**
```js
import { createSlice } from '@reduxjs/toolkit';

const counterSlice = createSlice({
  name: 'counter',
  initialState: { value: 0 },
  reducers: {
    increment: state => { state.value += 1 },
    decrement: state => { state.value -= 1 },
  },
});

export const { increment, decrement } = counterSlice.actions;
export default counterSlice.reducer;
```

**Exercise:**
- Set up Redux Toolkit in a React app and create a counter slice.

---

## Zustand, Jotai, Recoil (Modern Alternatives)
These libraries offer simpler APIs for global state management.
- **Zustand:** Minimal, uses hooks
- **Jotai:** Atomic state management
- **Recoil:** Facebook’s experimental state library

**Example (Zustand):**
```js
import create from 'zustand';
const useStore = create(set => ({ count: 0, inc: () => set(state => ({ count: state.count + 1 })) }));
```

---

## React Query / TanStack Query
Manages server state (data fetched from APIs), caching, and background updates.

**Example:**
```js
import { useQuery } from '@tanstack/react-query';
function Todos() {
  const { data, isLoading } = useQuery(['todos'], fetchTodos);
  if (isLoading) return 'Loading...';
  return <ul>{data.map(todo => <li key={todo.id}>{todo.text}</li>)}</ul>;
}
```

---

## Mini-Project: Global Theme Switcher
- Use Zustand or Redux to manage a global theme (light/dark)
- Add a button to toggle the theme across the app

---

## Best Practices
- Use local state for UI-specific data
- Use global state sparingly (only when needed)
- Use Redux Toolkit for scalable apps
- Use React Query for server data

---

## Glossary
- **Store:** Central place for app state
- **Action:** Object describing a state change
- **Reducer:** Function that updates state
- **Selector:** Function to extract data from state
- **Server State:** Data fetched from an external source (API)

---

**Next:** [React Ecosystem & Tooling](../06-ecosystem-tooling/README.md)