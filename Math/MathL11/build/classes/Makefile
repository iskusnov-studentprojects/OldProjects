## gmake file, requires a posix like shell
include ${ORACLE_HOME}/javavm/demo/unix.mk
include ${ORACLE_HOME}/javavm/demo/java.mk

# targets
all: compile deploy sql run

# classpath
MAKE_CLASSPATH = $(JDK12_CLASSPATH)

CLIENT_CLASS = \
	Employee.class

compile: client.jar

client.jar: $(CLIENT_CLASS)
	jar cfM $@ $?

deploy: jsprocndb

jsprocndb: client.jar
	loadjava -f -verbose -oracleresolver -resolve -oci8 -u $(USER)/$(PASS)$(CONNECT_STRING) $?
	touch jsprocndb

sql:
	sqlplus $(USER)/$(PASS)$(CONNECT_STRING) @create.sql

run: 
	sqlplus $(USER)/$(PASS)$(CONNECT_STRING) @run.sql

clean:
	$(RMRF) *.class */*.class client.jar *ndb *.~?~ */*.~?~
