package pro.sky.recipesapplication.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipesapplication.service.RecipeFileService;

import java.io.*;

@RestController
@RequestMapping("/recipe/files")
@Tag(name = "Файлы с рецептами", description = "CRUD-операции и другие эндпоинты для работы с файлами рецептов")
public class RecipesFilesController {
    private final RecipeFileService recipeFileService;

    public RecipesFilesController(RecipeFileService recipeFileService) {
        this.recipeFileService = recipeFileService;
    }

    @GetMapping("/export")
    @Operation(summary = "Скачать файлы с рецептами")
    public ResponseEntity<InputStreamResource> downloadRecipeDataFile() throws FileNotFoundException {
        File file = recipeFileService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка нового файла с заменой существующего")
    public ResponseEntity<Void> uploadRecipeDataFile(@RequestParam MultipartFile file) {
        recipeFileService.cleanDataFile();
        File dataFile = recipeFileService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {

            IOUtils.copy(file.getInputStream(), fos);
           return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
