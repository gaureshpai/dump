SELECT DISTINCT SNAME
FROM Salesman S
JOIN Sale SA ON S.Sid = SA.Sid
GROUP BY S.Sid, S.Sname
HAVING COUNT(SA.Saleid) >= 2;
