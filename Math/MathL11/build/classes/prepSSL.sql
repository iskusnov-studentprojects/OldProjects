
declare 
 val varchar2(100);
begin
 if (DEBUG) then 
   val := dbms_java.set_property('javax.net.debug','ssl,handshake,trustmanager');
 end if;
 val := dbms_java.set_property('javax.net.ssl.keyStoreType','TYPE');
 val := dbms_java.set_property('javax.net.ssl.keyStore','DIR/KEYST');
 val := dbms_java.set_property('javax.net.ssl.keyStorePassword', 'PSW');
 val := dbms_java.set_property('javax.net.ssl.trustStoreType','TYPE');
 val := dbms_java.set_property('javax.net.ssl.trustStore','DIR/TRUSTST');
 val := dbms_java.set_property('javax.net.ssl.trustStorePassword', 'PSW');
end;
/
