# Module 1: Advanced Querying

## Learning Objectives
- Use aggregate functions to summarize data
- Group and filter grouped data with GROUP BY and HAVING
- Join multiple tables using different join types
- Write subqueries and nested SELECT statements

---

## 1. Aggregate Functions
Aggregate functions perform calculations on sets of values and return a single value.

**Common Aggregate Functions:**
- `COUNT()` - Count rows or non-null values
- `SUM()` - Sum of values
- `AVG()` - Average of values
- `MIN()` - Minimum value
- `MAX()` - Maximum value

**Examples:**
```sql
SELECT COUNT(*) FROM employees;
SELECT AVG(salary) FROM employees WHERE department = 'Engineering';
SELECT MIN(hire_date), MAX(hire_date) FROM employees;
```

## 2. GROUP BY and HAVING
GROUP BY groups rows by column values. HAVING filters grouped results.

**Syntax:**
```sql
SELECT column1, aggregate_function(column2)
FROM table_name
GROUP BY column1
HAVING condition;
```

**Example:**
```sql
SELECT department, AVG(salary) as avg_salary
FROM employees
GROUP BY department
HAVING AVG(salary) > 70000;
```

## 3. Joins
Joins combine rows from multiple tables based on related columns.

**Types of Joins:**
- **INNER JOIN**: Returns matching rows from both tables
- **LEFT JOIN**: Returns all rows from left table and matching from right
- **RIGHT JOIN**: Returns all rows from right table and matching from left
- **FULL JOIN**: Returns all rows from both tables

**Example:**
```sql
SELECT e.first_name, e.last_name, d.name as department
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;
```

## 4. Subqueries
Subqueries are queries nested within other queries.

**Example:**
```sql
SELECT first_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);
```

---

## PostgreSQL Tips
- Use aliases for better readability
- PostgreSQL supports lateral joins for complex subqueries
- Use EXPLAIN ANALYZE to understand query performance

---

## Summary
- Aggregate functions summarize data
- GROUP BY groups data, HAVING filters groups
- Joins combine data from multiple tables
- Subqueries enable complex filtering and calculations

---

## Next Steps
Proceed to the next module to learn about data modeling and constraints!