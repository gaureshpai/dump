SELECT COUNT(*)
FROM customer
WHERE grade > (
    SELECT AVG(grade)
    FROM customer
    WHERE city = 'Bangalore'
);