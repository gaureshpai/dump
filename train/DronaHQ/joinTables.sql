SELECT
    t1.Group,
    t1.CompanyName,
    COUNT(*) AS Count
FROM
    main.table AS t1
JOIN
    cb_vendorinformation AS t2 ON t1.Groups = t2.Groups
GROUP BY
    t1.Group,
    t1.CompanyName
ORDER BY
    Count asc,
    t1.Group desc;