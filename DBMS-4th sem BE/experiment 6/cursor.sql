set severoutput on;

create procedure rollcall AS
rno1 int;
nm1 varchar(20);
rno2 int;
nm2 varchar(20);
done number:=0;
cursor c1 IS select roll, name from O_RollCall;
cursor c2 IS select roll, name from N_RollCall;
begin
open c1;
loop
    fetch c1 into rno1, nm1;
    exit when c1%notfound;
    done:=0;
    open c2;
    loop    
        fetch c2 into rno2, nm2;
        exit when c2%notfound;
        if rno1=rno2 then
            exit;
        end if;
    end loop;
    if c2%notfound; then 
        insert into N_RollCall values(rno1,nm1);
    end if;
    close c2;
end loop;
close c1;
end;
/

call rollcall();

select * from N_RollCall;