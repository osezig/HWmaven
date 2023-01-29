package me.iagfarov.hwmaven.controllers;

import lombok.RequiredArgsConstructor;
import me.iagfarov.hwmaven.model.Ingredient;
import me.iagfarov.hwmaven.service.IngredientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor

public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    ResponseEntity<Ingredient>  getIngredient(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        return ResponseEntity.ok(ingredient);

    }

    @PostMapping
    ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }
    @GetMapping("/all")
    public Collection<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }

    @PutMapping("/{id}")
    ResponseEntity<Ingredient> updateIngredient(@PathVariable Integer id, @Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredient));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Ingredient> removeIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.removeIngredient(id));
    }

}
