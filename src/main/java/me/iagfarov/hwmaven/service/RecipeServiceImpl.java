package me.iagfarov.hwmaven.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.iagfarov.hwmaven.model.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    final private FileService fileService;

    @Value("${name.of.file.one}")
    private String recipeFileName;

    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        saveToRecipeFile();
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer id) {
        return recipeMap.get(id);
    }
    @Override
    public Collection<Recipe> getAll() {
        return recipeMap.values();
    }

    @Override
    public Recipe removeRecipe(int id) {
        return recipeMap.remove(id);
    }

    @Override
    public Recipe updateRecipe(int id, Recipe recipe) {
        saveToRecipeFile();
        return recipeMap.put(id, recipe);
    }
    private void saveToRecipeFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json, recipeFileName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = fileService.readFile(recipeFileName);
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

}
