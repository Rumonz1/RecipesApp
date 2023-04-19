package pro.sky.recipesapplication.service;

import org.springframework.stereotype.Service;
import pro.sky.recipesapplication.model.Ingredient;

import java.io.File;
import java.util.Map;
@Service
public interface IngredientFileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
}
