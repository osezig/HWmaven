package me.iagfarov.hwmaven.service;

public interface FileService {

    void saveToFile(String json, String fileName);

    String readFile(String fileName);

    boolean cleanFile(String fileName);

}

