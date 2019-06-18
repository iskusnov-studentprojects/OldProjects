Rem point.sql

drop table point_table;
drop type point force;

create or replace type point as object (
  x number, 
  y number,
  member function jdistance (p point) return number,
  member procedure moveby (p point)
);
/

create or replace package pointruntime is
  function jdistance (p1 point, p2 point) return number;
  function moveby (p1 point, p2 point) return point;
end;
/

create or replace type body point is
  member function jdistance (p point) return number is
  begin
    return pointruntime.jdistance (self, p);
  end;
  member procedure moveby (p point) is
  begin
    self := pointruntime.moveby (self, p);
  end;
end;
/

create or replace package body pointruntime is
  function jdistance (p1 point, p2 point) return number is
  language java name
    'PointRuntime.jdistance (oracle.sql.STRUCT, oracle.sql.STRUCT) return double';
  function moveby (p1 point, p2 point) return point is
  language java name
    'PointRuntime.moveBy (oracle.sql.STRUCT, oracle.sql.STRUCT) return oracle.sql.STRUCT';
end;
/

create table point_table (p point);
insert into point_table values (point (0, 0));
insert into point_table values (point (3, 4));
insert into point_table values (point (10, 10));
