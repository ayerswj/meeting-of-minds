# 06. React Ecosystem & Tooling

## Overview
This module introduces essential tools and libraries in the React ecosystem, including routing, data fetching, component libraries, and development tools. You'll learn to build more complex applications with professional tooling.

---

## Routing (React Router)
React Router enables navigation between different views in a single-page application.

**Key Concepts:**
- **Routes:** Define which component to render for each URL
- **Links:** Navigate between routes without page refresh
- **Params:** Pass data through URLs

**Example:**
```jsx
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/about">About</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/user/:id" element={<UserProfile />} />
      </Routes>
    </BrowserRouter>
  );
}
```

**Exercise:**
- Create a simple app with Home, About, and Contact pages using React Router.

---

## Data Fetching (Axios, Fetch API)
- **Fetch API:** Built into browsers, promise-based
- **Axios:** Popular library with better defaults and error handling

**Example (Fetch):**
```jsx
function UserList() {
  const [users, setUsers] = React.useState([]);
  React.useEffect(() => {
    fetch('https://jsonplaceholder.typicode.com/users')
      .then(response => response.json())
      .then(data => setUsers(data));
  }, []);
  return <ul>{users.map(user => <li key={user.id}>{user.name}</li>)}</ul>;
}
```

**Example (Axios):**
```jsx
import axios from 'axios';
function UserList() {
  const [users, setUsers] = React.useState([]);
  React.useEffect(() => {
    axios.get('https://jsonplaceholder.typicode.com/users')
      .then(response => setUsers(response.data));
  }, []);
  return <ul>{users.map(user => <li key={user.id}>{user.name}</li>)}</ul>;
}
```

---

## Environment Variables
Store configuration values (API keys, URLs) outside your code.

**Example:**
```jsx
// .env file
REACT_APP_API_URL=https://api.example.com
REACT_APP_API_KEY=your_api_key

// In your component
const apiUrl = process.env.REACT_APP_API_URL;
```

---

## Linting & Formatting (ESLint, Prettier)
- **ESLint:** Find and fix code problems
- **Prettier:** Format code consistently

**Example (.eslintrc.js):**
```js
module.exports = {
  extends: ['react-app', 'react-app/jest'],
  rules: {
    'no-console': 'warn',
    'prefer-const': 'error',
  },
};
```

---

## Component Libraries (MUI, Ant Design, Chakra UI)
Pre-built UI components for faster development.

**Example (MUI):**
```jsx
import { Button, TextField, Box } from '@mui/material';
function MyForm() {
  return (
    <Box>
      <TextField label="Name" />
      <Button variant="contained">Submit</Button>
    </Box>
  );
}
```

---

## Storybook
Document and showcase your components in isolation.

**Example:**
```jsx
// Button.stories.js
import { Button } from './Button';
export default {
  title: 'Components/Button',
  component: Button,
};
export const Primary = { args: { label: 'Button' } };
```

---

## Mini-Project: Multi-Page App
- Build an app with multiple pages using React Router
- Fetch data from an API and display it
- Use a component library for styling

---

## Best Practices
- Use React Router for navigation
- Handle loading and error states when fetching data
- Use environment variables for configuration
- Set up linting and formatting early

---

## Glossary
- **Routing:** Navigation between different views in a single-page app
- **Fetch API:** Built-in browser API for making HTTP requests
- **Axios:** Popular HTTP client library
- **Environment Variables:** Configuration values stored outside code
- **ESLint:** Tool for finding and fixing code problems
- **Prettier:** Code formatter
- **Component Library:** Collection of pre-built UI components
- **Storybook:** Tool for documenting and showcasing components

---

**Next:** [Testing in React](../07-testing/README.md)