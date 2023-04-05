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
import pro.sky.recipesapplication.dto.RecipeDTO;
import pro.sky.recipesapplication.model.Ingredient;
import pro.sky.recipesapplication.model.Recipe;
import pro.sky.recipesapplication.service.RecipeService;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Найден рецепт",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public RecipeDTO getRecipe(@PathVariable("id") int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping
    @Operation(
            summary = "Вывод всех рецептов"
    )
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes() {
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        if (recipes != null) {
            return ResponseEntity.ok(recipes);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(
            summary = "Добавление рецепта"
    )
    public RecipeDTO addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование рецепта"
    )
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.editRecipe(id, recipe);
        if (editedRecipe != null) {
            return ResponseEntity.ok(recipe);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта"
    )
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
