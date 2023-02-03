package me.iagfarov.hwmaven.service;

public interface FileService {

    boolean saveToFile(String json, String fileName);

    String readFile(String fileName);
}

