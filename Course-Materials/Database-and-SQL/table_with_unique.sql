CREATE TABLE Student 
( 
    StudentId INTEGER, 
    FName VARCHAR2(10), 
    ContactNo NUMBER(10) CONSTRAINT Stud_cno_uk UNIQUE
);