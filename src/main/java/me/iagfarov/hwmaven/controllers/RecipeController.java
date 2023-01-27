package me.iagfarov.hwmaven.controllers;

import lombok.RequiredArgsConstructor;
import me.iagfarov.hwmaven.model.Recipe;
import me.iagfarov.hwmaven.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/{id}")
    Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping
    Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }
}
