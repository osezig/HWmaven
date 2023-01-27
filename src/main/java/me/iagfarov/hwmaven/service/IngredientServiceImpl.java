package me.iagfarov.hwmaven.service;

import me.iagfarov.hwmaven.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Ingredient addIngrediant(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return null;
    }

    @Override
    public Ingredient getIngredient(Integer id) {
        return ingredientMap.getOrDefault(id, null);
    }
}
