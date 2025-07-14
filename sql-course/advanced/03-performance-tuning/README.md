# Module 3: Performance Tuning and Optimization

## Learning Objectives
- Use EXPLAIN and EXPLAIN ANALYZE to understand query performance
- Optimize indexes for better query performance
- Implement proper vacuuming and maintenance
- Monitor and tune PostgreSQL configuration
- Identify and resolve performance bottlenecks

---

## 1. Query Planning and EXPLAIN

### Understanding Query Plans
PostgreSQL's query planner determines the most efficient way to execute queries.

**Basic EXPLAIN:**
```sql
EXPLAIN SELECT * FROM employees WHERE department = 'Engineering';
```

**EXPLAIN ANALYZE:**
```sql
EXPLAIN (ANALYZE, BUFFERS) 
SELECT e.first_name, d.name 
FROM employees e 
JOIN departments d ON e.department_id = d.id 
WHERE e.salary > 70000;
```

**Understanding Output:**
- **Seq Scan**: Full table scan (usually slow)
- **Index Scan**: Uses index to find rows
- **Bitmap Index Scan**: Uses multiple indexes
- **Hash Join**: Joins tables using hash tables
- **Nested Loop**: Joins tables using nested loops

## 2. Index Optimization

### Types of Indexes
```sql
-- B-tree index (default)
CREATE INDEX idx_employees_department ON employees(department);

-- Partial index (only for specific conditions)
CREATE INDEX idx_high_salary ON employees(salary) 
WHERE salary > 100000;

-- Composite index
CREATE INDEX idx_emp_dept_salary ON employees(department, salary);

-- GIN index for arrays
CREATE INDEX idx_products_tags ON products USING GIN(tags);

-- GiST index for geometric data
CREATE INDEX idx_locations_geom ON locations USING GIST(location);
```

### Index Maintenance
```sql
-- Check index usage
SELECT schemaname, tablename, indexname, idx_scan, idx_tup_read, idx_tup_fetch
FROM pg_stat_user_indexes
ORDER BY idx_scan DESC;

-- Rebuild index
REINDEX INDEX idx_employees_department;

-- Analyze table statistics
ANALYZE employees;
```

## 3. Vacuuming and Maintenance

### VACUUM Operations
```sql
-- Regular vacuum (reclaims space, updates statistics)
VACUUM employees;

-- Full vacuum (locks table, reclaims more space)
VACUUM FULL employees;

-- Analyze only (updates statistics)
ANALYZE employees;

-- Vacuum with analyze
VACUUM ANALYZE employees;
```

### Autovacuum Configuration
```sql
-- Check autovacuum settings
SHOW autovacuum;
SHOW autovacuum_vacuum_threshold;
SHOW autovacuum_analyze_threshold;

-- Monitor autovacuum activity
SELECT schemaname, tablename, last_vacuum, last_autovacuum
FROM pg_stat_user_tables;
```

## 4. Configuration Tuning

### Memory Settings
```sql
-- Shared buffers (25% of RAM for dedicated server)
shared_buffers = 256MB

-- Effective cache size (75% of RAM)
effective_cache_size = 1GB

-- Work memory for sorting and joins
work_mem = 4MB

-- Maintenance work memory
maintenance_work_mem = 64MB
```

### Connection Settings
```sql
-- Maximum connections
max_connections = 100

-- Connection timeout
statement_timeout = '30s'
```

## 5. Performance Monitoring

### System Views
```sql
-- Check active queries
SELECT pid, usename, application_name, state, query 
FROM pg_stat_activity 
WHERE state = 'active';

-- Check table statistics
SELECT schemaname, tablename, n_tup_ins, n_tup_upd, n_tup_del
FROM pg_stat_user_tables
ORDER BY n_tup_ins DESC;

-- Check index usage
SELECT indexrelname, idx_scan, idx_tup_read, idx_tup_fetch
FROM pg_stat_user_indexes
ORDER BY idx_scan DESC;
```

---

## PostgreSQL Tips
- Use EXPLAIN ANALYZE to see actual execution times
- Create indexes based on query patterns
- Monitor slow queries with pg_stat_statements
- Regular VACUUM prevents bloat
- Tune configuration based on workload

---

## Summary
- Query planning shows how PostgreSQL executes queries
- Proper indexing dramatically improves performance
- Regular maintenance prevents performance degradation
- Monitoring helps identify bottlenecks

---

## Next Steps
You've completed the Advanced section! Proceed to the Projects section to apply your knowledge to real-world scenarios.