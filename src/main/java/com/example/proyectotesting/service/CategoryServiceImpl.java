package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository repository; // dependencia

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Category> findOne(String color) {
        if (color == null)
            return Optional.empty();

        return repository.findByColor(color);
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
