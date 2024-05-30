-- Insert data into salesman table
INSERT INTO salesman (salesman_id, name, city, commission) VALUES
(1001, 'John Doe', 'New York', 15.00),
(1002, 'Jane Smith', 'Los Angeles', 12.50),
(1003, 'Robert Brown', 'Chicago', 10.00),
(1004, 'Emily Davis', 'Houston', 8.75),
(1005, 'Michael Wilson', 'Phoenix', 9.50);

-- Insert data into customer table
INSERT INTO customer (customer_id, cust_name, city, grade, salesman_id) VALUES
(2001, 'Alice Johnson', 'New York', 2, 1001),
(2002, 'Chris Martin', 'Los Angeles', 1, 1002),
(2003, 'David Lee', 'Chicago', 3, 1003),
(2004, 'Sophia Clark', 'Houston', 1, 1004),
(2005, 'Daniel Lewis', 'Phoenix', 2, 1005);

-- Insert data into orders table
INSERT INTO orders (ord_no, purchase_amt, ord_date, customer_id, salesman_id) VALUES
(3001, 1500.00, '2023-01-15', 2001, 1001),
(3002, 2000.00, '2023-02-20', 2002, 1002),
(3003, 2500.00, '2023-03-25', 2003, 1003),
(3004, 3000.00, '2023-04-30', 2004, 1004),
(3005, 3500.00, '2023-05-10', 2005, 1005);

-- Display data from salesman table
SELECT * FROM salesman;

-- Display data from customer table
SELECT * FROM customer;

-- Display data from orders table
SELECT * FROM orders;
