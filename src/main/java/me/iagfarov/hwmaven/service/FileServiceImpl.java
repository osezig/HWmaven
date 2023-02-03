package me.iagfarov.hwmaven.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class FileServiceImpl implements FileService {

        @Value("${path.to.file.folder}")
        private String dataFilePath;

        @Override
        public boolean saveToFile(String json, String fileName) {
            try {
                cleanRecipeFile(fileName);
                Files.writeString(Path.of(dataFilePath, fileName), json);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        @Override
        public String readFile(String fileName) {
            try {
                return Files.readString(Path.of(dataFilePath, fileName));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        private boolean cleanRecipeFile(String fileName) {
            try {
                Path path = Path.of(dataFilePath, fileName);
                Files.deleteIfExists(path);
                Files.createFile(path);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }
    }

