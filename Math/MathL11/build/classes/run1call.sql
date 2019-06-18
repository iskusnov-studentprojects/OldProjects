R em
Rem run1call.sql
Rem
Rem Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved. 
Rem
Rem    NAME
Rem      run1call.sql - Run JMX Server in a single java call.
Rem
Rem    DESCRIPTION
Rem      Run JMX Server in a single java call.
Rem      usage: SQL> run1call <port>
Rem
Rem    NOTES
Rem      The logic exemplified by this script is especially useful under MTS
Rem      where daemon threads and host sockets may not survive 
Rem      context/process switch
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    dnizhego    07/13/12 - Created
Rem

SET ECHO ON
SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 80
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 100

connect jmxdemo/jmxdemo

set serveroutput on

select dbms_java.set_property('com.sun.management.jmxremote.ssl', 'false') from dual;
select dbms_java.set_property('com.sun.management.jmxremote.authenticate', 'false')  from dual;
call start_agent_mbs_workload(&1);
quit
