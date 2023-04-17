package pro.sky.recipesapplication.service;

import java.io.File;
import java.nio.file.Path;

public interface RecipeFileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
}
