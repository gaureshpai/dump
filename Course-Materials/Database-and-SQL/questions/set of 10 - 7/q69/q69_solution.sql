SELECT S.Sid AS SID, S.Sname AS SNAME, S.Location AS LOCATION
FROM Salesman S
WHERE S.Sid IN (
    SELECT Sa.Sid
    FROM Sale Sa
    JOIN Saledetail SD ON Sa.Saleid = SD.Saleid
    JOIN Product P ON SD.Prodid = P.Prodid
    GROUP BY Sa.Sid
    HAVING SUM((P.Price * (1 - P.Discount / 100)) * SD.Quantity) >
           (SELECT AVG(TotalAmount)
            FROM (
                SELECT SUM((P1.Price * (1 - P1.Discount / 100)) * SD1.Quantity) AS TotalAmount
                FROM Sale Sa1
                JOIN Saledetail SD1 ON Sa1.Saleid = SD1.Saleid
                JOIN Product P1 ON SD1.Prodid = P1.Prodid
                GROUP BY Sa1.Sid, SD1.Saleid
            ) AvgAmount
           )
);
