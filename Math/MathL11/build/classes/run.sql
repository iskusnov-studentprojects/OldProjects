Rem run.sql
set echo on
set serveroutput on

rem This SQL script runs the Employee kprb test
rem show the original data in the employee table
select * from employee;

rem This PL/SQL block calls the annualReview procedure, which in turns
rem call the Java method updatePositionSalary in Employee class to
rem update the position and salary of the employee with employee number
rem 101.

declare
 eno 	number;
 ename 	varchar2(20);
 sal 	number;
 pos 	varchar2(20);
begin
 eno := 101;
 ename := getEmpName (eno); 
 sal := 0;
 pos := 'Sr. Engineer';
 annualReview (eno, pos, sal, 10000);
 dbms_output.put_line('eno=' || eno || ' ename=' || ename ||
                      ' position=' || pos || ' salary=' || sal);
end;
/ 

rem show the updated data in the employee table
select * from employee;

exit
