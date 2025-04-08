# Directorios
SRC_DIR = src
OUT_DIR = out

# Archivos
SOURCES = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

# Clases principales
MAIN_CLASS = src.GMFRecurring

# Compilación
compile:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $(SOURCES)

# Ejecución (sin argumentos)
run: compile
	java -cp $(OUT_DIR) $(MAIN_CLASS)

# Ejecución con archivo de entrada
run_file: compile
	java -cp $(OUT_DIR) $(MAIN_CLASS) input.txt

# Limpieza
clean:
	rm -rf $(OUT_DIR)


