-- Solutions: Security and User Management

-- 1. Create a role for application users with limited privileges
CREATE ROLE app_user WITH LOGIN PASSWORD 'app_password' NOSUPERUSER NOCREATEDB NOCREATEROLE;

-- 2. Create a read-only role for reporting
CREATE ROLE report_user WITH LOGIN PASSWORD 'report_password' NOSUPERUSER NOCREATEDB NOCREATEROLE;

-- 3. Grant appropriate permissions to app_user
GRANT USAGE ON SCHEMA public TO app_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO app_user;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO app_user;

-- 4. Grant read-only permissions to report_user
GRANT USAGE ON SCHEMA public TO report_user;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO report_user;

-- 5. Enable Row Level Security on employees table
ALTER TABLE employees ENABLE ROW LEVEL SECURITY;

-- 6. Create RLS policy for department-based access
CREATE POLICY dept_policy ON employees
  FOR ALL
  USING (department_id = current_setting('app.department_id')::integer);

-- 7. Create a policy for managers to see all employees
CREATE POLICY manager_policy ON employees
  FOR ALL
  USING (current_setting('app.role') = 'manager');

-- 8. Set connection limit for app_user
ALTER ROLE app_user CONNECTION LIMIT 50;

-- 9. Create a role for database maintenance
CREATE ROLE maintenance_user WITH LOGIN PASSWORD 'maintenance_pass' NOSUPERUSER CREATEDB NOCREATEROLE;

-- 10. Grant maintenance privileges
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO maintenance_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO maintenance_user;

-- 11. Set password expiration for app_user
ALTER ROLE app_user VALID UNTIL '2024-12-31';

-- 12. Create a role that can only access specific tables
CREATE ROLE hr_user WITH LOGIN PASSWORD 'hr_password' NOSUPERUSER NOCREATEDB NOCREATEROLE;
GRANT USAGE ON SCHEMA public TO hr_user;
GRANT SELECT, INSERT, UPDATE ON employees TO hr_user;
GRANT SELECT ON departments TO hr_user;

-- 13. Revoke unnecessary permissions
REVOKE DELETE ON employees FROM hr_user;

-- 14. Create a backup role with specific privileges
CREATE ROLE backup_user WITH LOGIN PASSWORD 'backup_pass' NOSUPERUSER NOCREATEDB NOCREATEROLE;
GRANT CONNECT ON DATABASE mydb TO backup_user;
GRANT USAGE ON SCHEMA public TO backup_user;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO backup_user;

-- 15. Test RLS by setting application context
-- (This would be done in application code)
-- SELECT set_config('app.department_id', '1', false);
-- SELECT set_config('app.role', 'user', false);