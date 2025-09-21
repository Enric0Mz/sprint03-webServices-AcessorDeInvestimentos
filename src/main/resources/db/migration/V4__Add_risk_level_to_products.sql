ALTER TABLE products
ADD COLUMN risk_level VARCHAR(20);

UPDATE products SET risk_level = 'MEDIO' WHERE risk_level IS NULL;

ALTER TABLE products
ALTER COLUMN risk_level SET NOT NULL;