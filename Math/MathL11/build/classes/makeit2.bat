REM makeit.bat
@echo off
@if not "%ECHO%"=="" echo %ECHO%
@setlocal & pushd & set RET=
call %ORACLE_HOME%\javavm\demo\common.bat
if not "%RET%"=="1" goto :DOSEXIT

REM load
call loadjava -f -verbose -oracleresolver -resolve -oci8 -u %USER%/%PASS%%CONNECT_STRING% client.jar

REM sql
sqlplus %USER%/%PASS%%CONNECT_STRING% @create.sql point2

:DOSEXIT
@endlocal & popd & set RET=%RET%
