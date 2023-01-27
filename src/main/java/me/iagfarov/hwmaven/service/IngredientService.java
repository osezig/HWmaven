package me.iagfarov.hwmaven.service;

import me.iagfarov.hwmaven.model.Ingredient;

public interface IngredientService {
    Ingredient getIngredient(Integer id);

    Ingredient addIngrediant(Ingredient ingredient);

    Ingredient addIngredient(Ingredient ingredient);
}
