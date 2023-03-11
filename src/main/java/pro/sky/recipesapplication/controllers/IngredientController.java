package pro.sky.recipesapplication.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.recipesapplication.dto.IngredientDTO;
import pro.sky.recipesapplication.model.Ingredient;
import pro.sky.recipesapplication.service.IngredientService;

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

    @PostMapping
    public IngredientDTO addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }
}
