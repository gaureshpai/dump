insert into course values('4JK22CS017','UHV',017); # unique constraint violated

insert into course values('4JK22CS017','S',null); # domain constraint violated

insert into course values('4JK22CS017','maths',null); # key constraint violated

alter table student modify(usn int); # integrity constraint violated

insert into course values('4JK22CS017','S',017); #entity constraint violated