Rem
Rem wrappers.sql
Rem
Rem Copyright (c) 2007, 2012, Oracle and/or its affiliates. 
Rem All rights reserved. 
Rem
Rem    NAME
Rem      wrappers.sql
Rem
Rem    DESCRIPTION
Rem      create PLSQL wrappers to run Load from jmxserv.jar
Rem
Rem    NOTES
Rem      Prev step: loadjava -u ... jmxserv.jar.
Rem      Next step: run.sql
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    dnizhego    05/11/08 - 
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

create or replace procedure workload 
  as language java name 'jmxserv.Load.medium()';
/
-- register all user MBeans and run workload
create or replace procedure all_mbs_and_workload 
  as language java name 'jmxserv.Load.addAllMBeansAndRunMediumLoad()';
/
-- start Agent, register all user MBeans and run workload
create or replace procedure start_agent_mbs_workload(port varchar2)
  as language java name 'jmxserv.Load.startAgentAddAllMBeansAndRunMediumLoad(java.lang.String)';
/
create or replace procedure workload_test
  as language java name 'jmxserv.Load.light()';
/
create or replace procedure workload_mb
  as language java name 'jmxserv.Load.addWorkLoad()';
/
create or replace procedure dbprops_mb
  as language java name 'jmxserv.Load.addDBProps()';
/
create or replace procedure ojvm_mb
  as language java name 'jmxserv.Load.addOJVM()';
/
create or replace procedure perm_manager_mb
  as language java name 'jmxserv.Load.addPermissionManager()';
/
create or replace procedure file_permission_mb
  as language java name 'jmxserv.Load.addFilePermissionMB()';
/
create or replace procedure prop_permission_mb
  as language java name 'jmxserv.Load.addPropertyPermissionMB()';
/

create or replace procedure prepSSL
  as language java name 'jmxserv.Load.setupSSLconfig()';
/

-- wrappers.rmi(protocol [, ksmanagers [, port [, trace]]]):
--   to use plain RMI, no SSL: call rmi() or call rmi('any string not ssl')
--   to use RMI with SSL, default jdk key managers: call rmi('ssl', 'default')
--   to use RMI with SSL, custom  managers: call rmi('ssl', 'xyz')
--   to specify PORT other than the default 2019, pass 3rd parameter as integer
--   to turn tracing ON, pass 4th parameter 'TRACE'

create or replace package wrappers as
 procedure rmi;
 procedure rmi(protocol varchar2);
 procedure rmi(protocol varchar2, ksm varchar2);
 procedure rmi(protocol varchar2, ksm varchar2, port varchar2);
 procedure rmi(protocol varchar2, ksm varchar2, port varchar2, trace varchar2);
 procedure setup_ssl(ksm varchar2);
 procedure setup_opki;
end;
/

create or replace package body wrappers as
 procedure rmi as language java name 'jmxserv.TestParamsRMISSLImpl.main(java.lang.String[])';
 procedure rmi(protocol varchar2) as language java name 'jmxserv.TestParamsRMISSLImpl.main(java.lang.String[])';
 procedure rmi(protocol varchar2, ksm varchar2) as language java name 'jmxserv.TestParamsRMISSLImpl.main(java.lang.String[])';
 procedure rmi(protocol varchar2, ksm varchar2, port varchar2) as language java name 'jmxserv.TestParamsRMISSLImpl.main(java.lang.String[])';
 procedure rmi(protocol varchar2, ksm varchar2, port varchar2, trace varchar2) as language java name 'jmxserv.TestParamsRMISSLImpl.main(java.lang.String[])';

 procedure setup_ssl(ksm varchar2) as language java name 'jmxserv.Load.setup_SSL(java.lang.String)';
 procedure setup_opki as language java name 'InstallOraPki.main(java.lang.String[])';

end;
/

quit




