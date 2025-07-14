-- Exercises: Introduction to Databases and SQL

-- 1. Select all columns from a table named employees
SELECT * FROM employees;

-- 2. Select only the first_name and last_name from employees
SELECT first_name, last_name FROM employees;

-- 3. Select all employees in the 'Engineering' department
SELECT * FROM employees WHERE department = 'Engineering';

-- 4. Select all unique departments from the employees table
SELECT DISTINCT department FROM employees;