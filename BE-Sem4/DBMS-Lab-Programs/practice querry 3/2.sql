select w.pname from manager m, works w
where w.pname = m.pname and salary in(select salary from works where(salary > 30000 and salary < 50000));