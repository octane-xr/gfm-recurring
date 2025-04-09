SRC_DIR = src
OUT_DIR = out

SOURCES = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

MAIN_CLASS = src.GMFRecurring

# Compilación
compile:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(SOURCES)

run: compile
	java -cp $(OUT_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(OUT_DIR)


