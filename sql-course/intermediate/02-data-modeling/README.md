# Module 2: Data Modeling and Constraints

## Learning Objectives
- Understand and implement primary keys and foreign keys
- Apply various constraints (UNIQUE, NOT NULL, CHECK)
- Create and use indexes for performance
- Design proper database relationships

---

## 1. Primary Keys and Foreign Keys
**Primary Key**: Uniquely identifies each row in a table
**Foreign Key**: References a primary key in another table

**Example:**
```sql
CREATE TABLE departments (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE employees (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  department_id INTEGER REFERENCES departments(id)
);
```

## 2. Constraints
Constraints enforce data integrity rules.

**Types of Constraints:**
- **NOT NULL**: Column cannot contain NULL values
- **UNIQUE**: All values in column must be unique
- **CHECK**: Values must satisfy a condition
- **DEFAULT**: Provides default value for column

**Example:**
```sql
CREATE TABLE employees (
  id SERIAL PRIMARY KEY,
  email VARCHAR(100) UNIQUE NOT NULL,
  salary NUMERIC(10,2) CHECK (salary > 0),
  hire_date DATE DEFAULT CURRENT_DATE
);
```

## 3. Indexes and Performance
Indexes improve query performance by creating data structures for faster lookups.

**Creating Indexes:**
```sql
CREATE INDEX idx_employees_department ON employees(department);
CREATE INDEX idx_employees_salary ON employees(salary);
```

**PostgreSQL Index Types:**
- B-tree (default)
- Hash
- GiST (geometric)
- GIN (full-text search)

---

## PostgreSQL Tips
- Use SERIAL for auto-incrementing primary keys
- Consider partial indexes for filtered queries
- Use EXPLAIN to analyze query performance

---

## Summary
- Primary keys uniquely identify rows
- Foreign keys establish relationships
- Constraints ensure data integrity
- Indexes improve query performance

---

## Next Steps
Proceed to the next module to learn about views, functions, and procedures!