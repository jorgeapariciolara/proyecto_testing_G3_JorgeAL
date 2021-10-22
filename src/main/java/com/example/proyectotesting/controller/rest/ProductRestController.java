package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController --> ProductService --> ProductRepository --> Base de datos (H2)
 */
@RestController // rest
public class ProductRestController {


    private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

}
