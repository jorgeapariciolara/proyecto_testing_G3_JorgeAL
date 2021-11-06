package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @DisplayName("Buscar utilizando el id del fabricante")
    @Nested
    class RetrieveIdTest {
        @DisplayName("Buscar los productos cuando el id del fabricante es conocido")
        @Test
        void findAllByManufacturerIdTest() {
            /*
            Busco los productos porque no aparecen como en ProyectoTestingAplication, id de los Products del 1 al 5,
            sino con id del 9 al 13. También me aseguro de cuáles son los id de los manufacturer: 1 y 3.
                    System.out.println(repository.findById(9L).get());
                    System.out.println(repository.findById(10L).get());
                    System.out.println(repository.findById(11L).get());
                    System.out.println(repository.findById(12L).get());
                    System.out.println(repository.findById(13L).get());
            */
            List<Product> productsAdidas = repository.findAllByManufacturerId(1L);
            List<Product> productsNike = repository.findAllByManufacturerId(3L);
            assertAll(
                    () -> assertNotNull(repository.count()),
                    () -> assertTrue(repository.count() == 5),
                    () -> assertNotNull(productsAdidas),
                    () -> assertNotNull(productsNike),
                    () -> assertEquals(3, productsAdidas.size()),
                    () -> assertEquals(1, productsNike.size())
            );
        }
        @DisplayName("Buscar los productos cuando el id del fabricante es nulo")
        @Test
        void findAllByManufacturerIdIsNullTest() {
            List<Product> productsNull = repository.findAllByManufacturerId(null);
            assertAll(
                    () -> assertNotNull(repository.count()),
                    () -> assertTrue(repository.count() == 5),
                    () -> assertEquals(1, productsNull.size())
            );
        }
        @DisplayName("Buscar los productos cuando el id del fabricante es conocido o nulo")
        @Test
        void findAllByManufacturerIdOrManufacturerIdIsNullTest() {
            List<Product> productsAdidas = repository.findAllByManufacturerId(1L);
            List<Product> productsNike = repository.findAllByManufacturerId(3L);
            List<Product> productsNull = repository.findAllByManufacturerId(null);
            assertAll(
                    () -> assertNotNull(repository.count()),
                    () -> assertTrue(repository.count() == 5),
                    () -> assertNotNull(productsAdidas),
                    () -> assertNotNull(productsNike),
                    () -> assertEquals(3, productsAdidas.size()),
                    () -> assertEquals(1, productsNike.size()),
                    () -> assertEquals(1, productsNull.size())
            );
        }
    }
    @DisplayName("Buscar utilizando el nombre del fabricante")
    @Nested
    class RetrieveNameTest {
        @DisplayName("Buscar los productos utilizando el nombre del fabricante")
        @Test
        void findByManufacturerNameOkTest() {
            List<Product> productsAdidas = repository.findByManufacturerName("Adidas");
            List<Product> productsNike = repository.findByManufacturerName("Nike");
            assertAll(
                    () -> assertNotNull(repository.count()),
                    () -> assertTrue(repository.count() == 5),
                    () -> assertNotNull(productsAdidas),
                    () -> assertNotNull(productsNike),
                    () -> assertEquals(3, productsAdidas.size()),
                    () -> assertEquals(1, productsNike.size())
            );
        }
        @DisplayName("Buscar los productos utilizando un nombre de fabricante inexistente")
        @Test
        void findByManufacturerNameNotContainsTest() {
            List<Product> products = repository.findByManufacturerName("Matel");
            assertAll(
                    () -> assertNotNull(repository.count()),
                    () -> assertTrue(repository.count() == 5),
                    () -> assertTrue(products.isEmpty())
            );
        }
        @DisplayName("Buscar los productos utilizando un nombre de fabricante nulo")
        @Test
        void findByManufacturerNameNullTest() {
            List<Product> productsNull = repository.findByManufacturerName(null);
            assertAll(
                    () -> assertNotNull(repository.count()),
                    () -> assertTrue(repository.count() == 5),
                    () -> assertEquals(1, productsNull.size())
            );
        }
    }

    @DisplayName("Buscar los productos que existen entre dos precios")
    @Test
    void findByPriceBetweenTest() {
        List<Product> products1 = repository.findByPriceBetween(0.00,50.00);
        List<Product> products2 = repository.findByPriceBetween(50.00,100.00);
        assertAll(
                () -> assertNotNull(products1),
                () -> assertNotNull(products2),
                () -> assertEquals(1, products1.stream().count()),
                () -> assertEquals(4, products2.stream().count()),

                () -> assertEquals(9L, products1.get(0).getId()),
                () -> assertEquals("Balón", products1.get(0).getName()),
                () -> assertEquals("Adidas", products1.get(0).getManufacturer().getName()),
                () -> assertEquals("Libros", products1.get(0).getCategories().get(0).getName()),
                () -> assertEquals("Computación", products1.get(0).getCategories().get(1).getName()),
                () -> assertEquals(10.99, products1.get(0).getPrice()),
                () -> assertEquals( 10L, products2.get(0).getId()),
                () -> assertEquals("Mesa", products2.get(0).getName()),
                () -> assertEquals("Adidas", products2.get(0).getManufacturer().getName()),
                () -> assertEquals("Libros", products2.get(0).getCategories().get(0).getName()),
                () -> assertEquals("Computación", products2.get(0).getCategories().get(1).getName()),
                () -> assertEquals("Hogar", products2.get(0).getCategories().get(2).getName()),
                () -> assertEquals(99.99, products2.get(0).getPrice()),
                () -> assertEquals( 11L, products2.get(1).getId()),
                () -> assertEquals("Botella", products2.get(1).getName()),
                () -> assertEquals("Adidas", products2.get(1).getManufacturer().getName()),
                () -> assertEquals("Libros", products2.get(1).getCategories().get(0).getName()),
                () -> assertEquals("Moda", products2.get(1).getCategories().get(1).getName()),
                () -> assertEquals(99.99, products2.get(1).getPrice()),
                () -> assertEquals( 12L, products2.get(2).getId()),
                () -> assertEquals("WebCam", products2.get(2).getName()),
                () -> assertEquals("Nike", products2.get(2).getManufacturer().getName()),
                () -> assertEquals(0, products2.get(2).getCategories().size()),
                () -> assertEquals(99.99, products2.get(2).getPrice()),
                () -> assertEquals( 13L, products2.get(3).getId()),
                () -> assertEquals("Zapatillas", products2.get(3).getName()),
                () -> assertEquals(null, products2.get(3).getManufacturer()),
                () -> assertEquals(0, products2.get(3).getCategories().size()),
                () -> assertEquals(99.99, products2.get(3).getPrice())
        );
    }
}
