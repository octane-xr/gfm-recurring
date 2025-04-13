TARGET = gfm-recurring

SRC_DIR = src
BIN_DIR = bin

SOURCES := $(shell find $(SRC_DIR) -name "*.java")
JAVAC = javac

MAIN_CLASS = main.Application

.PHONY: all run clean

all:
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SOURCES)

run: all
	java -cp $(BIN_DIR) $(MAIN_CLASS) input.txt

clean:
	rm -rf $(BIN_DIR)
