rem grant.sql
connect sys/&2 as sysdba

declare
user_name varchar2(30);
begin
user_name := upper('&1');
dbms_java.grant_permission(user_name, 'java.net.SocketPermission','*','CONNECT, RESOLVE');
dbms_java.grant_permission(user_name, 'java.util.PropertyPermission','user.timezone','write');
end;
/

exit
