package util;

import java.io.File;
import java.io.IOException;

/**
 * Utility class for handling basic CSV-related file operations.
 */
public final class CsvUtil {

    /**
     * Ensures that a file exists at the specified path.
     * If the file or its parent directories do not exist, they are created.
     *
     * @param filePath the path to the file
     * @throws IOException if an error occurs while creating the file
     */
    public void ensureFileExists(final String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error creating file: "
                        + e.getMessage());
            }
        }
    }
}
