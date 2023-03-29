package pro.sky.recipesapplication.controllers;

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
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipe(@PathVariable("id") int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes() {
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        if (recipes != null) {
            return ResponseEntity.ok(recipes);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public RecipeDTO addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.editRecipe(id, recipe);
        if (editedRecipe != null) {
            return ResponseEntity.ok(recipe);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
