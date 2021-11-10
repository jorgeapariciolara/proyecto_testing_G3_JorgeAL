package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CategoryServiceImplMockitoTest {

    CategoryRepository repositoryMock;

    CategoryServiceImpl service;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(CategoryRepository.class);
        service = new CategoryServiceImpl(repositoryMock);
    }

    @DisplayName("Contar el número de categorías")
    @Test
    void count() {
        when(repositoryMock.count()).thenReturn(4l);
        Long result = service.count();
        assertNotNull(result);
        assertEquals(4, result);
    }

    @DisplayName("Comprobar que existen los id de los categorías")
    @Test
    void existById() {
        when(repositoryMock.existsById(anyLong())).thenReturn(true);
        Boolean result = service.existsById(anyLong());
        assertNotNull(result);
        verify(repositoryMock).existsById(anyLong());
    }

    @DisplayName("Funcionalidad BUSCAR sobre CATEGORÍAS")
    @Nested
    class RetrieveTest {
        @DisplayName("Buscar todas las categorías")
        @Test
        void findAllTest() {
            Category category1 = new Category("Libros", "black");
            Category category2 = new Category("Computación", "blue");
            Category category3 = new Category("Hogar", "white");
            Category category4 = new Category("Moda", "brown");
            List<Category> categories = Arrays.asList(category1, category2, category3, category4);
            when(repositoryMock.findAll()).thenReturn(categories);
            List<Category> result = service.findAll();
            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals(4,result.size())
            );
            verify(repositoryMock).findAll();
        }
        @DisplayName("OPTIONAL - Buscar una categoría con un id conocido")
        @Test
        void findOneOkTest() {
            Category category1 = new Category("Libros", "black");
            Category category2 = new Category("Computación", "blue");
            Category category3 = new Category("Hogar", "white");
            Category category4 = new Category("Moda", "brown");
            when(repositoryMock.findById(1L)).thenReturn(Optional.of(category1));
            when(repositoryMock.findById(2L)).thenReturn(Optional.of(category2));
            when(repositoryMock.findById(3L)).thenReturn(Optional.of(category3));
            when(repositoryMock.findById(4L)).thenReturn(Optional.of(category4));
            Optional<Category> categoryOne = service.findOne(1L);
            Optional<Category> categoryTwo = service.findOne(2L);
            Optional<Category> categoryThree = service.findOne(3L);
            Optional<Category> categoryFour = service.findOne(4L);
            assertAll(
                    () -> assertNotNull(categoryOne),
                    () -> assertEquals("Libros",categoryOne.get().getName()),
                    () -> assertEquals("black",categoryOne.get().getColor()),
                    () -> assertNotNull(categoryTwo),
                    () -> assertEquals("Computación",categoryTwo.get().getName()),
                    () -> assertEquals("blue",categoryTwo.get().getColor()),
                    () -> assertNotNull(categoryThree),
                    () -> assertEquals("Hogar",categoryThree.get().getName()),
                    () -> assertEquals("white",categoryThree.get().getColor()),
                    () -> assertNotNull(categoryFour),
                    () -> assertEquals("Moda",categoryFour.get().getName()),
                    () -> assertEquals("brown",categoryFour.get().getColor())
            );
            verify(repositoryMock).findById(1L);
            verify(repositoryMock).findById(2L);
            verify(repositoryMock).findById(3L);
            verify(repositoryMock).findById(4L);
        }
        @DisplayName("OPTIONAL - Buscar una categoría con cualquier id")
        @Test
        void findAnyTest() {
            Category category1 = new Category("Libros", "black");
            when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(category1));
            Optional<Category> categoryOne = service.findOne(1L);
            assertAll(
                    () -> assertNotNull(categoryOne),
                    () -> assertEquals("Libros",categoryOne.get().getName()),
                    () -> assertEquals("black",categoryOne.get().getColor())
            );
            verify(repositoryMock).findById(anyLong());
        }
        @DisplayName("OPTIONAL - Buscar una categoría con un id negativo")
        @Test
        void findNegativeTest() {
            when(repositoryMock.findById(anyLong())).thenThrow(IllegalArgumentException.class);
            Optional<Category> categoryOpt = service.findOne(-9L);
            assertAll(
                    () -> assertTrue(categoryOpt.isEmpty())
            );
            verifyNoInteractions(repositoryMock);
        }
        /*
        @DisplayName("OPTIONAL - Buscar una categoría con un id nulo")
        @Test
        void findNullTest() {
            when(repositoryMock.findById(anyLong())).thenThrow(IllegalArgumentException.class);
            Optional<Category> categoryOpt = service.findOne(null);
            assertAll(
                    () -> assertTrue(categoryOpt.isEmpty())
            );
            verifyNoInteractions(repositoryMock);
        }
        */
        @DisplayName("OPTIONAL - Buscar una categoría con un id que no existe en la base de datos")
        @Test
        void findNotContainsTest() {
            Category category1 = new Category("Libros", "black");
            when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(category1));
            Optional<Category> categoryOne = service.findOne(999L);
            assertAll(
                    () -> assertNotNull(categoryOne),
                    () -> assertEquals("Libros",categoryOne.get().getName()),
                    () -> assertEquals("black",categoryOne.get().getColor())
            );
            verify(repositoryMock).findById(anyLong());
        }

        @DisplayName("Buscar una categoría utilizando el color")
        @Test
        void findByColorTest() {
            Category category1 = new Category("Libros", "black");
            Category category2 = new Category("Computación", "blue");
            Category category3 = new Category("Hogar", "white");
            Category category4 = new Category("Moda", "brown");
            when(repositoryMock.findByColor("black")).thenReturn(Optional.of(category1));
            when(repositoryMock.findByColor("blue")).thenReturn(Optional.of(category2));
            when(repositoryMock.findByColor("white")).thenReturn(Optional.of(category3));
            when(repositoryMock.findByColor("brown")).thenReturn(Optional.of(category4));
            Optional<Category> categoryOne = service.findOne("black");
            Optional<Category> categoryTwo = service.findOne("blue");
            Optional<Category> categoryThree = service.findOne("white");
            Optional<Category> categoryFour = service.findOne("brown");
            assertAll(
                    () -> assertNotNull(categoryOne),
                    // () -> assertEquals(1L, categoryOne.getId()),
                    () -> assertEquals("Libros", categoryOne.get().getName()),
                    () -> assertEquals("black", categoryOne.get().getColor()),
                    () -> assertNotNull(categoryTwo),
                    // () -> assertEquals(2L, categoryTwo.getId()),
                    () -> assertEquals("Computación", categoryTwo.get().getName()),
                    () -> assertEquals("blue", categoryTwo.get().getColor()),
                    () -> assertNotNull(categoryThree),
                    // () -> assertEquals(3L, categoryThree.getId()),
                    () -> assertEquals("Hogar", categoryThree.get().getName()),
                    () -> assertEquals("white", categoryThree.get().getColor()),
                    () -> assertNotNull(categoryFour),
                    // () -> assertEquals(4L, categoryFour.getId()),
                    () -> assertEquals("Moda", categoryFour.get().getName()),
                    () -> assertEquals("brown", categoryFour.get().getColor())
                    // ¿POR QUÉ NO SE ASIGNAN ID A LAS CATEGORÍAS?
            );
            verify(repositoryMock).findByColor("black");
            verify(repositoryMock).findByColor("blue");
            verify(repositoryMock).findByColor("white");
            verify(repositoryMock).findByColor("brown");
        }
    }

    @DisplayName("Funcionalidad CREAR y MODIFICAR sobre CATEGORÍAS")
    @Nested
    class SaveTest {
        @DisplayName("Guardar una categoría nula")
        @Test
        void saveNullTests() {
            Category category1 = null;
            when(repositoryMock.save(null)).thenThrow(IllegalArgumentException.class);
            Category result1 = service.save(category1);
            assertAll(
                    () -> assertNull(result1)
            );
            verifyNoInteractions(repositoryMock);
        }
        @DisplayName("Guardar una categoría")
        @Test
        void saveOkTest() {
            Category category1 = new Category("Libros", "black");
            Category category2 = new Category("Computación", "blue");
            Category category3 = new Category("Hogar", "white");
            Category category4 = new Category("Moda", "brown");
            when(repositoryMock.save(category1)).thenReturn(category1);
            when(repositoryMock.save(category2)).thenReturn(category2);
            when(repositoryMock.save(category3)).thenReturn(category3);
            when(repositoryMock.save(category4)).thenReturn(category4);
            Category categoryOne = service.save(category1);
            Category categoryTwo = service.save(category2);
            Category categoryThree = service.save(category3);
            Category categoryFour = service.save(category4);
            assertAll(
                    () -> assertNotNull(categoryOne),
                    // () -> assertEquals(1L, categoryOne.getId()),
                    () -> assertEquals("Libros", categoryOne.getName()),
                    () -> assertEquals("black", categoryOne.getColor()),
                    () -> assertNotNull(categoryTwo),
                    // () -> assertEquals(2L, categoryTwo.getId()),
                    () -> assertEquals("Computación", categoryTwo.getName()),
                    () -> assertEquals("blue", categoryTwo.getColor()),
                    () -> assertNotNull(categoryThree),
                    // () -> assertEquals(3L, categoryThree.getId()),
                    () -> assertEquals("Hogar", categoryThree.getName()),
                    () -> assertEquals("white", categoryThree.getColor()),
                    () -> assertNotNull(categoryFour),
                    // () -> assertEquals(4L, categoryFour.getId()),
                    () -> assertEquals("Moda", categoryFour.getName()),
                    () -> assertEquals("brown", categoryFour.getColor())
                    // ¿POR QUÉ NO SE ASIGNAN ID A LAS CATEGORÍAS?
            );
            verify(repositoryMock).save(category1);
            verify(repositoryMock).save(category2);
            verify(repositoryMock).save(category3);
            verify(repositoryMock).save(category4);
        }
    }

    @DisplayName("Funcionalidad BORRAR sobre categorías")
    @Nested
    class DeleteTest {
        @Test
        @DisplayName("Borrar una categoría de id nulo")
        void deleteNullTest(){
            boolean result = service.deleteById(null);
            assertFalse(result);
        }
        @Test
        @DisplayName("Borrar una categoría de id negativo")
        void deleteNegativeTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(-9L);
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(-9L));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar una categoría de id no incluido en la base de datos")
        void deleteNotContainsTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(999L);
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(999L));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar una categoría de id conocido")
        void deleteByIdOkTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteById(anyLong());
            boolean result = service.deleteById(anyLong());
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteById(anyLong()));
            verify(repositoryMock).deleteById(anyLong());
        }
        @Test
        @DisplayName("Borrar todos las categorías")
        void deleteAllTest(){
            doThrow(RuntimeException.class).when(repositoryMock).deleteAll();
            boolean result = service.deleteAll();
            assertFalse(result);
            assertThrows(Exception.class, () -> repositoryMock.deleteAll());
            verify(repositoryMock,times(2)).deleteAll();
        }
    }

}
