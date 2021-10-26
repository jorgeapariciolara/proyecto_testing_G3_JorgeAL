package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Escenario 1:
 * peticiones HTTP --> ProductRestController --> ProductRepository --> Base de datos (H2)
 *
 * Escenario 2:
 * peticiones HTTP --> ProductRestController --> ProductService --> ProductRepository --> Base de datos (H2)
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
    @GetMapping("/api/products")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> findOne(@PathVariable Long id){
        Optional<Product> productOpt = productService.findOne(id);
        return ResponseEntity.of(productOpt);
    }




}
