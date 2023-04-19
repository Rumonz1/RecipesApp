package pro.sky.recipesapplication.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
@Service
public interface RecipeFileService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
}
