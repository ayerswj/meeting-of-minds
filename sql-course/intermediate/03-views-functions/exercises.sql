-- Exercises: Views, Functions, and Procedures

-- 1. Create a view that shows employee name, department, and salary
CREATE VIEW employee_details AS
SELECT 
  e.first_name,
  e.last_name,
  d.name as department,
  e.salary
FROM employees e
JOIN departments d ON e.department_id = d.id;

-- 2. Create a function that returns the average salary for a given department
CREATE OR REPLACE FUNCTION get_dept_avg_salary(dept_name VARCHAR)
RETURNS NUMERIC AS $$
BEGIN
  RETURN (
    SELECT AVG(e.salary)
    FROM employees e
    JOIN departments d ON e.department_id = d.id
    WHERE d.name = dept_name
  );
END;
$$ LANGUAGE plpgsql;

-- 3. Create a procedure that transfers an employee to a different department
CREATE OR REPLACE PROCEDURE transfer_employee(
  emp_id INTEGER,
  new_dept_id INTEGER
)
LANGUAGE plpgsql
AS $$
BEGIN
  UPDATE employees 
  SET department_id = new_dept_id 
  WHERE id = emp_id;
  
  COMMIT;
END;
$$;

-- 4. Create a view that shows department statistics
CREATE VIEW department_stats AS
SELECT 
  d.name,
  COUNT(e.id) as employee_count,
  AVG(e.salary) as avg_salary,
  MIN(e.salary) as min_salary,
  MAX(e.salary) as max_salary
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
GROUP BY d.id, d.name;

-- 5. Create a function that returns employees above a certain salary threshold
CREATE OR REPLACE FUNCTION get_high_earners(salary_threshold NUMERIC)
RETURNS TABLE(first_name VARCHAR, last_name VARCHAR, salary NUMERIC) AS $$
BEGIN
  RETURN QUERY
  SELECT e.first_name, e.last_name, e.salary
  FROM employees e
  WHERE e.salary > salary_threshold
  ORDER BY e.salary DESC;
END;
$$ LANGUAGE plpgsql;