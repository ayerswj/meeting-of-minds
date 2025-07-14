# Module 1: Advanced PostgreSQL Features

## Learning Objectives
- Work with JSON and JSONB data types
- Use array data types effectively
- Implement full-text search capabilities
- Understand PostgreSQL extensions like PostGIS
- Implement table partitioning and sharding

---

## 1. JSON and Array Data Types

### JSON Data Types
PostgreSQL supports two JSON types: `JSON` and `JSONB`.

**JSON vs JSONB:**
- `JSON`: Stored as text, preserves whitespace and key order
- `JSONB`: Stored in binary format, more efficient for querying

**Creating JSON Columns:**
```sql
CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  metadata JSONB,
  tags TEXT[]
);
```

**JSON Operations:**
```sql
-- Insert JSON data
INSERT INTO products (name, metadata) VALUES (
  'Laptop',
  '{"brand": "Dell", "specs": {"ram": "16GB", "storage": "512GB"}}'
);

-- Query JSON data
SELECT name, metadata->>'brand' as brand
FROM products
WHERE metadata->>'brand' = 'Dell';

-- Query nested JSON
SELECT name, metadata->'specs'->>'ram' as ram
FROM products;
```

### Array Data Types
PostgreSQL supports arrays of any data type.

**Array Operations:**
```sql
-- Insert array data
INSERT INTO products (name, tags) VALUES (
  'Laptop',
  ARRAY['electronics', 'computer', 'portable']
);

-- Query arrays
SELECT name FROM products WHERE 'electronics' = ANY(tags);

-- Array functions
SELECT array_length(tags, 1) as tag_count FROM products;
```

## 2. Full-Text Search
PostgreSQL provides powerful full-text search capabilities.

**Creating Full-Text Search:**
```sql
-- Create a search vector column
ALTER TABLE products ADD COLUMN search_vector tsvector;

-- Update search vector
UPDATE products SET search_vector = 
  to_tsvector('english', name || ' ' || COALESCE(metadata->>'description', ''));

-- Create GIN index for fast search
CREATE INDEX products_search_idx ON products USING GIN(search_vector);

-- Search query
SELECT name, ts_rank(search_vector, query) as rank
FROM products, to_tsquery('english', 'laptop & dell') query
WHERE search_vector @@ query
ORDER BY rank DESC;
```

## 3. PostgreSQL Extensions

### PostGIS (Geospatial)
```sql
-- Enable PostGIS extension
CREATE EXTENSION postgis;

-- Create table with geometry
CREATE TABLE locations (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  location GEOMETRY(POINT, 4326)
);

-- Insert spatial data
INSERT INTO locations (name, location) VALUES (
  'Office',
  ST_GeomFromText('POINT(-74.006 40.7128)', 4326)
);

-- Spatial queries
SELECT name, ST_Distance(location, ST_GeomFromText('POINT(-74.006 40.7128)', 4326)) as distance
FROM locations
ORDER BY distance;
```

### Other Useful Extensions
```sql
-- pg_trgm for fuzzy string matching
CREATE EXTENSION pg_trgm;
SELECT name FROM products WHERE name % 'laptp'; -- fuzzy match

-- uuid-ossp for UUID generation
CREATE EXTENSION "uuid-ossp";
SELECT uuid_generate_v4();
```

## 4. Partitioning and Sharding

### Table Partitioning
```sql
-- Create partitioned table
CREATE TABLE orders (
  id SERIAL,
  order_date DATE,
  customer_id INTEGER,
  amount NUMERIC(10,2)
) PARTITION BY RANGE (order_date);

-- Create partitions
CREATE TABLE orders_2023 PARTITION OF orders
FOR VALUES FROM ('2023-01-01') TO ('2024-01-01');

CREATE TABLE orders_2024 PARTITION OF orders
FOR VALUES FROM ('2024-01-01') TO ('2025-01-01');
```

---

## PostgreSQL Tips
- Use JSONB for better performance when querying JSON data
- Create appropriate indexes for JSON and array operations
- Consider partitioning for large tables with time-based data
- Use extensions to extend PostgreSQL functionality

---

## Summary
- JSON and arrays provide flexible data storage
- Full-text search enables powerful text queries
- Extensions like PostGIS add specialized functionality
- Partitioning improves performance for large datasets

---

## Next Steps
Proceed to the next module to learn about security and user management!