package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testeo de los métodos presentes en la clase DirectionRepository del package Repository
 *
 * Saco el repositorio por consola porque no me cuadran los id en los test que hago y veo que tienen asignados los
 * id 2 y 4:
 *      System.out.println(repository.findAll());
 *         [Direction [id=2, street=Calle falsa, postalCode=33010, city=León, country=Spain],
 *         Direction [id=4, street=Calle verdadera, postalCode=11322, city=Madrid, country=Spain]]
 */

@DataJpaTest
public class DirectionRepositoryTest {

    @Autowired
    DirectionRepository repository;

    @DisplayName("Buscar utilizando el id de la dirección del fabricante")
    @Nested
    class RetrieveIdTest {
        @Test
        void findAllByIdOkTest() {
            List<Direction> directions1 = repository.findAllById(4L);
            List<Direction> directions2 = repository.findAllById(2L);
            assertAll(
                    () -> assertEquals(2, repository.count()),
                    () -> assertEquals(1, directions1.size()),
                    () -> assertEquals("Nike", directions1.get(0).getManufacturer().getName()),
                    () -> assertEquals(4, directions1.get(0).getId()),
                    () -> assertEquals("Calle verdadera", directions1.get(0).getStreet()),
                    () -> assertEquals("11322", directions1.get(0).getPostalCode()),
                    () -> assertEquals(1, directions2.size()),
                    () -> assertEquals("Adidas", directions2.get(0).getManufacturer().getName()),
                    () -> assertEquals(2, directions2.get(0).getId()),
                    () -> assertEquals("Calle falsa", directions2.get(0).getStreet()),
                    () -> assertEquals("33010", directions2.get(0).getPostalCode())
            );
        }
        @Test
        void findAllByIdNotContainsTest() {
            List<Direction> directions1 = repository.findAllById(9L);
            assertAll(
                    () -> assertEquals(2, repository.count()),
                    () -> assertEquals(0, directions1.size())
            );
        }
    }

    @Test
    void findByCityAndCountryTest() {
        List<Direction> directions1 = repository.findByCityAndCountry("Madrid","Spain");
        List<Direction> directions2 = repository.findByCityAndCountry("León","Spain");
        assertAll(
                () -> assertEquals(2,repository.count()),
                () -> assertEquals(1,directions1.size()),
                () -> assertEquals("Nike",directions1.get(0).getManufacturer().getName()),
                () -> assertEquals(4,directions1.get(0).getId()),
                () -> assertEquals("Calle verdadera",directions1.get(0).getStreet()),
                () -> assertEquals("11322",directions1.get(0).getPostalCode()),
                () -> assertEquals(1,directions2.size()),
                () -> assertEquals("Adidas",directions2.get(0).getManufacturer().getName()),
                () -> assertEquals(2,directions2.get(0).getId()),
                () -> assertEquals("Calle falsa",directions2.get(0).getStreet()),
                () -> assertEquals("33010",directions2.get(0).getPostalCode())
        );
    }
}
