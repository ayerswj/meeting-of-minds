-- Solutions: Performance Tuning and Optimization

-- 1. Analyze a simple query with EXPLAIN
EXPLAIN SELECT * FROM employees WHERE department = 'Engineering';

-- 2. Analyze a complex query with EXPLAIN ANALYZE
EXPLAIN (ANALYZE, BUFFERS) 
SELECT e.first_name, e.last_name, d.name as department, e.salary
FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE e.salary > 70000
ORDER BY e.salary DESC;

-- 3. Create appropriate indexes for common queries
CREATE INDEX idx_employees_department ON employees(department);
CREATE INDEX idx_employees_salary ON employees(salary);
CREATE INDEX idx_employees_dept_salary ON employees(department, salary);

-- 4. Create a partial index for high-salary employees
CREATE INDEX idx_high_salary_employees ON employees(salary, department)
WHERE salary > 100000;

-- 5. Create a GIN index for array columns (if you have products table with tags)
-- CREATE INDEX idx_products_tags ON products USING GIN(tags);

-- 6. Analyze table statistics
ANALYZE employees;
ANALYZE departments;

-- 7. Check index usage statistics
SELECT 
  schemaname, 
  tablename, 
  indexname, 
  idx_scan, 
  idx_tup_read, 
  idx_tup_fetch
FROM pg_stat_user_indexes
ORDER BY idx_scan DESC;

-- 8. Check table statistics
SELECT 
  schemaname, 
  tablename, 
  n_tup_ins, 
  n_tup_upd, 
  n_tup_del,
  last_vacuum,
  last_autovacuum
FROM pg_stat_user_tables
ORDER BY n_tup_ins DESC;

-- 9. Perform vacuum operations
VACUUM ANALYZE employees;
VACUUM ANALYZE departments;

-- 10. Check for slow queries (requires pg_stat_statements extension)
-- CREATE EXTENSION pg_stat_statements;
-- SELECT query, calls, total_time, mean_time
-- FROM pg_stat_statements
-- ORDER BY mean_time DESC
-- LIMIT 10;

-- 11. Monitor active connections
SELECT 
  pid, 
  usename, 
  application_name, 
  state, 
  query_start,
  state_change
FROM pg_stat_activity 
WHERE state = 'active';

-- 12. Check for locks
SELECT 
  l.pid,
  l.mode,
  l.granted,
  a.usename,
  a.query
FROM pg_locks l
JOIN pg_stat_activity a ON l.pid = a.pid
WHERE NOT l.granted;

-- 13. Analyze query performance with different index combinations
-- Test query with different index scenarios
EXPLAIN (ANALYZE, BUFFERS) 
SELECT * FROM employees 
WHERE department = 'Engineering' AND salary > 80000;

-- 14. Check table and index sizes
SELECT 
  schemaname,
  tablename,
  pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) as size
FROM pg_tables
WHERE schemaname = 'public'
ORDER BY pg_total_relation_size(schemaname||'.'||tablename) DESC;

-- 15. Monitor vacuum activity
SELECT 
  schemaname,
  tablename,
  last_vacuum,
  last_autovacuum,
  vacuum_count,
  autovacuum_count
FROM pg_stat_user_tables
ORDER BY last_vacuum DESC NULLS LAST;