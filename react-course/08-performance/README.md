# 08. Performance Optimization

## Overview
This module teaches you how to identify and fix performance bottlenecks in React applications. You'll learn techniques like memoization, virtualization, and profiling to make your apps faster and more responsive.

---

## Memoization (React.memo, useMemo, useCallback)
Memoization prevents unnecessary re-renders and expensive calculations.

**React.memo:**
```jsx
const ExpensiveComponent = React.memo(function ExpensiveComponent({ data }) {
  return <div>{/* expensive rendering */}</div>;
});
```

**useMemo:**
```jsx
function ExpensiveCalculation({ items }) {
  const expensiveValue = React.useMemo(() => {
    return items.reduce((sum, item) => sum + item.value, 0);
  }, [items]);
  return <div>{expensiveValue}</div>;
}
```

**useCallback:**
```jsx
function Parent() {
  const [count, setCount] = React.useState(0);
  const handleClick = React.useCallback(() => {
    console.log('Button clicked');
  }, []); // Empty dependency array
  return <Child onClick={handleClick} />;
}
```

**Exercise:**
- Optimize a component that re-renders unnecessarily using React.memo.

---

## Virtualization (react-window, react-virtualized)
Render only visible items in large lists to improve performance.

**Example (react-window):**
```jsx
import { FixedSizeList as List } from 'react-window';

function VirtualizedList({ items }) {
  const Row = ({ index, style }) => (
    <div style={style}>Row {items[index]}</div>
  );

  return (
    <List
      height={400}
      itemCount={items.length}
      itemSize={35}
    >
      {Row}
    </List>
  );
}
```

---

## Profiling
Use React DevTools Profiler to identify slow components.

**Example:**
```jsx
import { Profiler } from 'react';

function onRenderCallback(
  id, // the "id" prop of the Profiler tree that has just committed
  phase, // either "mount" (if the tree just mounted) or "update" (if it re-rendered)
  actualDuration, // time spent rendering the committed update
  baseDuration, // estimated time to render the entire subtree without memoization
  startTime, // when React began rendering this update
  commitTime, // when React committed the update
  interactions // the Set of interactions belonging to this update
) {
  console.log('Render time:', actualDuration);
}

function App() {
  return (
    <Profiler id="App" onRender={onRenderCallback}>
      <ExpensiveComponent />
    </Profiler>
  );
}
```

---

## Avoiding Unnecessary Renders
- Use React.memo for components that receive the same props
- Use useMemo for expensive calculations
- Use useCallback for functions passed as props
- Avoid creating objects/arrays in render

**Example:**
```jsx
// Bad: Creates new object on every render
function BadComponent({ items }) {
  const style = { color: 'red' }; // New object every render
  return <div style={style}>{items.map(item => <span key={item.id}>{item.name}</span>)}</div>;
}

// Good: Memoized style
function GoodComponent({ items }) {
  const style = React.useMemo(() => ({ color: 'red' }), []);
  return <div style={style}>{items.map(item => <span key={item.id}>{item.name}</span>)}</div>;
}
```

---

## Mini-Project: Performance Audit
- Profile an existing app and identify slow components
- Apply memoization techniques to optimize performance
- Implement virtualization for a large list

---

## Best Practices
- Only optimize when you have performance issues
- Use React DevTools Profiler to identify bottlenecks
- Memoize expensive calculations and components
- Virtualize large lists

---

## Glossary
- **Memoization:** Caching the result of expensive operations
- **Virtualization:** Rendering only visible items in large lists
- **Profiling:** Measuring performance to identify bottlenecks
- **React.memo:** Higher-order component for memoization
- **useMemo:** Hook for memoizing expensive calculations
- **useCallback:** Hook for memoizing functions

---

**Next:** [TypeScript with React](../09-typescript/README.md)