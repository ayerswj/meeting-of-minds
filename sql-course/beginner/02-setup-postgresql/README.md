# Module 2: Setting Up PostgreSQL

## Learning Objectives
- Install PostgreSQL on your operating system
- Perform basic configuration
- Connect to PostgreSQL using the psql command-line tool
- Understand how to create and manage databases

---

## 1. Installing PostgreSQL

### Windows
- Download the installer from https://www.postgresql.org/download/windows/
- Run the installer and follow the prompts
- Set a password for the default 'postgres' user

### macOS
- Use Homebrew:
  ```sh
  brew install postgresql
  brew services start postgresql
  ```
- Or download from https://www.postgresql.org/download/macosx/

### Linux (Ubuntu/Debian)
  ```sh
  sudo apt update
  sudo apt install postgresql postgresql-contrib
  sudo systemctl start postgresql
  sudo systemctl enable postgresql
  ```

### Verify Installation
  ```sh
  psql --version
  ```

---

## 2. Basic Configuration
- The default superuser is `postgres`.
- To switch to the postgres user (Linux):
  ```sh
  sudo -i -u postgres
  ```
- Change the password:
  ```sh
  psql
  \password postgres
  ```
- Data directory: `/var/lib/postgresql/<version>/main` (Linux)

---

## 3. Using psql (PostgreSQL CLI)
- Start psql:
  ```sh
  psql -U postgres
  ```
- Common commands:
  - `\l` : List databases
  - `\c <dbname>` : Connect to a database
  - `\dt` : List tables
  - `\q` : Quit psql

---

## 4. Creating and Managing Databases
- Create a new database:
  ```sql
  CREATE DATABASE testdb;
  ```
- Connect to a database:
  ```sh
  psql -U postgres -d testdb
  ```
- Drop a database:
  ```sql
  DROP DATABASE testdb;
  ```

---

## Troubleshooting Tips
- If you can't connect, check that the PostgreSQL service is running.
- Use `sudo systemctl status postgresql` (Linux) or check Services (Windows).
- Check firewall settings if connecting remotely.

---

## Next Steps
Proceed to the next module to learn basic SQL queries!