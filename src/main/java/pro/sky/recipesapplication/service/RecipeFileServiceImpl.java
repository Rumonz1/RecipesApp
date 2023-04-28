package pro.sky.recipesapplication.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFileServiceImpl  implements RecipeFileService{
    @Value("${path.to.data.file}")
    private String recipesFilePath;
    @Value("${name.of.recipes.file}")
    private String recipesFileName;



    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(recipesFilePath, recipesFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(recipesFilePath, recipesFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "{ERROR}";
        }
    }
    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(recipesFilePath, recipesFileName);
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
        return new File(recipesFilePath + "/" + recipesFileName);
    }
    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(recipesFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
