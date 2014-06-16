LIB_FILE = .:org.json.jar
JFLAGS = -cp $(LIB_FILE)
JC = javac
JVM= java
JAR= jar cvfm
MF_FILE= manifest.mf
FILE=
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java -encoding UTF-8
CLASSES = \
	TocHW3.java

MAIN = TocHW3

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(JFLAGS) $(MAIN)

jar: classes
	$(JAR) $(MAIN).jar $(MF_FILE) *.class

clean:
	$(RM) *.class
