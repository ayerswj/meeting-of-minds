# Module 4: Working with Tables

## Learning Objectives
- Create tables and understand PostgreSQL data types
- Insert, update, and delete data in tables
- Understand table structure and constraints

---

## 1. Creating Tables
Use CREATE TABLE to define a new table and its columns.

**Syntax:**
```sql
CREATE TABLE table_name (
  column1 datatype [constraints],
  column2 datatype [constraints],
  ...
);
```

**Example:**
```sql
CREATE TABLE employees (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  department VARCHAR(50),
  hire_date DATE,
  salary NUMERIC(10,2)
);
```

## 2. PostgreSQL Data Types
- `INTEGER`, `SERIAL` (auto-increment)
- `VARCHAR(n)`, `TEXT`
- `DATE`, `TIMESTAMP`
- `NUMERIC`, `REAL`, `BOOLEAN`

## 3. Inserting Data (INSERT)
**Syntax:**
```sql
INSERT INTO table_name (column1, column2) VALUES (value1, value2);
```
**Example:**
```sql
INSERT INTO employees (first_name, last_name, department, hire_date, salary)
VALUES ('Alice', 'Smith', 'Engineering', '2022-01-15', 80000.00);
```

## 4. Updating Data (UPDATE)
**Syntax:**
```sql
UPDATE table_name SET column1 = value1 WHERE condition;
```
**Example:**
```sql
UPDATE employees SET salary = 85000 WHERE id = 1;
```

## 5. Deleting Data (DELETE)
**Syntax:**
```sql
DELETE FROM table_name WHERE condition;
```
**Example:**
```sql
DELETE FROM employees WHERE id = 1;
```

---

## PostgreSQL Tips
- Use SERIAL for auto-incrementing primary keys
- Always use WHERE with UPDATE/DELETE to avoid affecting all rows
- Use `\d table_name` in psql to describe a table

---

## Summary
- CREATE TABLE defines structure
- INSERT adds data, UPDATE modifies, DELETE removes
- PostgreSQL offers rich data types and constraints

---

## Next Steps
Proceed to the Intermediate section to learn advanced querying and data modeling!