package me.iagfarov.hwmaven.service;

import me.iagfarov.hwmaven.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
        return ingredient;
    }
    @Override
    public Ingredient getIngredient(Integer id) {
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
        return null;
    }
}

