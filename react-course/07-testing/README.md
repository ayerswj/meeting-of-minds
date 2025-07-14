# 07. Testing in React

## Overview
This module covers testing strategies for React applications, including unit testing, integration testing, and end-to-end testing. You'll learn to write reliable tests that ensure your components work correctly.

---

## Unit Testing (Jest, React Testing Library)
Unit tests verify that individual components work as expected.

**Key Concepts:**
- **Jest:** Testing framework
- **React Testing Library:** Utilities for testing React components
- **User-centric testing:** Test behavior, not implementation

**Example:**
```jsx
import { render, screen, fireEvent } from '@testing-library/react';
import Counter from './Counter';

test('renders counter with initial value', () => {
  render(<Counter />);
  expect(screen.getByText('Count: 0')).toBeInTheDocument();
});

test('increments counter when button is clicked', () => {
  render(<Counter />);
  fireEvent.click(screen.getByText('Increment'));
  expect(screen.getByText('Count: 1')).toBeInTheDocument();
});
```

**Exercise:**
- Write tests for a simple form component that validates input.

---

## End-to-End Testing (Cypress, Playwright)
E2E tests simulate user interactions across the entire application.

**Example (Cypress):**
```js
describe('Todo App', () => {
  it('adds a new todo', () => {
    cy.visit('/');
    cy.get('[data-testid="todo-input"]').type('Buy groceries');
    cy.get('[data-testid="add-button"]').click();
    cy.get('[data-testid="todo-list"]').should('contain', 'Buy groceries');
  });
});
```

---

## Mocking APIs
Mock external dependencies to test components in isolation.

**Example:**
```jsx
import { rest } from 'msw';
import { setupServer } from 'msw/node';

const server = setupServer(
  rest.get('/api/users', (req, res, ctx) => {
    return res(ctx.json([{ id: 1, name: 'John' }]));
  })
);

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());
```

---

## Test Coverage
Measure how much of your code is tested.

**Example:**
```bash
npm test -- --coverage
```

---

## Mini-Project: Testing a Todo App
- Write unit tests for todo components
- Write integration tests for the todo workflow
- Set up E2E tests with Cypress

---

## Best Practices
- Test user behavior, not implementation details
- Use meaningful test descriptions
- Keep tests simple and focused
- Mock external dependencies

---

## Glossary
- **Unit Test:** Test for individual functions or components
- **Integration Test:** Test for how components work together
- **E2E Test:** Test that simulates user interactions
- **Mock:** Replace real dependencies with fake ones
- **Test Coverage:** Percentage of code covered by tests

---

**Next:** [Performance Optimization](../08-performance/README.md)