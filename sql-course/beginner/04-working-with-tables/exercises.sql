-- Exercises: Working with Tables

-- 1. Create a table called departments with id (serial, primary key) and name (varchar)
CREATE TABLE departments (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50)
);

-- 2. Insert three departments: 'Sales', 'Engineering', 'HR'
INSERT INTO departments (name) VALUES ('Sales'), ('Engineering'), ('HR');

-- 3. Update the name of department with id=1 to 'Marketing'
UPDATE departments SET name = 'Marketing' WHERE id = 1;

-- 4. Delete the department with id=3
DELETE FROM departments WHERE id = 3;