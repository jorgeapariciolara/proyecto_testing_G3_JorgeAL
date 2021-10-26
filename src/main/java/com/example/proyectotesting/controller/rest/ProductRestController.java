package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Escenario 1:
 * peticiones HTTP --> ProductController --> ProductRepository --> Base de datos (H2)
 *
 * Escenario 2:
 * peticiones HTTP --> ProductController --> ProductService --> ProductRepository --> Base de datos (H2)
 *
 * SmartDevice
 * |
 * |
 * SmartPhone
 */
@RestController // rest
public class ProductRestController {


    private ProductService productService; // dependencia

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // metodos HTTP



}
