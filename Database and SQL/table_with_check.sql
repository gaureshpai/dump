CREATE TABLE Student 
( 
    StudentId INTEGER, 
    FName VARCHAR2(10), 
    Gender CHAR(1) CONSTRAINT Stud_gender_ck1 CHECK(Gender IN('M', 'F'))
);