package me.iagfarov.hwmaven.controllers;

import me.iagfarov.hwmaven.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {

        @Value("${name.of.recipe.file}")
        private String recipeFileName;

    @Value("${name.of.ingredient.file}")
    private String ingredientFileName;
    private final FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/export")
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File recipeFile = fileService.getFile(recipeFileName);

        if (recipeFile.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(recipeFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(recipeFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/export/ingredient")
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws FileNotFoundException {
        File ingrFile = fileService.getFile(ingredientFileName);

        if (ingrFile.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(ingrFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(ingrFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredients.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upload(@RequestParam MultipartFile file) {
        fileService.cleanFile(recipeFileName);
        File recipeFile = fileService.getFile(recipeFileName);
        try (FileOutputStream fos = new FileOutputStream(recipeFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}

