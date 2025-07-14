# Module 2: Security and User Management

## Learning Objectives
- Create and manage database roles and users
- Implement proper permissions and access control
- Configure authentication methods
- Perform database backups and restores
- Implement security best practices

---

## 1. Roles and Permissions

### Creating Roles
PostgreSQL uses roles for authentication and authorization.

**Creating Roles:**
```sql
-- Create a role
CREATE ROLE app_user WITH LOGIN PASSWORD 'secure_password';

-- Create a role with specific attributes
CREATE ROLE readonly_user WITH LOGIN PASSWORD 'readonly_pass' NOSUPERUSER NOCREATEDB NOCREATEROLE;
```

**Role Attributes:**
- `LOGIN`: Can connect to database
- `SUPERUSER`: Has all privileges
- `CREATEDB`: Can create databases
- `CREATEROLE`: Can create other roles
- `INHERIT`: Inherits privileges from parent roles

### Granting Permissions
```sql
-- Grant specific permissions
GRANT SELECT, INSERT, UPDATE ON employees TO app_user;

-- Grant all permissions on a table
GRANT ALL PRIVILEGES ON employees TO app_user;

-- Grant permissions on all tables in schema
GRANT SELECT ON ALL TABLES IN SCHEMA public TO readonly_user;

-- Grant usage on schema
GRANT USAGE ON SCHEMA public TO app_user;
```

## 2. Row-Level Security (RLS)
RLS allows you to control access to rows based on user context.

**Enabling RLS:**
```sql
-- Enable RLS on table
ALTER TABLE employees ENABLE ROW LEVEL SECURITY;

-- Create policy
CREATE POLICY emp_policy ON employees
  FOR ALL
  USING (department_id = current_setting('app.department_id')::integer);
```

## 3. Authentication Methods

### Password Authentication
```sql
-- In pg_hba.conf
local   all             postgres                                peer
host    all             all             127.0.0.1/32            md5
host    all             all             ::1/128                 md5
```

### SSL/TLS Configuration
```sql
-- In postgresql.conf
ssl = on
ssl_cert_file = 'server.crt'
ssl_key_file = 'server.key'
```

## 4. Backups and Restores

### Logical Backups (pg_dump)
```bash
# Backup entire database
pg_dump -U postgres -d mydb > backup.sql

# Backup specific tables
pg_dump -U postgres -d mydb -t employees > employees_backup.sql

# Backup with custom format
pg_dump -U postgres -d mydb -Fc > backup.dump
```

### Physical Backups (pg_basebackup)
```bash
# Create base backup
pg_basebackup -D /backup/path -Ft -z -P
```

### Restoring Backups
```bash
# Restore from SQL file
psql -U postgres -d mydb < backup.sql

# Restore from custom format
pg_restore -U postgres -d mydb backup.dump
```

## 5. Security Best Practices

### Password Policies
```sql
-- Set password expiration
ALTER ROLE app_user VALID UNTIL '2024-12-31';

-- Require password change
ALTER ROLE app_user PASSWORD 'new_password';
```

### Connection Limits
```sql
-- Limit connections per user
ALTER ROLE app_user CONNECTION LIMIT 10;
```

### Audit Logging
```sql
-- Enable logging in postgresql.conf
log_statement = 'all'
log_connections = on
log_disconnections = on
```

---

## PostgreSQL Tips
- Use least privilege principle
- Regularly rotate passwords
- Monitor failed login attempts
- Use SSL for remote connections
- Implement proper backup strategies

---

## Summary
- Roles and permissions control access
- RLS provides row-level security
- Proper authentication ensures secure access
- Regular backups protect against data loss

---

## Next Steps
Proceed to the next module to learn about performance tuning and optimization!