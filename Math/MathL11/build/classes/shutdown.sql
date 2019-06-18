Rem
Rem $Header: shutdown.sql 19-oct-2007.10:00:15 dnizhego Exp $
Rem
Rem shutdown.sql
Rem
Rem Copyright (c) 2007, Oracle.  All rights reserved.  
Rem
Rem    NAME
Rem      shutdown.sql - <one-line expansion of the name>
Rem
Rem    DESCRIPTION
Rem      <short description of component this file declares/defines>
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    dnizhego    10/19/07 - Created
Rem

SET ECHO ON
SET FEEDBACK 1
SET NUMWIDTH 10
SET LINESIZE 80
SET TRIMSPOOL ON
SET TAB OFF
SET PAGESIZE 100

REM assume directory is T_WORK

connect/ as sysdba
set serveroutput on
shutdown abort
startup @t_initvm1.ora
quit
