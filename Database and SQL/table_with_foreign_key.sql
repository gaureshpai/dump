CREATE TABLE Marks
(
    CourseId INTEGER, 
    StudentId INTEGER CONSTRAINT marks_sid_fk REFERENCES Student(StudentId), 
    MarksScored DECIMAL(5,2)
);