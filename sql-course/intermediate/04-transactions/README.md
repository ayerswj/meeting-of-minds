# Module 4: Transactions and Concurrency

## Learning Objectives
- Understand database transactions and ACID properties
- Work with transaction isolation levels
- Handle locking and prevent deadlocks
- Implement proper transaction management

---

## 1. Transactions (BEGIN, COMMIT, ROLLBACK)
A transaction is a sequence of operations that are treated as a single unit of work.

**ACID Properties:**
- **Atomicity**: All operations succeed or all fail
- **Consistency**: Database remains in valid state
- **Isolation**: Concurrent transactions don't interfere
- **Durability**: Committed changes are permanent

**Example:**
```sql
BEGIN;
  UPDATE accounts SET balance = balance - 100 WHERE id = 1;
  UPDATE accounts SET balance = balance + 100 WHERE id = 2;
COMMIT;
```

## 2. Isolation Levels
PostgreSQL supports four isolation levels:

**Read Uncommitted (not supported in PostgreSQL):**
- Allows dirty reads

**Read Committed (default):**
- Prevents dirty reads
- Allows non-repeatable reads

**Repeatable Read:**
- Prevents dirty and non-repeatable reads
- Allows phantom reads

**Serializable:**
- Highest isolation level
- Prevents all concurrency anomalies

**Setting Isolation Level:**
```sql
BEGIN TRANSACTION ISOLATION LEVEL REPEATABLE READ;
  -- transaction operations
COMMIT;
```

## 3. Locking and Deadlocks
PostgreSQL uses locks to maintain data consistency.

**Row-Level Locks:**
```sql
-- FOR UPDATE locks rows for update
SELECT * FROM employees WHERE id = 1 FOR UPDATE;

-- FOR SHARE locks rows for reading
SELECT * FROM employees WHERE id = 1 FOR SHARE;
```

**Table-Level Locks:**
```sql
LOCK TABLE employees IN ACCESS EXCLUSIVE MODE;
```

**Deadlock Prevention:**
- Always acquire locks in the same order
- Use shorter transactions
- Monitor with `pg_stat_activity`

---

## PostgreSQL Tips
- Use `SAVEPOINT` for partial rollbacks
- Monitor locks with `pg_locks` view
- Use `NOWAIT` to avoid waiting for locks

---

## Summary
- Transactions ensure data consistency
- Isolation levels control concurrency behavior
- Proper locking prevents data corruption
- Deadlocks can be prevented with good practices

---

## Next Steps
Proceed to the Advanced section to learn about advanced PostgreSQL features!