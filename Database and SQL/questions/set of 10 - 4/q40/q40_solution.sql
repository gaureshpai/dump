SELECT Orderid, Quotationid, Status, Pymtdate
FROM Orders
WHERE Pymtdate IS NULL;
