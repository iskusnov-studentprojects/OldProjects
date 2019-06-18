-- jmxload.sql: startup JMX-enabled OJVM session with JMX agent listening on
--  a specified port and running a 10-minute CPU- and memory- intensive workload.
-- Usage example: SQL> @jmxload.sql 9999

SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 80
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 100
set serveroutput on
SET ECHO ON

call dbms_java.start_jmx_agent(&1, 'false', 'false');

-- xyz_mb(): add a few addional Mbeans to the server. The propery/permissions
-- MBeans may require additional permissions granted to the user.

-- add workload MBean
call workload_mb(); 

-- add Permissions Manager MBean
call perm_manager_mb(); 

-- add Java-in-the-DB System Properties interface MBean
call dbprops_mb(); 

-- add OJVM Runtime MBean
call ojvm_mb(); 

-- add File Permissions control MBean
call file_permission_mb(); 

-- add Property Permissions MBean
call prop_permission_mb(); 


-- Start a 10-minute memory and CPU intensive workload. Can be stopped with C-c
-- or using jmxserv.Load.stop() via JMX.
call workload();

-- quit after done
--quit
