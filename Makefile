# Variables
SRC_DIR=src
BIN_DIR=bin
TEST_DIR=$(SRC_DIR)/test
MAIN_CLASS=main.Application
CLASSPATH=$(BIN_DIR)

SOURCES=$(shell find $(SRC_DIR) -name "*.java")
TEST_CLASSES=$(shell find $(TEST_DIR) -name "*Tests.java")

all: compile

compile:
	mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) $(SOURCES)

run:
	java -cp $(CLASSPATH) $(MAIN_CLASS)

run-input:
	java -cp $(CLASSPATH) $(MAIN_CLASS) input.txt

test:
	javac -d $(BIN_DIR) $(SOURCES)
	java -cp $(CLASSPATH) test.TestRunner

clean:
	rm -rf $(BIN_DIR)
