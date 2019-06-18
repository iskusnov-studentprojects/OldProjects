Rem
Rem setup.sql
Rem
Rem Copyright (c) 2007, 2011, Oracle and/or its affiliates. 
Rem All rights reserved. 
Rem
Rem    NAME
Rem      setup.sql
Rem
Rem    DESCRIPTION
Rem      create a user for running a JMX server
Rem
Rem    NOTES
Rem      this can be executed repeatedly
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    yhuang      07/05/11 - add privilege
Rem    yhuang      02/17/09 - fix sys
Rem    dnizhego    10/06/07 - Created
Rem

SET ECHO ON
SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 80
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 100

connect sys/&1 as sysdba
set serveroutput on
drop user jmxdemo cascade;

create user jmxdemo identified by jmxdemo default tablespace system quota unlimited on system;
grant connect, resource, jmxserver to jmxdemo;
quit
