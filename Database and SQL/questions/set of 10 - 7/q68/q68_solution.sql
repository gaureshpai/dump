SELECT Prodid AS PRODID, Pdesc AS PDESC
FROM Product P
WHERE Prodid IN (
    SELECT Prodid
    FROM Saledetail SD
    GROUP BY Prodid
    HAVING SUM(Quantity) = (
        SELECT MIN(TotalQuantity)
        FROM (
            SELECT SUM(Quantity) AS TotalQuantity
            FROM Saledetail
            GROUP BY Prodid
        ) MinTotal
    )
);
