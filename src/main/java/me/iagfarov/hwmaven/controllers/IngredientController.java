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
@Tag(name = "Контроллер ингредиентов", description = "CRUD-опреции для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингредиента из списка",
            description = "Поиск ингредиента по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент найден"
            )
    })
    ResponseEntity<Ingredient>  getIngredient(@PathVariable Integer id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        return ResponseEntity.ok(ingredient);

    }

    @PostMapping
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
    ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }
    @GetMapping("/all")
    @Operation(
            summary = "Получение всех ингредиентов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты получены"
            )
    })
    public Collection<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение ингредиентов по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент изменен"
            )
    })
    ResponseEntity<Ingredient> updateIngredient(@PathVariable Integer id, @Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент удален"
            )
    })
    ResponseEntity<Ingredient> removeIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.removeIngredient(id));
    }

}
