create table Store(
Name varchar2(20) primary key,
Location varchar2(30) not null,
ManagerName varchar2(30) unique
);