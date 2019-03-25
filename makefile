build:
	javac main.java

run:
	java -classpath ".:./deps/sqlite-jdbc-3.27.2.1.jar" Main

all:
	make build
	make run