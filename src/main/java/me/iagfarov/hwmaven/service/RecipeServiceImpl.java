package me.iagfarov.hwmaven.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.iagfarov.hwmaven.model.Ingredient;
import me.iagfarov.hwmaven.model.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    final private FileService fileService;

    @Value("${name.of.recipe.file}")
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
        try {
            if (Files.exists(Path.of(recipeFileName))) {
                String json = fileService.readFile(recipeFileName);
                recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
                });
            } else {
                throw new FileNotFoundException();
            }
        } catch (JsonProcessingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }
    @Override
    public Path createRecipeTxt() throws IOException {
        Path recipeTxt = fileService.createTempFile("recipeTxt");
        for (Recipe recipeFromMap : recipeMap.values()) {
            try (Writer writer = Files.newBufferedWriter(recipeTxt, StandardCharsets.UTF_8)) {
                StringBuilder ingredient = new StringBuilder();
                for (Ingredient ingredients : recipeFromMap.getIngredients()) {
                    ingredient
                            .append(ingredients.getName())
                            .append(" ").append(ingredients.getCount())
                            .append(" ").append(ingredients.getMeasure())
                            .append("\n");
                }
                StringBuilder steps = new StringBuilder();
                for (String stepsFromList : recipeFromMap.getSteps()) {
                    steps.append("\n").append(stepsFromList.replace("[", "-"));
                }
                writer
                        .append(recipeFromMap.getName())
                        .append(": время приготовления ")
                        .append(String.valueOf(recipeFromMap.getCookingTime()))
                        .append("минут, \n")
                        .append(String.valueOf(ingredient))
                        .append(String.valueOf(steps))
                        .append("\n");
            }
        }
        return recipeTxt;
    }


}
