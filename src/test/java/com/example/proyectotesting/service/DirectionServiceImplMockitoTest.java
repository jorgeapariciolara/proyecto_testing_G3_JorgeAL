package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.repository.DirectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testeo de los métodos presentes en la clase DirectionServiceImpl del package Service
 */

public class DirectionServiceImplMockitoTest {

    DirectionRepository repositoryMock;
    DirectionServiceImpl service;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(DirectionRepository.class);
        service = new DirectionServiceImpl(repositoryMock);
    }

    @DisplayName("Contar el número de direcciones")
    @Test
    void count() {
        when(repositoryMock.count()).thenReturn(2l);
        Long result = service.count();
        assertNotNull(result);
        assertEquals(2, result);
    }

    @DisplayName("Comprobar que existen los id de las direcciones")
    @Test
    void existById() {
        when(repositoryMock.existsById(anyLong())).thenReturn(true);
        Boolean result = service.existsById(anyLong());
        assertNotNull(result);
        verify(repositoryMock).existsById(anyLong());
    }

    @DisplayName("Funcionalidad BUSCAR sobre DIRECCIONES")
    @Nested
    class RetrieveTest {
        @DisplayName("Buscar todas las direcciones")
        @Test
        void findAllTest() {
            List<Direction> directions = Arrays.asList(
                    new Direction("Calle Falsa", "33010", "León", "Spain"),
                    new Direction("Calle Verdadera", "11322", "Madrid", "Spain"),
                    new Direction("True Street", null, null, "France"),
                    new Direction("Calle Mercado", "89534", "Rabat", "Morocco"),
                    new Direction("Rue Olalá", "02698", "París", "France")
            );
            when(repositoryMock.findAll()).thenReturn(directions);
            List<Direction> result = service.findAll();
            assertNotNull(result);
            assertEquals(5, result.size());
            verify(repositoryMock).findAll();
        }
        @DisplayName("OPTIONAL - Buscar una dirección con un id conocido")
        @Test
        void findOneOkTest() {
            Direction direction1 = new Direction("Calle Falsa", "33010", "León", "Spain");
            Direction direction2 = new Direction();
            direction2.setStreet("Calle Verdadera");
            direction2.setPostalCode("11322");
            direction2.setCity("Madrid");
            direction2.setCountry("Spain");
            Direction direction3 = new Direction("True Street", null, null, "France");
            Direction direction4 = new Direction("Calle Mercado", "89534", "Rabat", "Morocco");
            Direction direction5 = new Direction("Rue Olalá", "02698", "París", "France");
            when(repositoryMock.findById(1L)).thenReturn((Optional.of(direction1)));
            when(repositoryMock.findById(2L)).thenReturn((Optional.of(direction2)));
            when(repositoryMock.findById(3L)).thenReturn((Optional.of(direction3)));
            when(repositoryMock.findById(4L)).thenReturn((Optional.of(direction4)));
            when(repositoryMock.findById(5L)).thenReturn((Optional.of(direction5)));
            Optional<Direction> directionOne = service.findOne(1L);
            Optional<Direction> directionTwo = service.findOne(2L);
            Optional<Direction> directionThree = service.findOne(3L);
            Optional<Direction> directionFour = service.findOne(4L);
            Optional<Direction> directionFive = service.findOne(5L);
            assertAll(
                    () -> assertNotNull(directionOne),
                    () -> assertEquals("Calle Falsa", directionOne.get().getStreet()),
                    () -> assertEquals("33010", directionOne.get().getPostalCode()),
                    () -> assertEquals("León", directionOne.get().getCity()),
                    () -> assertEquals("Spain", directionOne.get().getCountry()),
                    () -> assertNotNull(directionTwo),
                    () -> assertEquals("Calle Verdadera", directionTwo.get().getStreet()),
                    () -> assertEquals("11322", directionTwo.get().getPostalCode()),
                    () -> assertEquals("Madrid", directionTwo.get().getCity()),
                    () -> assertEquals("Spain", directionTwo.get().getCountry()),
                    () -> assertNotNull(directionThree),
                    () -> assertEquals("True Street", directionThree.get().getStreet()),
                    () -> assertEquals(null, directionThree.get().getPostalCode()),
                    () -> assertEquals(null, directionThree.get().getCity()),
                    () -> assertEquals("France", directionThree.get().getCountry()),
                    () -> assertNotNull(directionFour),
                    () -> assertEquals("Calle Mercado", directionFour.get().getStreet()),
                    () -> assertEquals("89534", directionFour.get().getPostalCode()),
                    () -> assertEquals("Rabat", directionFour.get().getCity()),
                    () -> assertEquals("Morocco", directionFour.get().getCountry()),
                    () -> assertNotNull(directionFive),
                    () -> assertEquals("Rue Olalá", directionFive.get().getStreet()),
                    () -> assertEquals("02698", directionFive.get().getPostalCode()),
                    () -> assertEquals("París", directionFive.get().getCity()),
                    () -> assertEquals("France", directionFive.get().getCountry())
            );
            verify(repositoryMock).findById(1L);
            verify(repositoryMock).findById(2L);
            verify(repositoryMock).findById(3L);
            verify(repositoryMock).findById(4L);
            verify(repositoryMock).findById(5L);
        }
        @DisplayName("OPTIONAL - Buscar una dirección con cualquier id conocido")
        @Test
        void findAnyTest() {
            Direction direction1 = new Direction("Calle Falsa", "33010", "León", "Spain");
            when(repositoryMock.findById(anyLong())).thenReturn((Optional.of(direction1)));
            Optional<Direction> directionOne = service.findOne(1L);
            assertAll(
                    () -> assertNotNull(directionOne),
                    () -> assertEquals("Calle Falsa", directionOne.get().getStreet()),
                    () -> assertEquals("33010", directionOne.get().getPostalCode()),
                    () -> assertEquals("León", directionOne.get().getCity()),
                    () -> assertEquals("Spain", directionOne.get().getCountry())
            );
            verify(repositoryMock).findById(anyLong());
        }
        @DisplayName("Buscar una dirección por ciudad y país")
        @Test
        void findByCityAndCountry() {
            Direction direction1 = new Direction("Calle Falsa", "33010", "León", "Spain");
            Direction direction2 = new Direction("Calle Verdadera", "11322", "Madrid", "Spain");
            Direction direction3 = new Direction("True Street", null, "Madrid", "Spain");
            Direction direction4 = new Direction("Calle Mercado", "89534", "León", "Spain");
            Direction direction5 = new Direction("Rue Olalá", "02698", "Madrid", "México");
            List<Direction> directions1 = new ArrayList<>();
            directions1.add(direction1);
            directions1.add(direction4);
            List<Direction> directions2 = new ArrayList<>();
            directions2.add(direction2);
            directions2.add(direction3);
            List<Direction> directions3 = new ArrayList<>();
            directions3.add(direction5);
            when(repositoryMock.findByCityAndCountry("León","Spain")).thenReturn(directions1);
            when(repositoryMock.findByCityAndCountry("Madrid","Spain")).thenReturn(directions2);
            when(repositoryMock.findByCityAndCountry("Madrid","México")).thenReturn(directions3);

            List<Direction> directionsOne = service.findByCityAndCountry("León","Spain");
            List<Direction> directionsTwo = service.findByCityAndCountry("Madrid","Spain");
            List<Direction> directionsThree = service.findByCityAndCountry("Madrid","México");

            assertAll(
                    () -> assertNotNull(directionsOne),
                    () -> assertEquals("Calle Falsa", directionsOne.get(0).getStreet()),
                    () -> assertEquals("33010", directionsOne.get(0).getPostalCode()),
                    () -> assertEquals("Calle Mercado", directionsOne.get(1).getStreet()),
                    () -> assertEquals("89534", directionsOne.get(1).getPostalCode()),
                    () -> assertNotNull(directionsTwo),
                    () -> assertEquals("Calle Verdadera", directionsTwo.get(0).getStreet()),
                    () -> assertEquals("11322", directionsTwo.get(0).getPostalCode()),
                    () -> assertEquals("True Street", directionsTwo.get(1).getStreet()),
                    () -> assertEquals(null, directionsTwo.get(1).getPostalCode()),
                    () -> assertNotNull(directionsThree),
                    () -> assertEquals("Rue Olalá", directionsThree.get(0).getStreet()),
                    () -> assertEquals("02698", directionsThree.get(0).getPostalCode())
            );
            verify(repositoryMock).findByCityAndCountry("León","Spain");
            verify(repositoryMock).findByCityAndCountry("Madrid","Spain");
            verify(repositoryMock).findByCityAndCountry("Madrid","México");
        }
    }

    @DisplayName("Funcionalidad CREAR y MODIFICAR sobre direcciones")
    @Nested
    class SaveTest {
        @DisplayName("Guardar una dirección")
        @Test
        void saveOkTest() {
            Direction direction1 = new Direction("Calle falsa", "33010", "León", "Spain");
            Direction direction2 = new Direction();
            direction2.setStreet("Calle verdadera");
            direction2.setPostalCode("11322");
            direction2.setCity("Madrid");
            direction2.setCountry("Spain");
            when(repositoryMock.save(direction1)).thenReturn(direction1);
            when(repositoryMock.save(direction2)).thenReturn(direction2);
            Direction directionOne = service.save(direction1);
            Direction directionTwo = service.save(direction2);
            assertAll(
                    () -> assertNotNull(directionOne),
                    () -> assertEquals("Calle falsa", directionOne.getStreet()),
                    () -> assertEquals("33010", directionOne.getPostalCode()),
                    () -> assertEquals("León", directionOne.getCity()),
                    () -> assertEquals("Spain", directionOne.getCountry()),
                    () -> assertNotNull(directionTwo),
                    () -> assertEquals("Calle verdadera", directionTwo.getStreet()),
                    () -> assertEquals("11322", directionTwo.getPostalCode()),
                    () -> assertEquals("Madrid", directionTwo.getCity()),
                    () -> assertEquals("Spain", directionTwo.getCountry())
            );
            verify(repositoryMock).save(direction1);
            verify(repositoryMock).save(direction2);
        }
        @DisplayName("Guardar una dirección nula")
        @Test
        void saveNullTest() {
            when(repositoryMock.save(null)).thenThrow(IllegalArgumentException.class);
            Direction result1 = service.save(null);
            assertAll(
                    () -> assertNull(result1)
            );
            verifyNoInteractions(repositoryMock);
        }
    }

    @DisplayName("Funcionalidad BORRAR sobre direcciones")
    @Nested
    class DeleteTest {
        @Test
        @DisplayName("Borrar una dirección de id nulo")
        void deleteNullTest(){
            boolean result = service.deleteById(null);
            assertFalse(result);
        }
        @Test
        @DisplayName("Borrar una dirección de id negativo")
        void deleteNegativeTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(-9L);
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(-9L));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar una dirección de id no incluido en la base de datos")
        void deleteNotContainsTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(999L);
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(999L));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar una dirección de id conocido")
        void deleteByIdOkTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(anyLong());
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(anyLong()));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar todas las direcciones")
        void deleteAllTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteAll();
            boolean result = service.deleteAll();
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteAll());
            verify(repositoryMock,times(2)).deleteAll();
        }
    }
}
