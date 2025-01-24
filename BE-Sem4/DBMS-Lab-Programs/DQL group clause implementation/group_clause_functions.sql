select min(usn) from student;

select max(usn) from student;

select count(*) from student;

select count(usn) from student
group by usn having usn > '4JK22CS017';

select sum(marks1) from student;