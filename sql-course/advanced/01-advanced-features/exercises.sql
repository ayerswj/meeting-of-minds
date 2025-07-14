-- Exercises: Advanced PostgreSQL Features

-- 1. Create a table with JSONB and array columns
CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  metadata JSONB,
  tags TEXT[],
  categories TEXT[]
);

-- 2. Insert data with JSON and arrays
INSERT INTO products (name, metadata, tags, categories) VALUES
('MacBook Pro', '{"brand": "Apple", "specs": {"ram": "16GB", "storage": "1TB", "processor": "M2"}}', 
 ARRAY['laptop', 'apple', 'premium'], ARRAY['electronics', 'computers']),
('Dell XPS 13', '{"brand": "Dell", "specs": {"ram": "8GB", "storage": "512GB", "processor": "Intel i7"}}',
 ARRAY['laptop', 'dell', 'ultrabook'], ARRAY['electronics', 'computers']);

-- 3. Query JSON data to find products with specific specs
SELECT name, metadata->'specs'->>'ram' as ram
FROM products
WHERE metadata->'specs'->>'ram' = '16GB';

-- 4. Query array data to find products with specific tags
SELECT name, tags
FROM products
WHERE 'premium' = ANY(tags);

-- 5. Create a full-text search setup
ALTER TABLE products ADD COLUMN search_vector tsvector;

UPDATE products SET search_vector = 
  to_tsvector('english', name || ' ' || COALESCE(metadata->>'brand', ''));

CREATE INDEX products_search_idx ON products USING GIN(search_vector);

-- 6. Perform a full-text search
SELECT name, ts_rank(search_vector, query) as rank
FROM products, to_tsquery('english', 'laptop & apple') query
WHERE search_vector @@ query
ORDER BY rank DESC;

-- 7. Create a partitioned table for orders
CREATE TABLE orders (
  id SERIAL,
  order_date DATE,
  customer_id INTEGER,
  product_id INTEGER,
  amount NUMERIC(10,2)
) PARTITION BY RANGE (order_date);

CREATE TABLE orders_2023 PARTITION OF orders
FOR VALUES FROM ('2023-01-01') TO ('2024-01-01');

CREATE TABLE orders_2024 PARTITION OF orders
FOR VALUES FROM ('2024-01-01') TO ('2025-01-01');

-- 8. Insert data into partitioned table
INSERT INTO orders (order_date, customer_id, product_id, amount) VALUES
('2023-06-15', 1, 1, 1999.99),
('2024-02-20', 2, 2, 1299.99);

-- 9. Query partitioned table
SELECT * FROM orders WHERE order_date >= '2024-01-01';

-- 10. Use array functions
SELECT name, array_length(tags, 1) as tag_count, 
       array_to_string(tags, ', ') as all_tags
FROM products;