package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryServiceImplTest {

    @Autowired
    CategoryRepository repository;

    @DisplayName("Funcionalidad buscar sobre categorías")
    @Nested
    class RetrieveTest {
        @DisplayName("Contar las categorías")
        @Test
        void countTest() {
            Long result = repository.count();
            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals(4, result)
            );
        }
        @DisplayName("Buscar todas las categorías")
        @Test
        void findAllTest() {
            List<Category> categories = repository.findAll();
            assertAll(
                    () -> assertNotNull(categories),
                    () -> assertTrue(categories.size() == 4),
                    () -> assertEquals("Libros", categories.get(0).getName()),
                    () -> assertEquals("black", categories.get(0).getColor()),
                    () -> assertEquals("Computación", categories.get(1).getName()),
                    () -> assertEquals("blue", categories.get(1).getColor()),
                    () -> assertEquals("Hogar", categories.get(2).getName()),
                    () -> assertEquals("white", categories.get(2).getColor()),
                    () -> assertEquals("Moda", categories.get(3).getName()),
                    () -> assertEquals("brown", categories.get(3).getColor())
            );
        }

        @DisplayName("Buscar las categorías por el color")
        @Test
        void findByColorTest() {
            Optional<Category> optionalCategory1 = repository.findByColor("black");
            Optional<Category> optionalCategory2 = repository.findByColor("blue");
            Optional<Category> optionalCategory3 = repository.findByColor("white");
            Optional<Category> optionalCategory4 = repository.findByColor("brown");
            assertAll(
                    () -> assertNotNull(optionalCategory1),
                    () -> assertEquals("Libros", optionalCategory1.get().getName()),
                    () -> assertNotNull(optionalCategory2),
                    () -> assertEquals("Computación", optionalCategory2.get().getName()),
                    () -> assertNotNull(optionalCategory3),
                    () -> assertEquals("Hogar", optionalCategory3.get().getName()),
                    () -> assertNotNull(optionalCategory4),
                    () -> assertEquals("Moda", optionalCategory4.get().getName())
            );
        }
    }

    @DisplayName("Funcionalidad crear y modificar sobre categorías")
    @Nested
    class SaveTest {
        @DisplayName("Crear una nueva categoría")
        @Test
        void createOkTest() {
            Category category = new Category("Categoria JUnit", "verde");
            Category categoryDB = repository.save(category);
            assertNotNull(categoryDB);
            assertNotNull(categoryDB.getId());
            assertTrue(repository.existsById(categoryDB.getId()));
            assertEquals("Categoria JUnit", repository.getById(categoryDB.getId()).getName());
            assertEquals("verde", repository.getById(categoryDB.getId()).getColor());
        }
        @DisplayName("Modificar una categoría")
        @Test
        void updateOkTest() {
            Category category = new Category("Libros-EDITADO", "NEGRO");
            Category categoryDB = repository.save(category);
            assertNotNull(categoryDB);
            assertNotNull(categoryDB.getId());
            assertTrue(repository.existsById(categoryDB.getId()));
            assertEquals("Libros-EDITADO", repository.getById(categoryDB.getId()).getName());
            assertEquals("NEGRO", repository.getById(categoryDB.getId()).getColor());
        }
    }

    @DisplayName("Funcionalidad borrar sobre categorías")
    @Nested
    class DeleteTest {
        @DisplayName("Borrar una categoría")
        @Test
        void deleteOneOkTest(){

        }
        @DisplayName("Borrar todas las categorías")
        @Test
        void deleteAllOkTest(){

        }


    }

}
