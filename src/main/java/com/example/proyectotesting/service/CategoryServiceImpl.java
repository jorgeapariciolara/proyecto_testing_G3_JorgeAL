package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {
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
