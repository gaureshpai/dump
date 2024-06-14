SELECT p.Prodid, p.Pdesc, SUM(sd.Quantity) AS TotalQuantitySold
FROM Product p
JOIN Saledetail sd ON p.Prodid = sd.Prodid
GROUP BY p.Prodid, p.Pdesc
ORDER BY TotalQuantitySold DESC;
