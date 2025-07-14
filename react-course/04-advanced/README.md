# 04. Advanced React

## Overview
This module covers advanced React concepts and patterns, including custom hooks, reducers, portals, error boundaries, and code splitting. You’ll learn to build robust, scalable, and maintainable applications.

---

## Custom Hooks
Custom hooks let you extract and reuse logic across components.

**Example:**
```jsx
function useLocalStorage(key, initialValue) {
  const [value, setValue] = React.useState(() => {
    const stored = window.localStorage.getItem(key);
    return stored ? JSON.parse(stored) : initialValue;
  });
  React.useEffect(() => {
    window.localStorage.setItem(key, JSON.stringify(value));
  }, [key, value]);
  return [value, setValue];
}
```

**Exercise:**
- Create a custom hook `useToggle` for toggling a boolean value.

---

## useReducer
`useReducer` is useful for managing complex state logic.

**Example:**
```jsx
function reducer(state, action) {
  switch (action.type) {
    case 'increment': return { count: state.count + 1 };
    case 'decrement': return { count: state.count - 1 };
    default: throw new Error();
  }
}

function Counter() {
  const [state, dispatch] = React.useReducer(reducer, { count: 0 });
  return (
    <>
      <p>Count: {state.count}</p>
      <button onClick={() => dispatch({ type: 'increment' })}>+</button>
      <button onClick={() => dispatch({ type: 'decrement' })}>-</button>
    </>
  );
}
```

---

## Advanced useContext
Combine context with custom hooks for scalable state management.

**Example:**
```jsx
const AuthContext = React.createContext();
function useAuth() {
  return React.useContext(AuthContext);
}
```

---

## Portals
Portals let you render children into a DOM node outside the parent component hierarchy (useful for modals, tooltips).

**Example:**
```jsx
import ReactDOM from 'react-dom';
function Modal({ children }) {
  return ReactDOM.createPortal(
    <div className="modal">{children}</div>,
    document.getElementById('modal-root')
  );
}
```

---

## Error Boundaries
Catch JavaScript errors anywhere in the component tree and display a fallback UI.

**Example:**
```jsx
class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false };
  }
  static getDerivedStateFromError(error) {
    return { hasError: true };
  }
  componentDidCatch(error, info) {
    // Log error
  }
  render() {
    if (this.state.hasError) {
      return <h1>Something went wrong.</h1>;
    }
    return this.props.children;
  }
}
```

---

## Code Splitting & Lazy Loading
Split your code into smaller bundles to improve performance. Use `React.lazy` and `Suspense` for dynamic imports.

**Example:**
```jsx
const OtherComponent = React.lazy(() => import('./OtherComponent'));
function App() {
  return (
    <React.Suspense fallback={<div>Loading...</div>}>
      <OtherComponent />
    </React.Suspense>
  );
}
```

---

## Mini-Project: Modal with Error Boundary
- Build a modal using portals
- Wrap it in an error boundary
- Lazy load the modal content

---

## Best Practices
- Use custom hooks for reusable logic
- Use error boundaries for critical UI sections
- Split code for faster load times
- Keep context providers close to where they’re needed

---

## Glossary
- **Custom Hook:** A function that uses React hooks and can be reused
- **Reducer:** Function to manage state transitions
- **Portal:** Render children outside the parent DOM hierarchy
- **Error Boundary:** Component that catches errors in the tree
- **Code Splitting:** Breaking code into smaller chunks for performance

---

**Next:** [State Management](../05-state-management/README.md)