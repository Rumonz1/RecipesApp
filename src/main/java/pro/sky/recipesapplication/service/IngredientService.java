package pro.sky.recipesapplication.service;

import org.springframework.stereotype.Service;
import pro.sky.recipesapplication.dto.IngredientDTO;
import pro.sky.recipesapplication.model.Ingredient;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientService {
    private int idCounter = 0;
    private final Map<Integer, Ingredient> ingredients = new HashMap<>();
    final private IngredientFileService ingredientFileService;

    public IngredientService(IngredientFileService ingredientFileService) {
        this.ingredientFileService = ingredientFileService;
    }
    @PostConstruct
    private void init() {
        ingredientFileService.readFromFile();

    }

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

    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    public boolean deleteIngredient(int id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return true;
        }
return false;
    }

    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredients;
    }
}
