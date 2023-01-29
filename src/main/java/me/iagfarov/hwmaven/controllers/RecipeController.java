package me.iagfarov.hwmaven.controllers;

import lombok.RequiredArgsConstructor;
import me.iagfarov.hwmaven.model.Recipe;
import me.iagfarov.hwmaven.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/{id}")
    ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PostMapping
    ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping("/all")
    ResponseEntity<Collection<Recipe>> getResipes() {
        return ResponseEntity.ok(recipeService.getAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<Recipe> updateRecipe(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Recipe> removeRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.removeRecipe(id));
    }
}
