package me.iagfarov.hwmaven.service;

import me.iagfarov.hwmaven.model.Recipe;

public interface RecipeService {
    Recipe getRecipe(Integer id);

    Recipe addRecipe(Recipe recipe);
}
