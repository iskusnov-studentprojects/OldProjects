# Use this make file as 
#
#   make -f codegen.mk
#
# to change/rewrite/fix/develop java code in this demo. Edit files Test.jsl and
# Macros.jsl. Do not edit file Test.java.

include ./Makefile

.SUFFIXES: .java .class .jsl

JASPER_MAIN = oracle.aurora.ncomp.jasper.Main 
JASPER_ARGS = -d ncomp -env oracle.aurora.ncomp.javac.ExtensibleBatchEnvironment -notree -replaceJavaSource 

.jsl.java:
	java -classpath $(MAKE_CLASSPATH) $(JASPER_MAIN) $(JASPER_ARGS) $<

ncomp/Test.java: ncomp/Macros.class 
