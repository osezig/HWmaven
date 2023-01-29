package me.iagfarov.hwmaven.service;

import me.iagfarov.hwmaven.model.Recipe;

import java.util.Collection;

public interface RecipeService {
    Recipe getRecipe(Integer id);

    Recipe addRecipe(Recipe recipe);

    Collection<Recipe> getAll();

    Recipe removeRecipe(int id);

    Recipe updateRecipe(int id, Recipe recipe);
}
