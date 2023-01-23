package me.iagfarov.hwmaven.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @GetMapping
    public String helloWorld() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info() {
        return "Сергей " +
                "Проект: Книга рецептов " +
                "Дата создания 22/01/2023 " +
                "Описание проекта - Сборник рецептов народов мира в котором собраны не только вкусные рецепты, но и еще и полезные ";
    }
}