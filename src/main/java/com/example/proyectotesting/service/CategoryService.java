package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findOne(Long id);
    
    Category save(Category category);

    Long count();

    boolean deleteById(Long id);
}
