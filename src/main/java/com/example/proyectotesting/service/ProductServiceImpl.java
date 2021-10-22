package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProductService --> ProductRepository --> Base de datos
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository; // dependencia

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAllByPriceBetween(Double min, Double max) {
        return null;
    }

    @Override
    public List<Product> findAllByManufacturer(String manufacturer) {
        return null;
    }

    @Override
    public Double calculateShippingCost(Product product, Direction direction) {
        return 0d;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
