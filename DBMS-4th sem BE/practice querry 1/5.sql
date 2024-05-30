-- First, delete orders associated with the salesman
DELETE FROM orders
WHERE salesman_id = 1000;

-- Then, delete the salesman
DELETE FROM salesman
WHERE salesman_id = 1000;
