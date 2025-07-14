# Module 3: Views, Functions, and Procedures

## Learning Objectives
- Create and use views to simplify complex queries
- Write user-defined functions in PostgreSQL
- Understand stored procedures and their use cases
- Implement reusable database logic

---

## 1. Views
Views are virtual tables based on the result set of a SQL statement.

**Creating Views:**
```sql
CREATE VIEW employee_summary AS
SELECT 
  e.first_name,
  e.last_name,
  d.name as department,
  e.salary
FROM employees e
JOIN departments d ON e.department_id = d.id;
```

**Using Views:**
```sql
SELECT * FROM employee_summary WHERE department = 'Engineering';
```

**Updatable Views:**
```sql
CREATE VIEW simple_employees AS
SELECT id, first_name, last_name, department_id
FROM employees;

-- Can perform INSERT, UPDATE, DELETE on simple views
```

## 2. User-Defined Functions
PostgreSQL allows you to create custom functions using PL/pgSQL.

**Basic Function:**
```sql
CREATE OR REPLACE FUNCTION get_employee_count(dept_name VARCHAR)
RETURNS INTEGER AS $$
BEGIN
  RETURN (
    SELECT COUNT(*)
    FROM employees e
    JOIN departments d ON e.department_id = d.id
    WHERE d.name = dept_name
  );
END;
$$ LANGUAGE plpgsql;
```

**Using Functions:**
```sql
SELECT get_employee_count('Engineering');
```

## 3. Stored Procedures
Stored procedures can perform multiple operations and return multiple values.

**Example Procedure:**
```sql
CREATE OR REPLACE PROCEDURE update_employee_salary(
  emp_id INTEGER,
  new_salary NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
  UPDATE employees 
  SET salary = new_salary 
  WHERE id = emp_id;
  
  COMMIT;
END;
$$;
```

**Calling Procedures:**
```sql
CALL update_employee_salary(1, 85000);
```

---

## PostgreSQL Tips
- Views can improve security by hiding sensitive columns
- Functions can return tables, arrays, or custom types
- Use RETURNS TABLE for functions that return multiple rows

---

## Summary
- Views simplify complex queries and improve security
- Functions encapsulate reusable logic
- Procedures handle complex operations with multiple steps

---

## Next Steps
Proceed to the next module to learn about transactions and concurrency!