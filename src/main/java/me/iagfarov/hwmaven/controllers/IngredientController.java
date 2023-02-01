package me.iagfarov.hwmaven.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Констроллер ингредиентов", description = "CRUD-опреции для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    ResponseEntity<Ingredient>  getIngredient(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        return ResponseEntity.ok(ingredient);

    }

    @Operation(
            summary = "Добавление ингредиента в список",
            description = "Необходимо передать с помощью JSON объект ингредиента"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент добавлен"
            )
    })

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
