package pro.sky.recipesapplication.service;

import org.springframework.stereotype.Service;
import pro.sky.recipesapplication.model.Ingredient;

import java.io.File;
import java.util.Map;
@Service
public interface IngredientFileService {
    String addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient ingredient);

    String removeIngredient(int id);

    Map<Integer, Ingredient> getAllIngredients();

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
}
