SELECT 
    E.Id AS ID, 
    E.Ename AS ENAME, 
    E.Compid AS COMPID, 
    C.Make AS MAKE
FROM 
    Employee E
JOIN 
    Computer C ON E.Compid = C.Compid
WHERE 
    C.Model IN ('Precision', 'Edge')
ORDER BY 
    E.Id;
