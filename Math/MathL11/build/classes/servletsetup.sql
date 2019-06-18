call dbms_xdb.cfg_update
     (
       updatexml 
       (
         dbms_xdb.cfg_get(),
         '/xdbconfig/sysconfig/protocolconfig/httpconfig/webappconfig/servletconfig/servlet-list',
         xmlelement
         (
           "servlet-list",
           xmlattributes('http://xmlns.oracle.com/xdb/xdbconfig.xsd' AS "xmlns"),
           xmlconcat
           (
             extract
             (
               dbms_xdb.cfg_get(),
               '/xdbconfig/sysconfig/protocolconfig/httpconfig/webappconfig/servletconfig/servlet-list/servlet'
             ),
             xmltype
             (
               '<servlet xmlns="http://xmlns.oracle.com/xdb/xdbconfig.xsd">
                  <servlet-name>PolicyServlet</servlet-name>
                  <servlet-language>Java</servlet-language>
                  <display-name>Policy Table Servlet</display-name>
                  <servlet-class>PolicyServlet</servlet-class> 
                  <servlet-schema>SYS</servlet-schema>
                  <security-role-ref>
                    <role-name>authenticatedUser</role-name> 
                    <role-link>authenticatedUser</role-link>
                  </security-role-ref> 
                  </servlet>'
             )
           )
         ),
         '/xdbconfig/sysconfig/protocolconfig/httpconfig/webappconfig/servletconfig/servlet-mappings',
         xmlelement
         (
           "servlet-mappings",
           xmlattributes('http://xmlns.oracle.com/xdb/xdbconfig.xsd' AS "xmlns"),           
           xmlconcat
           (
             extract
             (
               dbms_xdb.cfg_get(),
               '/xdbconfig/sysconfig/protocolconfig/httpconfig/webappconfig/servletconfig/servlet-mappings/servlet-mapping'
             ),
             xmltype
             (
               '<servlet-mapping xmlns="http://xmlns.oracle.com/xdb/xdbconfig.xsd">
                  <servlet-pattern>/security/policy</servlet-pattern>
                  <servlet-name>PolicyServlet</servlet-name>
                </servlet-mapping>'
             )
           )
         )
       )
     )
/
