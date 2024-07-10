CREATE VIEW top_salesman_of_the_day AS
    SELECT s.salesman_id, s.name, o.ord_date
        FROM salesman s
        JOIN orders o ON s.salesman_id = o.salesman_id
    JOIN (
        SELECT ord_date, MAX(purchase_amt) AS max_purchase_amt
        FROM orders
        GROUP BY ord_date
    ) max_orders ON o.ord_date = max_orders.ord_date AND o.purchase_amt = max_orders.max_purchase_amt
    JOIN customer c ON o.customer_id = c.customer_id
    WHERE s.salesman_id = o.salesman_id;