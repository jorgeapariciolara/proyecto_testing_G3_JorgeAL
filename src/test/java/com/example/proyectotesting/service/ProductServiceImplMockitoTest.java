package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplMockitoTest {

    ProductRepository repositoryMock;

    ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(ProductRepository.class);

        service = new ProductServiceImpl(repositoryMock);
    }

    @DisplayName("Contar el número de productos")
    @Test
    void count() {
        when(repositoryMock.count()).thenReturn(5l);
        Long result = service.count();
        assertNotNull(result);
        assertEquals(5, result);
    }

    @DisplayName("Comprobar que existen los id de los productos")
    @Test
    void existById() {
        when(repositoryMock.existsById(anyLong())).thenReturn(true);
        Boolean result = service.existsById(anyLong());
        assertNotNull(result);
        verify(repositoryMock).existsById(anyLong());
    }

    @DisplayName("Funcionalidad BUSCAR sobre PRODUCTOS")
    @Nested
    class RetrieveTest {
        @DisplayName("Buscar todos los productos")
        @Test
        void findAllTest() {
            Manufacturer adidas = new Manufacturer();
            Manufacturer nike = new Manufacturer();
            Manufacturer none = new Manufacturer();
            List<Product> products = Arrays.asList(
                    new Product("Balón", "Lorem impsum dolor", 2, 10.99, adidas),
                    new Product("Mesa", "Lorem impsum dolor", 8, 99.99, adidas),
                    new Product("Botella", "Lorem impsum dolor", 5, 99.99, adidas),
                    new Product("WebCam", "Lorem impsum dolor", 12, 99.99, nike),
                    new Product("Zapatillas", "Lorem impsum dolor", 12, 99.99, none)
            );
            when(repositoryMock.findAll()).thenReturn(products);
            List<Product> result = service.findAll();
            assertNotNull(result);
            assertEquals(5, result.size());
            verify(repositoryMock).findAll();
        }
        @DisplayName("OPTIONAL - Buscar un producto con un id conocido")
        @Test
        void findOneOkTest() {
            Manufacturer adidas = new Manufacturer();
            Manufacturer nike = new Manufacturer();
            Manufacturer none = new Manufacturer();
            Product product1 = new Product(
                    "Balón", "Lorem impsum dolor", 2, 10.99, adidas);
            Product product2 = new Product(
                    "Mesa", "Lorem impsum dolor", 8, 99.99, adidas);
            Product product3 = new Product(
                    "Botella", "Lorem impsum dolor", 5, 99.99, adidas);
            Product product4 = new Product(
                    "WebCam", "Lorem impsum dolor", 12, 99.99, nike);
            Product product5 = new Product(
                    "Zapatillas", "Lorem impsum dolor", 12, 99.99, none);
            when(repositoryMock.findById(1L)).thenReturn(Optional.of(product1));
            when(repositoryMock.findById(2L)).thenReturn(Optional.of(product2));
            when(repositoryMock.findById(3L)).thenReturn(Optional.of(product3));
            when(repositoryMock.findById(4L)).thenReturn(Optional.of(product4));
            when(repositoryMock.findById(5L)).thenReturn(Optional.of(product5));
            Optional<Product> result1 = service.findOne(1L);
            Optional<Product> result2 = service.findOne(2L);
            Optional<Product> result3 = service.findOne(3L);
            Optional<Product> result4 = service.findOne(4L);
            Optional<Product> result5 = service.findOne(5L);
            assertAll(
                    () -> assertTrue(result1.isPresent()),
                    // () -> assertEquals(1L, result1.get().getId()),
                    () -> assertEquals("Balón", result1.get().getName()),
                    () -> assertEquals("Lorem impsum dolor", result1.get().getDescription()),
                    () -> assertEquals(2, result1.get().getQuantity()),
                    () -> assertEquals(10.99, result1.get().getPrice()),
                    // () -> assertEquals("adidas", result1.get().getManufacturer().getName()),
                    () -> assertTrue(result2.isPresent()),
                    // () -> assertEquals(2L, result2.get().getId()),
                    () -> assertEquals("Mesa", result2.get().getName()),
                    () -> assertEquals("Lorem impsum dolor", result2.get().getDescription()),
                    () -> assertEquals(8, result2.get().getQuantity()),
                    () -> assertEquals(99.99, result2.get().getPrice()),
                    // () -> assertEquals("adidas", result2.get().getManufacturer().getName()),
                    () -> assertTrue(result3.isPresent()),
                    // () -> assertEquals(3L, result3.get().getId()),
                    () -> assertEquals("Botella", result3.get().getName()),
                    () -> assertEquals("Lorem impsum dolor", result3.get().getDescription()),
                    () -> assertEquals(5, result3.get().getQuantity()),
                    () -> assertEquals(99.99, result3.get().getPrice()),
                    // () -> assertEquals("adidas", result2.get().getManufacturer().getName()),
                    () -> assertTrue(result4.isPresent()),
                    // () -> assertEquals(4L, result4.get().getId()),
                    () -> assertEquals("WebCam", result4.get().getName()),
                    () -> assertEquals("Lorem impsum dolor", result4.get().getDescription()),
                    () -> assertEquals(12, result4.get().getQuantity()),
                    () -> assertEquals(99.99, result4.get().getPrice()),
                    // () -> assertEquals("nike", result1.get().getManufacturer().getName()),
                    () -> assertTrue(result5.isPresent()),
                    // () -> assertEquals(5L, result5.get().getId()),
                    () -> assertEquals("Zapatillas", result5.get().getName()),
                    () -> assertEquals("Lorem impsum dolor", result5.get().getDescription()),
                    () -> assertEquals(12, result5.get().getQuantity()),
                    () -> assertEquals(99.99, result5.get().getPrice())
                    // () -> assertEquals("none", result5.get().getManufacturer().getName()),
            );
            verify(repositoryMock).findById(1L);
            verify(repositoryMock).findById(2L);
            verify(repositoryMock).findById(3L);
            verify(repositoryMock).findById(4L);
            verify(repositoryMock).findById(5L);
        }
        @DisplayName("OPTIONAL - Buscar un producto con cualquier id conocido")
        @Test
        void findOneAnyTest() {
            Manufacturer adidas = new Manufacturer();
            Product product1 = new Product("Balón", "Lorem impsum dolor",
                    2, 10.99, adidas);
            when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(product1));
            Optional<Product> result1 = service.findOne(1L);
            assertAll(
                    () -> assertTrue(result1.isPresent()),
                    () -> assertEquals("Balón", result1.get().getName()),
                    () -> assertEquals("Lorem impsum dolor", result1.get().getDescription()),
                    () -> assertEquals(2, result1.get().getQuantity()),
                    () -> assertEquals(10.99, result1.get().getPrice())
            );
            verify(repositoryMock).findById(anyLong());
        }
        @DisplayName("OPTIONAL - Buscar un producto con un id < 0")
        @Test
        void findOneNegativeTest() {
            when(repositoryMock.findById(anyLong())).thenThrow(IllegalArgumentException.class);
            Optional<Product> productOpt = service.findOne(-9L);
            assertAll(
                    () -> assertTrue(productOpt.isEmpty())
            );
            verifyNoInteractions(repositoryMock);
        }
        @DisplayName("OPTIONAL - Buscar un producto con un id nulo")
        @Test
        void findOneNullTest() {
            when(repositoryMock.findById(anyLong())).thenThrow(IllegalArgumentException.class);
            Optional<Product> productOpt = service.findOne(null);
            assertAll(
                    () -> assertTrue(productOpt.isEmpty())
            );
            verifyNoInteractions(repositoryMock);
        }
        @DisplayName("OPTIONAL - Buscar un producto con un id que no existe en la base de datos")
        @Test
        void findOneNotContainsTest() {
            Manufacturer adidas = new Manufacturer();
            Product product1 = new Product("Gorrilla", "Verde azulada",
                    12, 17.59, adidas);
            when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(product1));
            Optional<Product> result1 = service.findOne(999L);
            assertAll(
                    () -> assertTrue(result1.isPresent()),
                    () -> assertEquals("Gorrilla", result1.get().getName()),
                    () -> assertEquals("Verde azulada", result1.get().getDescription()),
                    () -> assertEquals(12, result1.get().getQuantity()),
                    () -> assertEquals(17.59, result1.get().getPrice())
            );
            verify(repositoryMock).findById(anyLong());
        }

        @DisplayName("Buscar un producto entre dos precios (mínimo y máximo)")
        @Test
        void findByPriceBetween() {
            Manufacturer adidas = new Manufacturer();
            Manufacturer nike = new Manufacturer();
            Manufacturer none = new Manufacturer();
            List<Product> products1 = Arrays.asList(
                    new Product("Balón", "Lorem impsum dolor", 2, 10.99, adidas)
            );
            List<Product> products2 = Arrays.asList(
                    new Product("Mesa", "Lorem impsum dolor", 8, 99.99, adidas),
                    new Product("Botella", "Lorem impsum dolor", 5, 99.99, adidas),
                    new Product("WebCam", "Lorem impsum dolor", 12, 99.99, nike),
                    new Product("Zapatillas", "Lorem impsum dolor", 12, 99.99, none)
            );
            when(repositoryMock.findByPriceBetween(0.0,50.0)).thenReturn(products1);
            when(repositoryMock.findByPriceBetween(50.0,100.0)).thenReturn(products2);
            List<Product> productsOne = service.findByPriceBetween(0.0,50.0);
            List<Product> productsTwo = service.findByPriceBetween(50.0,100.0);
            assertAll(
                    () -> assertFalse(productsOne.isEmpty()),
                    () -> assertFalse(productsTwo.isEmpty()),
                    () -> assertEquals(1,productsOne.size()),
                    () -> assertEquals(4,productsTwo.size())
            );
        }

        @DisplayName("Buscar un producto utilizando el fabricante")
        @Test
        void findByManufacturerTest() {
            Manufacturer adidas = new Manufacturer();
            Manufacturer nike = new Manufacturer();
            Manufacturer none = new Manufacturer();
            List<Product> products1 = Arrays.asList(
                    new Product("Balón", "Lorem impsum dolor", 2, 10.99, adidas),
                    new Product("Mesa", "Lorem impsum dolor", 8, 99.99, adidas),
                    new Product("Botella", "Lorem impsum dolor", 5, 99.99, adidas)
            );
            List<Product> products2 = Arrays.asList(
                    new Product("WebCam", "Lorem impsum dolor", 12, 99.99, nike)
            );
            List<Product> products3 = Arrays.asList(
                    new Product("Zapatillas", "Lorem impsum dolor", 12, 99.99, none)
            );
            when(repositoryMock.findByManufacturerName("adidas")).thenReturn(products1);
            when(repositoryMock.findByManufacturerName("nike")).thenReturn(products2);
            when(repositoryMock.findByManufacturerName("none")).thenReturn(products3);

            List<Product> productsOne = service.findByManufacturer("adidas");
            List<Product> productsTwo = service.findByManufacturer("nike");
            List<Product> productsThree = service.findByManufacturer("none");

            assertAll(
                    () -> assertFalse(productsOne.isEmpty()),
                    () -> assertFalse(productsTwo.isEmpty()),
                    () -> assertFalse(productsThree.isEmpty()),
                    () -> assertEquals(3,productsOne.size()),
                    () -> assertEquals(1,productsTwo.size()),
                    () -> assertEquals(1,productsThree.size())
            );
        }
    }

    @DisplayName("Funcionalidad CREAR y MODIFICAR sobre productos")
    @Nested
    class SaveTest {
        @DisplayName("Guardar un producto con id nulo")
        @Test
        void saveNullTest() {
            Manufacturer nike = new Manufacturer();
            Product newProduct = new Product(
                    "Gorra", "Azul con lunares amarillos", 7, 13.99, nike);
            when(repositoryMock.save(any())).thenReturn(newProduct);
            Product result1 = service.save(newProduct);
            assertAll(
                    () -> assertNotNull(result1),
                    () -> assertEquals(6, result1.getId()),
                    // org.opentest4j.AssertionFailedError: expected: <6> but was: <null>
                    // No asigna ningún id al nuevo producto. Le deja id nulo
                    () -> assertEquals("Gorra", result1.getName()),
                    () -> assertEquals("Azul con lunares amarillos", result1.getDescription()),
                    () -> assertEquals(7, result1.getQuantity()),
                    () -> assertEquals(13.99, result1.getPrice()),
                    () -> assertEquals("nike", result1.getManufacturer().getName())
                    //	org.opentest4j.AssertionFailedError: expected: <nike> but was: <null>
                    // No guarda el fabricante
            );
            verify(repositoryMock).save(newProduct);
        }
        @DisplayName("Guardar un producto con id conocido")
        @Test
        void saveOkTest() {
            Manufacturer adidas = new Manufacturer();
            Manufacturer nike = new Manufacturer();
            Manufacturer none = new Manufacturer();
            Product product1 = new Product(
                    "Balón", "Lorem impsum dolor", 2, 10.99, adidas);
            Product product2 = new Product(
                    "Mesa", "Lorem impsum dolor", 8, 99.99, adidas);
            Product product3 = new Product(
                    "Botella", "Lorem impsum dolor", 5, 99.99, adidas);
            Product product4 = new Product(
                    "WebCam", "Lorem impsum dolor", 12, 99.99, nike);
            Product product5 = new Product(
                    "Zapatillas", "Lorem impsum dolor", 12, 99.99, none);
            when(repositoryMock.save(product1)).thenReturn(product1);
            when(repositoryMock.save(product2)).thenReturn(product2);
            when(repositoryMock.save(product3)).thenReturn(product3);
            when(repositoryMock.save(product4)).thenReturn(product4);
            when(repositoryMock.save(product5)).thenReturn(product5);

            Product result1 = service.save(product1);
            Product result2 = service.save(product2);
            Product result3 = service.save(product3);
            Product result4 = service.save(product4);
            Product result5 = service.save(product5);
            assertAll(
                    () -> assertNotNull(result1),
                    // () -> assertEquals(1L, result1.getId()),
                    () -> assertEquals("Balón", result1.getName()),
                    () -> assertEquals("Lorem impsum dolor", result1.getDescription()),
                    () -> assertEquals(2, result1.getQuantity()),
                    () -> assertEquals(10.99, result1.getPrice()),
                    // () -> assertEquals("adidas", result1.getManufacturer().getName()),
                    () -> assertNotNull(result2),
                    // () -> assertEquals(2L, result2.getId()),
                    () -> assertEquals("Mesa", result2.getName()),
                    () -> assertEquals("Lorem impsum dolor", result2.getDescription()),
                    () -> assertEquals(8, result2.getQuantity()),
                    () -> assertEquals(99.99, result2.getPrice()),
                    // () -> assertEquals("adidas", result2.getManufacturer().getName()),
                    () -> assertNotNull(result3),
                    // () -> assertEquals(3L, result3.getId()),
                    () -> assertEquals("Botella", result3.getName()),
                    () -> assertEquals("Lorem impsum dolor", result3.getDescription()),
                    () -> assertEquals(5, result3.getQuantity()),
                    () -> assertEquals(99.99, result3.getPrice()),
                    // () -> assertEquals("adidas", result3.getManufacturer().getName()),
                    () -> assertNotNull(result4),
                    // () -> assertEquals(4L, result4.getId()),
                    () -> assertEquals("WebCam", result4.getName()),
                    () -> assertEquals("Lorem impsum dolor", result4.getDescription()),
                    () -> assertEquals(12, result4.getQuantity()),
                    () -> assertEquals(99.99, result4.getPrice()),
                    // () -> assertEquals("nike", result4.getManufacturer().getName()),
                    () -> assertNotNull(result5),
                    // () -> assertEquals(5L, result5.getId()),
                    () -> assertEquals("Zapatillas", result5.getName()),
                    () -> assertEquals("Lorem impsum dolor", result5.getDescription()),
                    () -> assertEquals(12, result5.getQuantity()),
                    () -> assertEquals(99.99, result5.getPrice())
                    // () -> assertEquals("none", result5.getManufacturer().getName())
            );
            verify(repositoryMock).save(product1);
            verify(repositoryMock).save(product2);
            verify(repositoryMock).save(product3);
            verify(repositoryMock).save(product4);
            verify(repositoryMock).save(product5);
        }
    }

    @DisplayName("Funcionalidad BORRAR sobre productos")
    @Nested
    class DeleteTest {
        @Test
        @DisplayName("Borrar un producto de id nulo")
        void deleteNullTest(){
            boolean result = service.deleteById(null);
            assertFalse(result);
        }
        @Test
        @DisplayName("Borrar un producto de id negativo")
        void deleteNegativeTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(-9L);
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(-9L));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar un producto de id no incluido en la base de datos")
        void deleteNotContainsTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(999L);
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(999L));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar un producto de id conocido")
        void deleteByIdOkTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(anyLong());
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(anyLong()));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar todos los productos")
        void deleteAllTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteAll();
            boolean result = service.deleteAll();
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteAll());
            verify(repositoryMock,times(2)).deleteAll();
        }
    }

    @DisplayName("Calcular costes de envío en función del producto y de la dirección")
    @Nested
    class CalculateTest {
        @Test
        void calculateShippingCostOk() {
            /*
                    Este test está mal planteado porque realmente sólo utiliza la dirección del fabricante para cobrar
                    gastos de envío o no, en función de si está en España o no.
            */
            Direction direction1 = new Direction("Calle falsa", "33010", "León", "Spain");
            Direction direction2 = new Direction("Calle verdadera", "11322", "Madrid", null);
            Direction direction3 = new Direction("Fresuit Street", "03010", "Lyön", "Belgium");
            Manufacturer adidas = new Manufacturer();
            Manufacturer adididas = new Manufacturer();
            Manufacturer nike = new Manufacturer();
            Manufacturer none = new Manufacturer();
            Product product1 = new Product(
                    "Balón", "Baloncesto", 2, 17.99, adidas);
            Product product2 = new Product(
                    "Mesa", "Ping-pong", 8, 99.50, nike);
            Product product3 = new Product(
                    "Botella", "Aluminio", 5, 9.95, adididas);
            Product product4 = new Product(
                    "Libro", "Deportes naturaleza", 15, 29.95, none);
            Double shippingCost1 = service.calculateShippingCost(product1,direction1);
            Double shippingCost2 = service.calculateShippingCost(product2,direction2);
            Double shippingCost3 = service.calculateShippingCost(product3,direction3);
            Double shippingCost4 = service.calculateShippingCost(product4,null);
            Double shippingCost5 = service.calculateShippingCost(null,direction1);
            Double shippingCost6 = service.calculateShippingCost(null,direction2);
            Double shippingCost7 = service.calculateShippingCost(null,direction3);

            assertAll(
                    () -> assertEquals(2.99,shippingCost1),
                    () -> assertEquals(0.00,shippingCost2),
                    // No tiene sentido no cobrar nada si no conocemos el dato del País. Debería saltar un mensaje para
                    //      que nos obligue a introducir el dato
                    () -> assertEquals(22.99,shippingCost3),
                    // org.opentest4j.AssertionFailedError: expected: <22.99> but was: <22.990000000000002>
                    () -> assertEquals(0.00,shippingCost4),
                    // No tiene sentido no cobrar nada si no conocemos la Dirección. Debería saltar un mensaje para
                    //      que nos obligue a introducir el dato
                    () -> assertEquals(0.00,shippingCost5),
                    () -> assertEquals(0.00,shippingCost6),
                    () -> assertEquals(0.00,shippingCost7)
            );
        }
    }
}





