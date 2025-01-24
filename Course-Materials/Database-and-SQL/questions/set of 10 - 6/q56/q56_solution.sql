SELECT 
    Prodid, 
    SUBSTR(Pdesc, 1, 5) AS Pdesc_Five, 
    Category
FROM 
    Product;
