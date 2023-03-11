package pro.sky.recipesapplication.service;

import org.springframework.stereotype.Service;
import pro.sky.recipesapplication.dto.IngredientDTO;
import pro.sky.recipesapplication.dto.RecipeDTO;
import pro.sky.recipesapplication.model.Ingredient;
import pro.sky.recipesapplication.model.Recipe;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService {
    private int idCounter = 0;
    private final Map<Integer, Ingredient> ingredients = new HashMap<>();
    public IngredientDTO addIngredient(Ingredient ingredient) {
        int id = idCounter++;
        ingredients.put(id, ingredient);
        return IngredientDTO.from(id, ingredient);
    }
    public IngredientDTO getIngredient(int id) {
        Ingredient ingredient = ingredients.get(id);
        if (ingredient != null) {
            return IngredientDTO.from(id, ingredient);
        }
        return null;
    }
}
