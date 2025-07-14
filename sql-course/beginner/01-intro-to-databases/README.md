# Module 1: Introduction to Databases and SQL

## Learning Objectives
- Understand what a database is and why it’s used
- Distinguish between relational and non-relational databases
- Learn the basics of SQL and its role in data management
- Get introduced to PostgreSQL and its ecosystem

---

## 1. What is a Database?
A **database** is an organized collection of data, generally stored and accessed electronically. Databases are used to store, retrieve, and manage data efficiently for applications, websites, and organizations.

### Key Concepts
- **Data**: Facts, figures, or information stored for later use
- **Database Management System (DBMS)**: Software for creating, managing, and interacting with databases

## 2. Relational vs. Non-Relational Databases
- **Relational Databases (RDBMS)**: Store data in tables (rows and columns). Use SQL for queries. Examples: PostgreSQL, MySQL, Oracle.
- **Non-Relational (NoSQL) Databases**: Store data in formats like documents, key-value pairs, graphs, or wide-columns. Examples: MongoDB, Redis, Cassandra.

### Why Use Relational Databases?
- Structured data
- Powerful querying with SQL
- Data integrity and relationships

## 3. Introduction to SQL
**SQL (Structured Query Language)** is the standard language for interacting with relational databases. It allows you to:
- Create and modify tables
- Insert, update, and delete data
- Query and filter data
- Manage permissions

### Example SQL Query
```sql
SELECT first_name, last_name FROM employees WHERE department = 'Sales';
```

## 4. Introduction to PostgreSQL
**PostgreSQL** is a powerful, open-source object-relational database system. It is known for:
- Standards compliance
- Extensibility (custom types, functions)
- Advanced features (JSON, full-text search, GIS)
- Strong community support

### PostgreSQL Ecosystem
- `psql`: Command-line interface for PostgreSQL
- GUI tools: pgAdmin, DBeaver
- Extensions: PostGIS (geospatial), pg_trgm (text search)

---

## Summary
- Databases store and organize data
- Relational databases use tables and SQL
- PostgreSQL is a leading open-source RDBMS

---

## Next Steps
Proceed to the next module to set up PostgreSQL and get hands-on experience!