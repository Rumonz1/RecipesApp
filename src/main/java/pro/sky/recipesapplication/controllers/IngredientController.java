package pro.sky.recipesapplication.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesapplication.dto.IngredientDTO;
import pro.sky.recipesapplication.model.Ingredient;
import pro.sky.recipesapplication.service.IngredientService;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD-операции и другие эндпоинты для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск ингредиента по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Найден ингредиент",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public IngredientDTO getIngredient(@PathVariable("id") int id) {
        return ingredientService.getIngredient(id);
    }

    @GetMapping
    @Operation(
            summary = "Вывод всех ингредиентов из базы данных"
    )
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredients() {
        Map<Integer, Ingredient> ingredients = ingredientService.getAllIngredients();
        if (ingredients != null) {
            return ResponseEntity.ok(ingredients);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(
            summary = "Добавление ингредиента в базу данных")
    public IngredientDTO addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование ингредиента"
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient editedIngredient = ingredientService.editIngredient(id, ingredient);
        if (editedIngredient != null) {
            return ResponseEntity.ok(ingredient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
