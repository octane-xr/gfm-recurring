package util;

import java.io.File;
import java.io.IOException;

public class CsvUtil {
    public void ensureFileExists(String file_path) throws IOException {
        File file = new File(file_path);
        if(!file.exists()) {
            try{
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error creating file: " + e.getMessage());
            }
        }
    }
}
