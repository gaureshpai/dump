-- Create salesman table
CREATE TABLE salesman (
    salesman_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    commission DECIMAL(5, 2) NOT NULL
);

-- Create customer table
CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    cust_name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    grade INT,
    salesman_id INT,
    FOREIGN KEY (salesman_id) REFERENCES salesman(salesman_id)
);

-- Create orders table
CREATE TABLE orders (
    ord_no INT PRIMARY KEY,
    purchase_amt DECIMAL(10, 2) NOT NULL,
    ord_date DATE NOT NULL,
    customer_id INT,
    salesman_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (salesman_id) REFERENCES salesman(salesman_id)
);