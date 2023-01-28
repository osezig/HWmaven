package me.iagfarov.hwmaven.service;

import me.iagfarov.hwmaven.model.Ingredient;

public interface IngredientService {
    Ingredient getIngredient(Integer id);

    Ingredient addIngredient(Ingredient ingredient);

}
