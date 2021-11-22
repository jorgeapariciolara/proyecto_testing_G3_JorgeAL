package com.example.proyectotesting.controller.mvc;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.testing.spring-boot-applications">Spring Boot Docs</a>
 */
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerMockMvcTest {

    @Autowired
    MockMvc mvc;

    @Test
    void obtenerListaTest() throws Exception {
        mvc.perform(get("/products")) // url a testear: http://localhost:8080/products
                .andExpect(status().isOk()) // estado HTTP de la respuesta 200
                .andExpect(model().attributeExists("products")) // comprobar los atributos cargados en el modelo
                .andExpect(view().name("product-list")) // comprobar los atributos cargados en el modelo
                .andExpect( forwardedUrl("/WEB-INF/views/product-list.jsp")); // vista que se mostrará
    }

    @Test
    void crearProductoTest() throws Exception {
        mvc.perform(post("/products").param("name", "producto prueba"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    void viewProductNotFoundTest() throws Exception {
        mvc.perform(get("/products/1/view"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void viewProductOkTest() throws Exception {
        mvc.perform(get("/products/11/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("product-view"));
    }
}
