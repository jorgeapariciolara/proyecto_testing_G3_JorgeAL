package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testeo de los métodos presentes en la clase CategoryRepository del package Repository
 */

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository repository;

    @DisplayName("Buscar las categorías utilizando un color que está definido")
    @Test
    void findByColorOkTest() {
        Optional<Category> optionalCategory1 = repository.findByColor("black");
        Optional<Category> optionalCategory2 = repository.findByColor("blue");
        Optional<Category> optionalCategory3 = repository.findByColor("white");
        Optional<Category> optionalCategory4 = repository.findByColor("brown");
        assertAll(
                () -> assertTrue(optionalCategory1.isPresent()),
                () -> assertEquals("Libros", optionalCategory1.get().getName()),
                () -> assertTrue(optionalCategory2.isPresent()),
                () -> assertEquals("Computación", optionalCategory2.get().getName()),
                () -> assertTrue(optionalCategory3.isPresent()),
                () -> assertEquals("Hogar", optionalCategory3.get().getName()),
                () -> assertTrue(optionalCategory4.isPresent()),
                () -> assertEquals("Moda", optionalCategory4.get().getName())
        );
    }
    @DisplayName("Buscar las categorías utilizando un color que no está definido")
    @Test
    void findByColorNotContainsTest() {
        Optional<Category> optionalCategory1 = repository.findByColor("amarillo");
        Optional<Category> optionalCategory2 = repository.findByColor("verde");
        assertAll(
                () -> assertFalse(optionalCategory1.isPresent()),
                () -> assertFalse(optionalCategory2.isPresent())
        );
    }
}
