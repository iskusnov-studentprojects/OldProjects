rem create.sql

rem Set up for the Employee kprb test
drop table employee;

create table employee (enum int, ename varchar2(20), 
     position varchar2(20), salary int);

insert into employee values (100, 'John Doe', 'Secretary', 40000);
insert into employee values (101, 'Jane Johnson', 'Engineer', 60000);

create or replace function getEmpName (n IN number) return varchar2 is
	language java name 'Employee.getEmpName(int)
	return java.lang.String';
/

rem This shows how to pass IN, IN OUT, and OUT parameters to an
rem Java stored procedure
create or replace procedure annualReview (enum IN number, 
	ename IN OUT varchar2, sal OUT number, raise IN number) as
          	language java name 'Employee.
          	updatePositionSalary(int, java.lang.String[], int[], int)';
/
exit
