SELECT 
    TO_CHAR(Sldate, 'Month') AS Month,
    COUNT(*) AS Number_Sale
FROM 
    Sale
GROUP BY 
    TO_CHAR(Sldate, 'Month')
ORDER BY 
    Number_Sale DESC;
