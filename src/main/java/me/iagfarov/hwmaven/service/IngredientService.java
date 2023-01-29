package me.iagfarov.hwmaven.service;

import me.iagfarov.hwmaven.model.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Ingredient getIngredient(Integer id);

    Ingredient addIngredient(Ingredient ingredient);

    Collection<Ingredient> getAll();

    Ingredient removeIngredient(int id);

    Ingredient updateIngredient(int id, Ingredient ingredient);

}
