package me.iagfarov.hwmaven.service;

import java.io.File;
import java.nio.file.Path;

public interface FileService {

    void saveToFile(String json, String fileName);

    String readFile(String fileName);

    boolean cleanFile(String fileName);
   File getFile(String fileName);

    Path createTempFile(String suffix);

}

