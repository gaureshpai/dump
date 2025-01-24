Enter user-name: / as sysdba

show user

create user c##gp identified by gp123

grant connect, resource, dba to c##gp;

connect c##gp/gp123;

show user