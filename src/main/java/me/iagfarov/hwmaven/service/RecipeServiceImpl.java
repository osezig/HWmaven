package me.iagfarov.hwmaven.service;

import lombok.RequiredArgsConstructor;
import me.iagfarov.hwmaven.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
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
        return recipeMap.put(id, recipe);
    }

}
