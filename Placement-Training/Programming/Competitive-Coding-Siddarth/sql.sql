create table students(
	id int primary key AUTOINCREMENT,
    student_name varchar(20),
    marks INT
)

insert into students values(1,'Sachin',89);

insert into students values(2,'Rahul',75);

insert into students values(3,'Smrithi',85);

--display the last inserted id without using aggregate function
select id from students order by id desc limit 1 ;

--display the student_name with the marks greater than avg
select student_name from students where marks>(Select avg(marks) from students);

--display the students whose admission is done
create table admissions(
	id int primary key,
    student_id int,
    ad_no int,
    FOREIGN key(student_id) REFERENCES students
);

insert into admissions VALUES(1,3,123),(2,2,234)

select student_name from students, admissions where student_id=students.id