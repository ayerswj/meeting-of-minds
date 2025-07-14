# 09. TypeScript with React

## Overview
This module introduces TypeScript in React applications. You'll learn how to add type safety to your components, props, and state, making your code more reliable and easier to maintain.

---

## Why TypeScript?
TypeScript adds static typing to JavaScript, providing:
- **Better IDE support** with autocomplete and error detection
- **Catch errors early** during development
- **Better documentation** through types
- **Improved refactoring** with confidence

---

## Setting up TypeScript in React
Create a new React app with TypeScript:
```bash
npx create-react-app my-app --template typescript
```

Or add TypeScript to an existing project:
```bash
npm install --save-dev typescript @types/react @types/react-dom
```

---

## Typing Props, State, and Events
**Props:**
```tsx
interface UserProps {
  name: string;
  age?: number; // Optional prop
  onUserClick: (id: number) => void;
}

function User({ name, age, onUserClick }: UserProps) {
  return (
    <div onClick={() => onUserClick(1)}>
      {name} {age && `(${age})`}
    </div>
  );
}
```

**State:**
```tsx
function Counter() {
  const [count, setCount] = React.useState<number>(0);
  const [user, setUser] = React.useState<User | null>(null);
  return <div>{count}</div>;
}
```

**Events:**
```tsx
function Form() {
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
  };
  
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value);
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input onChange={handleChange} />
    </form>
  );
}
```

**Exercise:**
- Create a typed component that accepts a user object and displays their information.

---

## Generics in Components
Use generics for reusable components that work with different types.

**Example:**
```tsx
interface ListProps<T> {
  items: T[];
  renderItem: (item: T) => React.ReactNode;
}

function List<T>({ items, renderItem }: ListProps<T>) {
  return (
    <ul>
      {items.map((item, index) => (
        <li key={index}>{renderItem(item)}</li>
      ))}
    </ul>
  );
}

// Usage
<List
  items={[{ id: 1, name: 'John' }]}
  renderItem={(user) => <span>{user.name}</span>}
/>
```

---

## Common TypeScript Patterns
**Union Types:**
```tsx
type Status = 'loading' | 'success' | 'error';

function StatusDisplay({ status }: { status: Status }) {
  switch (status) {
    case 'loading': return <div>Loading...</div>;
    case 'success': return <div>Success!</div>;
    case 'error': return <div>Error occurred</div>;
  }
}
```

**Type Guards:**
```tsx
function isUser(obj: any): obj is User {
  return obj && typeof obj.name === 'string';
}

function processData(data: unknown) {
  if (isUser(data)) {
    console.log(data.name); // TypeScript knows data is User
  }
}
```

---

## Mini-Project: Typed Todo App
- Convert a JavaScript React app to TypeScript
- Add proper types for todos, users, and events
- Use generics for reusable components

---

## Best Practices
- Start with `any` and gradually add types
- Use interfaces for object shapes
- Use union types for finite sets of values
- Leverage TypeScript's built-in utility types

---

## Glossary
- **TypeScript:** Superset of JavaScript that adds static typing
- **Interface:** Defines the shape of an object
- **Generic:** Reusable type that works with different types
- **Union Type:** Type that can be one of several types
- **Type Guard:** Function that checks if a value is of a certain type

---

**Next:** [React Native (Mobile)](../10-react-native/README.md)