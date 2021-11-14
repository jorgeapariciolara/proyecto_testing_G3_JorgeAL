package com.example.proyectotesting.service;



import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
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

public class ManufacturerServiceImplMockitoTest {

    ManufacturerRepository repositoryMock;
    ManufacturerServiceImpl service;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(ManufacturerRepository.class);
        service = new ManufacturerServiceImpl(repositoryMock);
    }

    @DisplayName("Contar el número de fabricantes")
    @Test
    void count() {
        when(repositoryMock.count()).thenReturn(2l);
        Long result = service.count();
        assertNotNull(result);
        assertEquals(2, result);
    }

    @DisplayName("Funcionalidad BUSCAR sobre FABRICANTES")
    @Nested
    class RetrieveTest {
        @DisplayName("Buscar todos los fabricantes")
        @Test
        void findAllTest() {
            List<Manufacturer> manufacturers = Arrays.asList(
                new Manufacturer("Adidas","2343235325G",60000,1949),
                new Manufacturer("Nike","2343235325G",60000,1977)
            );
            when(repositoryMock.findAll()).thenReturn(manufacturers);
            List<Manufacturer> result = service.findAll();
            assertNotNull(result);
            assertEquals(2, result.size());
            verify(repositoryMock).findAll();
        }
        @DisplayName("OPTIONAL - Buscar un fabricante con un id conocido")
        @Test
        void findOneOkTest() {
            Manufacturer adidas = new Manufacturer("Adididas","2343235325G",60000,1949);
            Manufacturer nike = new Manufacturer();
            nike.setName("Nique");
            nike.setCif("2658435325Y");
            nike.setNumEmployees(78000);
            nike.setYear(1977);
            when(repositoryMock.findById(1L)).thenReturn(Optional.of(adidas));
            when(repositoryMock.findById(2L)).thenReturn(Optional.of(nike));
            Optional<Manufacturer> result1 = service.findOne(1L);
            Optional<Manufacturer> result2 = service.findOne(2L);
            assertAll(
                    () -> assertTrue(result1.isPresent()),
                    () -> assertEquals("Adididas",result1.get().getName()),
                    () -> assertEquals("2343235325G",result1.get().getCif()),
                    () -> assertEquals(60000,result1.get().getNumEmployees()),
                    () -> assertEquals(1949,result1.get().getYear()),
                    () -> assertTrue(result2.isPresent()),
                    () -> assertEquals("Nique",result2.get().getName()),
                    () -> assertEquals("2658435325Y",result2.get().getCif()),
                    () -> assertEquals(78000,result2.get().getNumEmployees()),
                    () -> assertEquals(1977,result2.get().getYear())
            );
            verify(repositoryMock).findById(1L);
            verify(repositoryMock).findById(2L);
        }
        @DisplayName("OPTIONAL - Buscar un fabricante con cualquier id conocido")
        @Test
        void findAnyTest() {
            Manufacturer adidas = new Manufacturer("Adididas","2343235325G",60000,1949);
            when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(adidas));
            Optional<Manufacturer> result1 = service.findOne(1L);
            assertAll(
                    () -> assertTrue(result1.isPresent()),
                    () -> assertEquals("Adididas",result1.get().getName()),
                    () -> assertEquals("2343235325G",result1.get().getCif()),
                    () -> assertEquals(60000,result1.get().getNumEmployees()),
                    () -> assertEquals(1949,result1.get().getYear())
            );
            verify(repositoryMock).findById(anyLong());
        }
        @DisplayName("OPTIONAL - Buscar un fabricante con un id < 0")
        @Test
        void findOneNegativeTest() {
            when(repositoryMock.findById(anyLong())).thenThrow(IllegalArgumentException.class);
            Optional<Manufacturer> manufacturerOpt = service.findOne(-9L);
            assertAll(
                    () -> assertTrue(manufacturerOpt.isEmpty())
            );
            verifyNoInteractions(repositoryMock);
        }
        @DisplayName("OPTIONAL - Buscar un fabricante con un id nulo")
        @Test
        void findOneNullTest() {
            when(repositoryMock.findById(anyLong())).thenThrow(IllegalArgumentException.class);
            Optional<Manufacturer> manufacturerOpt = service.findOne(null);
            assertAll(
                    () -> assertTrue(manufacturerOpt.isEmpty())
            );
            verifyNoInteractions(repositoryMock);
        }
        @DisplayName("OPTIONAL - Buscar un fabricante con un id que no existe en la base de datos")
        @Test
        void findOneNotContainsTest() {
            Manufacturer adidas = new Manufacturer("Adididas","2343235325G",60000,1949);
            when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(adidas));
            Optional<Manufacturer> result1 = service.findOne(999L);
            assertAll(
                    () -> assertTrue(result1.isPresent()),
                    () -> assertEquals("Adididas",result1.get().getName()),
                    () -> assertEquals("2343235325G",result1.get().getCif()),
                    () -> assertEquals(60000,result1.get().getNumEmployees()),
                    () -> assertEquals(1949,result1.get().getYear())
            );
            verify(repositoryMock).findById(anyLong());
        }

        @DisplayName("Buscar un fabricante por el año de su fundación")
        @Test
        void findByYearTest() {
            List<Manufacturer> manufacturers1 = Arrays.asList(
                    new Manufacturer("Adididas","2343235325G",60000,1949),
                    new Manufacturer("Suap","23432336984P",6000,1949)
            );
            List<Manufacturer> manufacturers2 = Arrays.asList(
                    new Manufacturer("Nikle","2643502735R",75000,1977),
                    new Manufacturer("Pompin","2351478635R",45000,1977)
            );
            List<Manufacturer> manufacturers3 = Arrays.asList(
                    new Manufacturer("D´metrio","2351478951W",25000,1997)
            );
            when(repositoryMock.findByYear(1949)).thenReturn(manufacturers1);
            when(repositoryMock.findByYear(1977)).thenReturn(manufacturers2);
            when(repositoryMock.findByYear(1997)).thenReturn(manufacturers3);
            List<Manufacturer> manufacturersOne = service.findByYear(1949);
            List<Manufacturer> manufacturersTwo = service.findByYear(1977);
            List<Manufacturer> manufacturersThree = service.findByYear(1997);
            assertAll(
                    () -> assertFalse(manufacturersOne.isEmpty()),
                    () -> assertEquals("Adididas",manufacturersOne.get(0).getName()),
                    () -> assertEquals("2343235325G",manufacturersOne.get(0).getCif()),
                    () -> assertEquals(60000,manufacturersOne.get(0).getNumEmployees()),
                    () -> assertEquals("Suap",manufacturersOne.get(1).getName()),
                    () -> assertEquals("23432336984P",manufacturersOne.get(1).getCif()),
                    () -> assertEquals(6000,manufacturersOne.get(1).getNumEmployees()),
                    () -> assertFalse(manufacturersTwo.isEmpty()),
                    () -> assertEquals("Nikle",manufacturersTwo.get(0).getName()),
                    () -> assertEquals("2643502735R",manufacturersTwo.get(0).getCif()),
                    () -> assertEquals(75000,manufacturersTwo.get(0).getNumEmployees()),
                    () -> assertEquals("Pompin",manufacturersTwo.get(1).getName()),
                    () -> assertEquals("2351478635R",manufacturersTwo.get(1).getCif()),
                    () -> assertEquals(45000,manufacturersTwo.get(1).getNumEmployees()),
                    () -> assertFalse(manufacturersThree.isEmpty()),
                    () -> assertEquals("D´metrio",manufacturersThree.get(0).getName()),
                    () -> assertEquals("2351478951W",manufacturersThree.get(0).getCif()),
                    () -> assertEquals(25000,manufacturersThree.get(0).getNumEmployees())
            );
            verify(repositoryMock).findByYear(1949);
            verify(repositoryMock).findByYear(1977);
            verify(repositoryMock).findByYear(1997);
        }

        @DisplayName("Buscar un fabricante por el país")
        @Test
        void findByCountryTest() {
            Direction direction1 = new Direction("Calle Falsa", "33010", "León", "Spain");
            Direction direction2 = new Direction("Calle Verdadera", "11322", "Madrid", "Spain");
            Direction direction3 = new Direction("True Street", null, null, "France");
            Direction direction4 = new Direction("Calle Mercado", "89534", "Rabat", "Morocco");
            Direction direction5 = new Direction("Rue Olalá", "02698", "París", "France");

            Manufacturer adididas = new Manufacturer("Adididas","2343235325G",60000,1949);
            adididas.setDirection(direction1);
            Manufacturer suap = new Manufacturer("Suap","23432336984P",6000,1949);
            suap.setDirection(direction2);
            Manufacturer nikle = new Manufacturer("Nikle","2643502735R",75000,1977);
            nikle.setDirection(direction3);
            Manufacturer pompin = new Manufacturer("Pompin","2351478635R",45000,1977);
            pompin.setDirection(direction4);
            Manufacturer dmetrio = new Manufacturer("D´metrio","2351478951W",25000,1997);
            dmetrio.setDirection(direction5);

            List<Manufacturer> manufacturers1 = new ArrayList<>();
            manufacturers1.add(0,adididas);
            manufacturers1.add(1,suap);
            List<Manufacturer> manufacturers2 = new ArrayList<>();
            manufacturers2.add(0,nikle);
            manufacturers2.add(1,dmetrio);
            List<Manufacturer> manufacturers3 = new ArrayList<>();
            manufacturers3.add(0,pompin);

            when(repositoryMock.findManufacturerByDirectionCountry("Spain")).thenReturn(manufacturers1);
            when(repositoryMock.findManufacturerByDirectionCountry("France")).thenReturn(manufacturers2);
            when(repositoryMock.findManufacturerByDirectionCountry("Morocco")).thenReturn(manufacturers3);

            List<Manufacturer> manufacturersOne = service.findManufacturerByCountry("Spain");
            List<Manufacturer> manufacturersTwo = service.findManufacturerByCountry("France");
            List<Manufacturer> manufacturersThree = service.findManufacturerByCountry("Morocco");

            assertAll(
                    () -> assertEquals(manufacturersOne,manufacturers1),
                    () -> assertEquals("Adididas",manufacturersOne.get(0).getName()),
                    () -> assertEquals("Calle Falsa",manufacturersOne.get(0).getDirection().getStreet()),
                    () -> assertEquals("33010",manufacturersOne.get(0).getDirection().getPostalCode()),
                    () -> assertEquals("León",manufacturersOne.get(0).getDirection().getCity()),
                    () -> assertEquals("Suap",manufacturersOne.get(1).getName()),
                    () -> assertEquals("Calle Verdadera",manufacturersOne.get(1).getDirection().getStreet()),
                    () -> assertEquals("11322",manufacturersOne.get(1).getDirection().getPostalCode()),
                    () -> assertEquals("Madrid",manufacturersOne.get(1).getDirection().getCity()),
                    () -> assertEquals(manufacturersTwo,manufacturers2),
                    () -> assertEquals("Nikle",manufacturersTwo.get(0).getName()),
                    () -> assertEquals("True Street",manufacturersTwo.get(0).getDirection().getStreet()),
                    () -> assertEquals(null,manufacturersTwo.get(0).getDirection().getPostalCode()),
                    () -> assertEquals(null,manufacturersTwo.get(0).getDirection().getCity()),
                    () -> assertEquals("D´metrio",manufacturersTwo.get(1).getName()),
                    () -> assertEquals("Rue Olalá",manufacturersTwo.get(1).getDirection().getStreet()),
                    () -> assertEquals("02698",manufacturersTwo.get(1).getDirection().getPostalCode()),
                    () -> assertEquals("París",manufacturersTwo.get(1).getDirection().getCity()),
                    () -> assertEquals(manufacturersThree,manufacturers3),
                    () -> assertEquals("Pompin",manufacturersThree.get(0).getName()),
                    () -> assertEquals("Calle Mercado",manufacturersThree.get(0).getDirection().getStreet()),
                    () -> assertEquals("89534",manufacturersThree.get(0).getDirection().getPostalCode()),
                    () -> assertEquals("Rabat",manufacturersThree.get(0).getDirection().getCity())
            );
            verify(repositoryMock).findManufacturerByDirectionCountry("Spain");
            verify(repositoryMock).findManufacturerByDirectionCountry("France");
            verify(repositoryMock).findManufacturerByDirectionCountry("Morocco");
        }
    }

    @DisplayName("Funcionalidad CREAR y MODIFICAR sobre fabricantes")
    @Nested
    class SaveTest {
        @DisplayName("Guardar un fabricante")
        @Test
        void saveOkTest() {
            Manufacturer adidas = new Manufacturer("Adidas","2343235325G",60000,1949);
            Manufacturer nike = new Manufacturer();
            nike.setName("Nike");
            nike.setCif("2343235325G");
            nike.setNumEmployees(60000);
            nike.setYear(1977);
            when(repositoryMock.save(adidas)).thenReturn(adidas);
            when(repositoryMock.save(nike)).thenReturn(nike);
            Manufacturer result1 = service.save(adidas);
            Manufacturer result2 = service.save(nike);
            assertAll(
                    () -> assertNotNull(result1),
                    () -> assertEquals("Adidas", result1.getName()),
                    () -> assertEquals("2343235325G", result1.getCif()),
                    () -> assertEquals(60000, result1.getNumEmployees()),
                    () -> assertEquals(1949, result1.getYear()),
                    () -> assertNotNull(result2),
                    () -> assertEquals("Nike", result2.getName()),
                    () -> assertEquals("2343235325G", result2.getCif()),
                    () -> assertEquals(60000, result2.getNumEmployees()),
                    () -> assertEquals(1977, result2.getYear())
            );
            verify(repositoryMock).save(adidas);
            verify(repositoryMock).save(nike);
        }
        @DisplayName("Guardar un fabricante nulo")
        @Test
        void saveNullTest() {
            when(repositoryMock.save(null)).thenThrow(IllegalArgumentException.class);
            Manufacturer result1 = service.save(null);
            assertAll(
                    () -> assertNull(result1)
            );
            verifyNoInteractions(repositoryMock);
        }
    }

    @DisplayName("Funcionalidad BORRAR sobre fabricantes")
    @Nested
    class DeleteTest {
        @Test
        @DisplayName("Borrar un fabricante de id nulo")
        void deleteNullTest(){
            boolean result = service.deleteById(null);
            assertFalse(result);
        }
        @Test
        @DisplayName("Borrar un fabricante de id negativo")
        void deleteNegativeTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(-9L);
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(-9L));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar un fabricante de id no incluido en la base de datos")
        void deleteNotContainsTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(999L);
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(999L));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar un fabricante de id conocido")
        void deleteByIdOkTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(anyLong());
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(anyLong()));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar todos los fabricantes")
        void deleteAllTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteAll();
            boolean result = service.deleteAll();
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteAll());
            verify(repositoryMock,times(2)).deleteAll();
        }
    }
}
