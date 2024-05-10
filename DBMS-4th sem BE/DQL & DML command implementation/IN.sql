select * from cse_course
where faculty_name in (select faculty_name from cse_course
where course_name= 'DBMS')