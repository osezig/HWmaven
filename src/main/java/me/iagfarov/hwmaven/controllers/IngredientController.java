package me.iagfarov.hwmaven.controllers;

import lombok.RequiredArgsConstructor;
import me.iagfarov.hwmaven.model.Ingredient;
import me.iagfarov.hwmaven.service.IngredientService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    Ingredient getIngredient(@PathVariable Integer id) {
        return ingredientService.getIngredient(id);
    }

    @PostMapping
    Ingredient addIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }
}
