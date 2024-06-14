SELECT 
    Prodid,
    SUM(Quantity) AS Qty_Sold
FROM 
    Saledetail
WHERE 
    Quantity > 1
GROUP BY 
    Prodid
HAVING 
    COUNT(Prodid) > 1;
