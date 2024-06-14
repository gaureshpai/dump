SELECT Itemcode, Descr, Price
FROM Item
WHERE (Descr LIKE '%Shirt%' OR Descr LIKE '%Skirt%')
AND Category = 'B';
