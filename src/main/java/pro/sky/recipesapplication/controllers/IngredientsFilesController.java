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
import pro.sky.recipesapplication.service.IngredientFileService;

import java.io.*;

@RestController
@RequestMapping("/ingredient/files")
@Tag(name = "Файлы с ингредиентами", description = "CRUD-операции и другие эндпоинты для работы с файлами ингредиентов")
public class IngredientsFilesController {
    private final IngredientFileService ingredientFileService;

    public IngredientsFilesController(IngredientFileService ingredientFileService) {
        this.ingredientFileService = ingredientFileService;
    }
    @GetMapping("/export")
    @Operation (summary = "Скачать файлы с ингредиентами")
    public ResponseEntity<InputStreamResource> downloadIngredientDataFile() throws FileNotFoundException {
        File file = ingredientFileService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"IngredientsLog.json\"" )
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка нового файла с заменой существующего")
    public ResponseEntity<Void> uploadIngredientDataFile(@RequestParam MultipartFile file) {
        ingredientFileService.cleanDataFile();
        File dataFile = ingredientFileService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();


//        try (BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
//                FileOutputStream fos = new FileOutputStream(dataFile);
//        BufferedOutputStream bos = new BufferedOutputStream(fos)){
//            byte[] buffer = new byte[1024];
//            while (bis.read(buffer) > 0) {
//                bos.write(buffer);
//            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }



    }

}
