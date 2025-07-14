-- Solutions: Advanced Querying

-- 1. Count the total number of employees in each department
SELECT department, COUNT(*) as employee_count
FROM employees
GROUP BY department;

-- 2. Find the average salary by department, showing only departments with avg salary > 75000
SELECT department, AVG(salary) as avg_salary
FROM employees
GROUP BY department
HAVING AVG(salary) > 75000;

-- 3. Join employees and departments tables to show employee name and department name
SELECT e.first_name, e.last_name, d.name as department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;

-- 4. Find employees who earn more than the company average salary
SELECT first_name, last_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);

-- 5. Show departments with their employee count and average salary
SELECT d.name, COUNT(e.id) as employee_count, AVG(e.salary) as avg_salary
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
GROUP BY d.id, d.name;