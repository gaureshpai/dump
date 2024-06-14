SELECT 
    E.Id AS ID, 
    E.Ename AS ENAME, 
    E.Dept AS DEPT, 
    E.Compid AS COMPID, 
    C.Make AS MAKE
FROM 
    Employee E
JOIN 
    Computer C ON E.Compid = C.Compid
ORDER BY 
    E.Id;
