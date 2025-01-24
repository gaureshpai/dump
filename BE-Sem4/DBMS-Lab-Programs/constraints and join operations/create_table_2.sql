create table course
(
   usn varchar(10),
   cname varchar(20),
   cid int,
   primary key(cid),
   foreign key(usn) references student(usn) 
);

desc course;