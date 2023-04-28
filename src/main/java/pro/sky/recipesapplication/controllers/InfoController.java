package pro.sky.recipesapplication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InfoController {

    @GetMapping
    public String hello() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info() {
        return "Ахмедов Тимур, Recipes App, 11.03.2023, приложение для хранения рецептов";
    }
}
