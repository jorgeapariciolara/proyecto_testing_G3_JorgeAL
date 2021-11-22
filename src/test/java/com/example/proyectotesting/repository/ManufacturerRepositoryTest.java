package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Manufacturer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Testeo de los métodos presentes en la clase ManufacturerRepository del package Repository
 */
@DataJpaTest
public class ManufacturerRepositoryTest {

    @Autowired
    ManufacturerRepository repository;

    @DisplayName("Funcionalidad buscar sobre fabricantes utilizando el año de fundación")
    @Nested
    class RetrieveYearTest {
        @Test
        void findByYearOkTest() {
            List<Manufacturer> manufacturers1 = repository.findByYear(1949);
            List<Manufacturer> manufacturers2 = repository.findByYear(1977);
            assertAll(
                    () -> assertEquals(2,repository.count()),
                    () -> assertFalse(manufacturers1.isEmpty()),
                    () -> assertFalse(manufacturers2.isEmpty()),
                    () -> assertEquals(1L, manufacturers1.get(0).getId()),
                    () -> assertEquals("Adidas", manufacturers1.get(0).getName()),
                    () -> assertEquals("2343235325G", manufacturers1.get(0).getCif()),
                    () -> assertEquals(60000, manufacturers1.get(0).getNumEmployees()),
                    () -> assertEquals(3L, manufacturers2.get(0).getId()),
                    () -> assertEquals("Nike", manufacturers2.get(0).getName()),
                    () -> assertEquals("2343235325G", manufacturers2.get(0).getCif()),
                    () -> assertEquals(60000, manufacturers2.get(0).getNumEmployees())
            );
        }
        @Test
        void findByYearNotContainsTest() {
            List<Manufacturer> manufacturers1 = repository.findByYear(1849);
            List<Manufacturer> manufacturers2 = repository.findByYear(1997);
            assertAll(
                    () -> assertEquals(2,repository.count()),
                    () -> assertTrue(manufacturers1.isEmpty()),
                    () -> assertTrue(manufacturers2.isEmpty())
            );
        }
        @Test
        void findByYearNullTest() {
            List<Manufacturer> manufacturers1 = repository.findByYear(null);
            assertAll(
                    () -> assertEquals(2,repository.count()),
                    () -> assertTrue(manufacturers1.isEmpty())
            );
        }
    }

    @DisplayName("Funcionalidad buscar sobre fabricantes utilizando el año de fundación")
    @Nested
    class RetrieveCountryTest {
        @Test
        void findManufacturerByDirectionCountryOkTest() {
            List<Manufacturer> manufacturers = repository.findManufacturerByDirectionCountry("Spain");
            assertAll(
                    () -> assertEquals(2,repository.count()),
                    () -> assertFalse(manufacturers.isEmpty()),
                    () -> assertEquals(2, manufacturers.size()),
                    () -> assertEquals(1L, manufacturers.get(0).getId()),
                    () -> assertEquals("Adidas", manufacturers.get(0).getName()),
                    () -> assertEquals("2343235325G", manufacturers.get(0).getCif()),
                    () -> assertEquals(60000, manufacturers.get(0).getNumEmployees()),
                    () -> assertEquals(3L, manufacturers.get(1).getId()),
                    () -> assertEquals("Nike", manufacturers.get(1).getName()),
                    () -> assertEquals("2343235325G", manufacturers.get(1).getCif()),
                    () -> assertEquals(60000, manufacturers.get(1).getNumEmployees())
            );
        }
        @Test
        void findManufacturerByDirectionCountryNotContainsTest() {
            List<Manufacturer> manufacturers = repository.findManufacturerByDirectionCountry("Belgium");
            assertAll(
                    () -> assertEquals(2,repository.count()),
                    () -> assertTrue(manufacturers.isEmpty())
            );
        }
    }
}
