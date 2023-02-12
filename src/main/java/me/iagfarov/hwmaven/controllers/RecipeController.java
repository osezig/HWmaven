package me.iagfarov.hwmaven.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.iagfarov.hwmaven.model.Recipe;
import me.iagfarov.hwmaven.service.RecipeService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Контроллер рецептов", description = "CRUD-опреции для работы с рецептами")

public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта из списка",
            description = "Поиск рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт получен"
            )
    })
    ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PostMapping
    @Operation(
            summary = "Добавление рецепта в мапу",
            description = "Необходимо передать с помощью JSON объект рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен"
            )
    })
    ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping("/all")
    @Operation(
            summary = "Получение всех рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты получены"
            )
    })
    ResponseEntity<Collection<Recipe>> getRecipes() {
        return ResponseEntity.ok(recipeService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт изменен"
            )
    })
    ResponseEntity<Recipe> updateRecipe(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален"
            )
    })
    ResponseEntity<Recipe> removeRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.removeRecipe(id));
    }
    @GetMapping("/gettxtfile")
    public ResponseEntity<Object> getTxtFile() {
        try {
            Path path = recipeService.createRecipeTxt();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipeBook.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
