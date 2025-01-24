select d.Dnumber, count(d.Dnumber), avg(e.salary) from Emp e,
dept d where e.Dno = d.Dnumber group by d.Dnumber;