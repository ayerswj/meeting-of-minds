# Module 3: Basic SQL Queries

## Learning Objectives
- Write basic SELECT queries to retrieve data
- Filter results using WHERE
- Sort results with ORDER BY
- Limit and paginate results with LIMIT and OFFSET

---

## 1. SELECT Statements
The SELECT statement retrieves data from one or more tables.

**Syntax:**
```sql
SELECT column1, column2 FROM table_name;
```

**Example:**
```sql
SELECT first_name, last_name FROM employees;
```

## 2. Filtering Data (WHERE)
The WHERE clause filters rows based on a condition.

**Syntax:**
```sql
SELECT * FROM table_name WHERE condition;
```

**Example:**
```sql
SELECT * FROM employees WHERE department = 'Sales';
```

## 3. Sorting Results (ORDER BY)
ORDER BY sorts the result set by one or more columns.

**Syntax:**
```sql
SELECT * FROM table_name ORDER BY column1 [ASC|DESC];
```

**Example:**
```sql
SELECT * FROM employees ORDER BY hire_date DESC;
```

## 4. Limiting Results (LIMIT, OFFSET)
LIMIT restricts the number of rows returned. OFFSET skips a number of rows before starting to return rows.

**Syntax:**
```sql
SELECT * FROM table_name LIMIT n OFFSET m;
```

**Example:**
```sql
SELECT * FROM employees ORDER BY id LIMIT 5 OFFSET 10;
```

---

## PostgreSQL Tips
- PostgreSQL is case-insensitive for unquoted identifiers.
- Use single quotes for string literals.
- LIMIT and OFFSET are especially useful for pagination.

---

## Summary
- SELECT retrieves data
- WHERE filters rows
- ORDER BY sorts results
- LIMIT/OFFSET control result size and pagination

---

## Next Steps
Proceed to the next module to learn how to create and modify tables!