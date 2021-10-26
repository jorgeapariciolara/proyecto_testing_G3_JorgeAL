package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.service.CategoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {

    private CategoryService categoryService;

    public CategoryRestController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
