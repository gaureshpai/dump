SELECT s.name, 'Has Customers' AS customer_status
FROM salesman s
JOIN customer c ON s.salesman_id = c.salesman_id AND s.city = c.city
UNION
SELECT s.name, 'No Customers' AS customer_status
FROM salesman s
WHERE NOT EXISTS (
    SELECT 1
    FROM customer c
    WHERE s.salesman_id = c.salesman_id AND s.city = c.city
);
