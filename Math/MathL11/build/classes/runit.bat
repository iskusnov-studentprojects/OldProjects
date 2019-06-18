REM runit.bat
@echo off
@if not "%ECHO%"=="" echo %ECHO%
@setlocal & pushd & set RET=
call %ORACLE_HOME%\javavm\demo\common.bat
if not "%RET%"=="1" goto :DOSEXIT

REM set classpath local to this script
set MAKE_CLASSPATH=%JDK12_CLASSPATH%

REM run
sqlplus %USER%/%PASS%%CONNECT_STRING% @run.sql

:DOSEXIT
@endlocal & popd & set RET=%RET%
