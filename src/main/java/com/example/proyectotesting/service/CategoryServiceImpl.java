package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAll() {
        List<Category> categoryes = new ArrayList<>();
        categoryes.add(new Category("Categoria1", "Rojo"));
        categoryes.add(new Category("Categoria2", "Azul"));
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findOne(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        try {
            return categoryRepository.findById(id);
        } catch (NullPointerException n) {
            n.printStackTrace(System.out);
            System.out.println("No se encontro el id con id: " + id);
        }

        return Optional.of(new Category());

    }

    @Override
    public Category save(Category category) {
        if (category == null)
            return null;
        else if (category != null)
            System.out.println("No se ha encontrado la categoria");

        return categoryRepository.save(category);

    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !categoryRepository.existsById(id)) {
            System.out.println("No existe id a eliminar");
            return false;
        } else {
            categoryRepository.deleteById(id);
        }
        return true;

    }
}
