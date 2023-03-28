package pro.sky.recipesapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesapplication.dto.IngredientDTO;
import pro.sky.recipesapplication.model.Ingredient;
import pro.sky.recipesapplication.service.IngredientService;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public IngredientDTO getIngredient(@PathVariable("id") int id) {
        return ingredientService.getIngredient(id);
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredients() {
        Map<Integer, Ingredient> ingredients = ingredientService.getAllIngredients();
        if (ingredients != null) {
            return ResponseEntity.ok(ingredients);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public IngredientDTO addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        Ingredient editedIngredient = ingredientService.editIngredient(id, ingredient);
        if (editedIngredient != null) {
            return ResponseEntity.ok(ingredient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
