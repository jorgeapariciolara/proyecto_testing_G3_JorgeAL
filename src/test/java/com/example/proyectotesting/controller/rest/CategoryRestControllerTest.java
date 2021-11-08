package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.entities.Product;
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
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.hasBody());
        Category product = response.getBody();
        assertNotNull(product);
        assertEquals("Category creado desde JUnit", product.getName());
    }

    @Test
    void createBadRequest() {
        String json = """
                {
                    "id": 5,
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
                    "name": "Libros", 
                    "color": "black"
                }
                """, product.getId());
        System.out.println(json);
        ResponseEntity<Category> response =
                testRestTemplate.exchange(CATEGORIES_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());
        Category responseCategory = response.getBody();
        assertEquals(response.getBody().getId(), responseCategory.getId());
        assertEquals("Categoryo modificado desde JUnit", responseCategory.getName());
        assertNotEquals(responseCategory.getName(), product.getName());
    }

    @Test
    void updateBadRequest() {
        String json = """
                {
                    "id": null,
                    "name": "Libros", 
                    "color": "black"
                }
                """;
        ResponseEntity<Category> response =
                testRestTemplate.exchange(CATEGORIES_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void updateNotFoundRequest() {
        String json = """
                {
                    "id": 999L,
                    "name": "Libros", 
                    "color": "black"
                }
                """;
        ResponseEntity<Category> response =
                testRestTemplate.exchange(CATEGORIES_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
        // org.opentest4j.AssertionFailedError: Expected:404 Not Found   Actual:400 Bad Request
    }

    @Test
    void deleteById() {
        Category product = createDemoCategory();
        String url = CATEGORIES_URL + "/" + product.getId();
        ResponseEntity<Category> response = testRestTemplate.getForEntity(url, Category.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product.getId(), response.getBody().getId());
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
        createDemoCategory();
        createDemoCategory();
        ResponseEntity<Category[]> response = testRestTemplate.getForEntity(CATEGORIES_URL, Category[].class);
        assertNotNull(response.getBody());
        List<Category> categories = List.of(response.getBody());
        assertTrue(categories.size() >= 2);
        testRestTemplate.delete(CATEGORIES_URL); // borra todos
        response = testRestTemplate.getForEntity(CATEGORIES_URL, Category[].class);
        assertNotNull(response.getBody());
        categories = List.of(response.getBody());
        assertEquals(0, categories.size());
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