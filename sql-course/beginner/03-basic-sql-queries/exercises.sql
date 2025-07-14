-- Exercises: Basic SQL Queries

-- 1. Select all columns from the employees table
SELECT * FROM employees;

-- 2. Select the first_name and salary of employees in the 'Marketing' department
SELECT first_name, salary FROM employees WHERE department = 'Marketing';

-- 3. Select all employees hired after 2020-01-01, sorted by hire_date ascending
SELECT * FROM employees WHERE hire_date > '2020-01-01' ORDER BY hire_date ASC;

-- 4. Select the top 3 highest paid employees
SELECT * FROM employees ORDER BY salary DESC LIMIT 3;

-- 5. Select the 6th to 10th employees (by id)
SELECT * FROM employees ORDER BY id LIMIT 5 OFFSET 5;