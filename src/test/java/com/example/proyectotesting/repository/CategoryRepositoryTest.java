package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository repository;

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
