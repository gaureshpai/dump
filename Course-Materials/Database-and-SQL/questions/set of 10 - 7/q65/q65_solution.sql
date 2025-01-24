SELECT 
    E.Dept AS DEPT,
    COUNT(*) AS "NUMBER OF COMPUTERS"
FROM 
    Employee E
JOIN 
    Computer C ON E.Compid = C.Compid
WHERE 
    C.Make = 'Dell'
GROUP BY 
    E.Dept
ORDER BY 
    E.Dept;
