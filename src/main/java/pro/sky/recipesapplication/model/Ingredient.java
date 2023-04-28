package pro.sky.recipesapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String title;
    private int number;
    private String measureUnit;

}
