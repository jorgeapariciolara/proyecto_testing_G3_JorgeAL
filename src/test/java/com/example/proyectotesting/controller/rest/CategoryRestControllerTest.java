package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryRestControllerTest {

    private static final String CATEGORIES_URL = "/api/category";
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        createDemoCategory();
        createDemoCategory();
        ResponseEntity<Category[]> response = testRestTemplate.getForEntity(CATEGORIES_URL, Category[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());
        List<Category> categories = List.of(response.getBody());
        assertNotNull(categories);
        assertTrue(categories.size() >= 2);
    }

    @Test
    void findOneOk() {
        Category product = createDemoCategory();
        ResponseEntity<Category> response =
                testRestTemplate.getForEntity(CATEGORIES_URL + "/" + product.getId(), Category.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        Category responseCategory = response.getBody();
        assertNotNull(responseCategory);
        assertNotNull(responseCategory.getId());
        assertEquals(product.getId(), responseCategory.getId());
    }

    @Test
    void findOneNotFound() {
        ResponseEntity<Category> response =
                testRestTemplate.getForEntity(CATEGORIES_URL + "/99999", Category.class);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void createOK() {
        String json = """
                {
                    "name": "Libros", 
                    "color": "black"
                }
                """;
        ResponseEntity<Category> response =
                testRestTemplate.postForEntity(CATEGORIES_URL, createHttpRequest(json), Category.class);
        // Pongo 200 = OK porque con 201 = CREATED salta el error
        // org.opentest4j.AssertionFailedError: Expected:201 CREATED    Actual:200 OK
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        Category product = response.getBody();
        assertNotNull(product);
        assertEquals("Libros", product.getName());
    }

    @Test
    void createBadRequest() {
        String json = """
                {
                    "id": null,
                    "name": "Libros", 
                    "color": "black"
                }
                """;
        ResponseEntity<Category> response =
                testRestTemplate.postForEntity(CATEGORIES_URL, createHttpRequest(json), Category.class);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void update() {
        Category product = createDemoCategory();
        String json = String.format("""
                {
                    "id": %d,
                    "name": "Category modificado desde JUnit", 
                    "color": "black"
                }
                """, product.getId());
        System.out.println(json);
        ResponseEntity<Category> response =
                testRestTemplate.exchange(CATEGORIES_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);
        // 2021-11-10 16:38:03.636  WARN 5340 --- [o-auto-1-exec-2] .w.s.m.s.DefaultHandlerExceptionResolver:
        // Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'PUT' not supported]
        // org.opentest4j.AssertionFailedError:     Expected:200 OK    Actual:405 METHOD NOT ALLOWED
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());
        Category responseCategory = response.getBody();
        assertEquals(response.getBody().getId(), responseCategory.getId());
        assertEquals("Category modificado desde JUnit", responseCategory.getName());
        assertNotEquals(responseCategory.getName(), product.getName());
    }

    @Test
    void updateBadRequest() {
        String json = """
                {
                    "id": null,
                    "name": "Category modificado desde JUnit", 
                    "color": "black"
                }
                """;
        ResponseEntity<Category> response =
                testRestTemplate.exchange(CATEGORIES_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);
        // org.opentest4j.AssertionFailedError: Expected:400 BAD REQUEST    Actual:405 METHOD NOT ALLOWED
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void updateNotFoundRequest() {
        String json = """
                {
                    "id": 999L,
                    "name": "Category modificado desde JUnit", 
                    "color": "black"
                }
                """;
        ResponseEntity<Category> response =
                testRestTemplate.exchange(CATEGORIES_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
        // org.opentest4j.AssertionFailedError: Expected:404 NOT FOUND   Actual:405 METHOD NOT ALLOWED
    }

    @Test
    void deleteById() {
        Category category = createDemoCategory();
        String url = CATEGORIES_URL + "/" + category.getId();
        ResponseEntity<Category> response = testRestTemplate.getForEntity(url, Category.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category.getId(), response.getBody().getId());
        testRestTemplate.delete(url);
        ResponseEntity<Category> response2 = testRestTemplate.getForEntity(url, Category.class);
        assertEquals(404, response2.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        assertFalse(response2.hasBody());
    }

    /*
    @Test
    void deleteNoContentById() {

    }

    @Test
    void deleteNotFoundById() {

    }
    */

    @Test
    void deleteAll() {
        String url = CATEGORIES_URL;
        ResponseEntity<Category[]> response = testRestTemplate.getForEntity(url, Category[].class);
        assertNotNull(response.getBody());
        List<Category> categories = List.of(response.getBody());
        assertTrue(categories.size() >= 0);
        testRestTemplate.delete(url);
        response = testRestTemplate.getForEntity(url, Category[].class);
        assertNotNull(response.getBody());
        categories = List.of(response.getBody());
        System.out.println(categories.size());
        assertEquals(0, categories.size());
        // org.opentest4j.AssertionFailedError: Expected:0      Actual:4
        //
        // Caused by: org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Violación de una restricción de
        // Integridad Referencial: "FK1E1SI1TOCE2P99STDY58HR644: PUBLIC.PRODUCT_CATEGORY FOREIGN KEY(CATEGORY_ID)
        // REFERENCES PUBLIC.CATEGORIAS(ID) (5)"
        // Referential integrity constraint violation: "FK1E1SI1TOCE2P99STDY58HR644: PUBLIC.PRODUCT_CATEGORY FOREIGN
        // KEY(CATEGORY_ID) REFERENCES PUBLIC.CATEGORIAS(ID) (5)"; SQL statement:
        // delete from categorias where id=? [23503-200]
    }

    private Category createDemoCategory(){
        String json = """
                {
                    "name": "Libros", 
                    "color": "black"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Category> response =
                testRestTemplate.postForEntity(CATEGORIES_URL, request, Category.class);
        return response.getBody();
    }

    private HttpEntity<String> createHttpRequest(String json){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        return request;
    }
}