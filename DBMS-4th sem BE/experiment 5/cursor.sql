set servertoutput on;

declare cursor c1 is select E_id, salary from Employee;
Nid varchar2(10);
Nsal int;
begin
dbms_output.put_line('Emp ID'||'    '||'Emp Sal');
dbms_output.put_line('-------------------------');
open c1;
loop
fetch c1 into Nid,Nsal;
exit when c1%notfound; /*return TRUE is no rowds are affected*/
dbms_output.put_line(Nid||'     '||Nsal);
end loop;
close c1;
end;
/