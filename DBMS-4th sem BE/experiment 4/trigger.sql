set serveroutput on;

create or replace trigger sal_diff
    before delete or insert or update on customers
    for each row
    when(new.id > 0)
    declare 
        salary_difference number;
    begin 
        salary_difference := :new.salary-:old.salary;
        dbms_output.put_line('Previous salary:'||:old.salary);
        dbms_output.put_line('Current salary:'||:new.salary);
        dbms_output.put_line('salary difference:'||salary_difference);
    end:
/