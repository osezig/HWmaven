package me.iagfarov.hwmaven.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.iagfarov.hwmaven.model.Ingredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    @Value("${name.of.file.two}")
    private String ingredientFileName;

    final private FileService fileService;

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
        saveIngredientFile();
        return ingredient;

    }
    @Override
    public Ingredient getIngredient(Integer id) {
        saveIngredientFile();
        return ingredientMap.get(id);

    }
    @Override
    public Collection<Ingredient> getAll() {
        return ingredientMap.values();

    }

    @Override
    public Ingredient removeIngredient(int id) {
        return ingredientMap.remove(id);

    }

    @Override
    public Ingredient updateIngredient(int id, Ingredient ingredient) {
        ingredientMap.put(id, ingredient);
        return ingredient;

    }
    private void saveIngredientFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileService.saveToFile(json, ingredientFileName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    private void readFromFile() {
        String json = fileService.readFile(ingredientFileName);
        try {
            ingredientMap = new ObjectMapper().readValue
                    (json, new TypeReference<HashMap<Integer, Ingredient>>() {
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

