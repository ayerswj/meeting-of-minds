-- Solutions: Transactions and Concurrency

-- 1. Create a transaction that transfers money between two accounts
BEGIN;
  UPDATE accounts SET balance = balance - 500 WHERE id = 1;
  UPDATE accounts SET balance = balance + 500 WHERE id = 2;
COMMIT;

-- 2. Create a transaction with a savepoint for partial rollback
BEGIN;
  INSERT INTO employees (first_name, last_name, department_id) 
  VALUES ('John', 'Doe', 1);
  SAVEPOINT sp1;
  INSERT INTO employees (first_name, last_name, department_id) 
  VALUES ('Jane', 'Smith', 2);
  ROLLBACK TO SAVEPOINT sp1;
  INSERT INTO employees (first_name, last_name, department_id) 
  VALUES ('Bob', 'Johnson', 1);
COMMIT;

-- 3. Set isolation level to repeatable read for a transaction
BEGIN TRANSACTION ISOLATION LEVEL REPEATABLE READ;
  SELECT * FROM employees WHERE department_id = 1;
  -- Some other operations
COMMIT;

-- 4. Use row-level locking to prevent concurrent updates
SELECT * FROM employees WHERE id = 1 FOR UPDATE;

-- 5. Create a transaction that handles errors gracefully
BEGIN;
  INSERT INTO departments (name) VALUES ('New Department');
  INSERT INTO employees (first_name, last_name, department_id) 
  VALUES ('Alice', 'Brown', (SELECT id FROM departments WHERE name = 'New Department'));
  -- If any error occurs, the entire transaction will be rolled back
COMMIT;