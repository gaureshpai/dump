select * from student join course on student.sid < 103; -- theta join

select * from student join course on student.sid = 101; -- equi join

select * from student natural join course; -- natural join

select * from student right outer join course on sid < 20; -- right outer join

select * from student left outer join course on cid > 20; -- left outer join

select * from student full outer join course on  cid > 20; --  full outer join