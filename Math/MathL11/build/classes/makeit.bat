REM makeit.bat
@echo off
@if not "%ECHO%"=="" echo %ECHO%
@setlocal & pushd & set RET=
call %ORACLE_HOME%\javavm\demo\common.bat
if not "%RET%"=="1" goto :DOSEXIT

REM set classpath local to this script
set MAKE_CLASSPATH=%JDK12_CLASSPATH%

REM CLIENT_CLASS
javac -g -classpath %MAKE_CLASSPATH% Employee.java

REM client.jar
jar cfM client.jar *.class

REM load
call loadjava -f -verbose -oracleresolver -resolve -oci8 -u %USER%/%PASS%%CONNECT_STRING% client.jar

REM sql
sqlplus %USER%/%PASS%%CONNECT_STRING% @create.sql

:DOSEXIT
@endlocal & popd & set RET=%RET%
