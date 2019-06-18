Rem
Rem run.sql
Rem
Rem Copyright (c) 2007, 2013, Oracle and/or its affiliates. 
Rem All rights reserved. 
Rem
Rem    NAME
Rem      run_ssl.sql
Rem
Rem    DESCRIPTION
Rem      Run JMX Server over SSL
Rem
Rem    NOTES
Rem      This assumes jmxserv.jar providing Load.medium etc has been loaded in the
Rem      schema.
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    yhuang      03/28/13 - pass port
Rem    dnizhego    05/08/08 - 
Rem    yhuang      04/21/08 - move user.pass into sql file
Rem    dnizhego    10/06/07 - Created
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

@configure_SSL.tmp 

call dbms_java.start_jmx_agent('&1', 'true');
call workload_mb();
call perm_manager_mb();
call dbprops_mb();
call ojvm_mb();
call file_permission_mb();
call prop_permission_mb();

-- this takes a couple of minutes to complete unless stopped with Load.stop()
call workload();
quit
