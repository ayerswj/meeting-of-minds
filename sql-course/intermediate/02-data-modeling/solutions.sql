-- Solutions: Data Modeling and Constraints

-- 1. Create a departments table with id (primary key) and name (not null)
CREATE TABLE departments (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

-- 2. Create an employees table with foreign key to departments
CREATE TABLE employees (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  department_id INTEGER REFERENCES departments(id),
  salary NUMERIC(10,2) CHECK (salary > 0),
  hire_date DATE DEFAULT CURRENT_DATE
);

-- 3. Create an index on the email column for faster lookups
CREATE INDEX idx_employees_email ON employees(email);

-- 4. Create a projects table with constraints
CREATE TABLE projects (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE CHECK (end_date > start_date),
  budget NUMERIC(12,2) CHECK (budget > 0)
);

-- 5. Create a junction table for employee-project assignments
CREATE TABLE employee_projects (
  employee_id INTEGER REFERENCES employees(id),
  project_id INTEGER REFERENCES projects(id),
  role VARCHAR(50) NOT NULL,
  PRIMARY KEY (employee_id, project_id)
);