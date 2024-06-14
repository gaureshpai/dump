SELECT 
    Prodid,
    Category,
    Discount
FROM 
    Product
WHERE 
    Category IN ('Sports', 'Apparel')
ORDER BY 
    Category ASC, 
    Discount ASC;
