package pro.sky.recipesapplication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.sky.recipesapplication.model.Ingredient;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Service
public class IngredientFileServiceImpl implements IngredientFileService{
    @Value("${path.to.data.file}")
    private String ingredientFilePath;
    @Value("${name.of.ingredients.file}")
    private String ingredientFileName;


    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath,ingredientFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "{ERROR}";
        }
    }
    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(ingredientFilePath,ingredientFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getDataFile() {
        return new File(ingredientFilePath + "/" + ingredientFileName);
    }
}
