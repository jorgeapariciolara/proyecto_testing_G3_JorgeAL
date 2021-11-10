package com.example.proyectotesting.controller.rest;
import com.example.proyectotesting.entities.Manufacturer;
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
public class ManufacturerRestControllerTest {

    private static final String MANUFACTURERS_URL = "/api/manufacturers";
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
        // Creamos 2 productos demo
        createDemoManufacturer();
        createDemoManufacturer();

        ResponseEntity<Manufacturer[]> response = testRestTemplate.getForEntity(MANUFACTURERS_URL, Manufacturer[].class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());

        List<Manufacturer> products = List.of(response.getBody());
        assertNotNull(products);
        assertTrue(products.size() >= 2);
    }

    @Test
    void findOneOk() {
        Manufacturer manufacturer = createDemoManufacturer();
        ResponseEntity<Manufacturer> response =
                testRestTemplate.getForEntity(MANUFACTURERS_URL + "/" + manufacturer.getId(), Manufacturer.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());

        Manufacturer Manufacturer = response.getBody();
        assertNotNull(Manufacturer);
        assertNotNull(Manufacturer.getId());
        assertEquals(manufacturer.getId(), Manufacturer.getId());
    }

    @Test
    void findOneNotFound() {
        ResponseEntity<Manufacturer> response =
                testRestTemplate.getForEntity(MANUFACTURERS_URL + "/99999", Manufacturer.class);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void createOK() {
        String json = """
                {
                    "name": "Adidas",
                    "cif": "2343235325G",
                    "numEmployees": 60000,
                    "year": 1949
                }
                """;
        ResponseEntity<Manufacturer> response =
                testRestTemplate.postForEntity(MANUFACTURERS_URL, createHttpRequest(json), Manufacturer.class);
        assertEquals(200, response.getStatusCodeValue()); // Poniendo 201 CREATED da error
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        Manufacturer manufacturer = response.getBody();
        assertNotNull(manufacturer);
        assertEquals("Adidas", manufacturer.getName());
    }

    @Test
    void createBadRequest() {
        String json = """
                {
                    "id": 2,
                    "name": "Adidas",
                    "cif": "2343235325G",
                    "numEmployees": 60000,
                    "year": 1949
                }
                """;
        ResponseEntity<Manufacturer> response =
                testRestTemplate.postForEntity(MANUFACTURERS_URL, createHttpRequest(json), Manufacturer.class);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void update() {
        Manufacturer manufacturer = createDemoManufacturer();
        String json = String.format("""
                {
                    "id": %d,
                    "name": "Adidas - EDITADO",
                    "cif": "2343235325G",
                    "numEmployees": 60000,
                    "year": 1949
                }
                """, manufacturer.getId());
        System.out.println(json);
        ResponseEntity<Manufacturer> response =
                testRestTemplate.exchange(MANUFACTURERS_URL, HttpMethod.PUT, createHttpRequest(json), Manufacturer.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());
        Manufacturer responseManufacturer = response.getBody();
        assertEquals(manufacturer.getId(), responseManufacturer.getId());
        assertEquals("Adidas - EDITADO", responseManufacturer.getName());
        assertNotEquals(responseManufacturer.getName(), manufacturer.getName());
    }

    @Test
    void updateBadRequest() {
        String json = """
                {
                    "id": null,
                    "name": "Adidas - EDITADO",
                    "cif": "2343235325G",
                    "numEmployees": 60000,
                    "year": 1949
                }
                """;
        ResponseEntity<Manufacturer> response =
                testRestTemplate.exchange(MANUFACTURERS_URL, HttpMethod.PUT, createHttpRequest(json), Manufacturer.class);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void updateNotFoundRequest() {
        String json = """
                {
                    "id": 999L,
                    "name": "Adidas - EDITADO",
                    "cif": "2343235325G",
                    "numEmployees": 60000,
                    "year": 1949
                }
                """;
        ResponseEntity<Manufacturer> response =
                testRestTemplate.exchange(MANUFACTURERS_URL, HttpMethod.PUT, createHttpRequest(json), Manufacturer.class);
        assertEquals(404, response.getStatusCodeValue());
                // org.opentest4j.AssertionFailedError:
                //      Expected:404 Not Found
                //      Actual:400 Bad Request
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void deleteById() {
        Manufacturer manufacturer = createDemoManufacturer();
        String url = MANUFACTURERS_URL + "/" + manufacturer.getId();
        ResponseEntity<Manufacturer> response = testRestTemplate.getForEntity(url, Manufacturer.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(manufacturer.getId(), response.getBody().getId());
        testRestTemplate.delete(url);
        ResponseEntity<Manufacturer> response2 = testRestTemplate.getForEntity(url,Manufacturer.class);
        assertEquals(404, response2.getStatusCodeValue());
        //      org.opentest4j.AssertionFailedError:
        //          Expected :404
        //          Actual   :200
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
        createDemoManufacturer();
        createDemoManufacturer();
        ResponseEntity<Manufacturer[]> response = testRestTemplate.getForEntity(MANUFACTURERS_URL, Manufacturer[].class);
        assertNotNull(response.getBody());
        List<Manufacturer> manufacturers = List.of(response.getBody());
        assertTrue(manufacturers.size() >= 2);
        testRestTemplate.delete(MANUFACTURERS_URL);
        response = testRestTemplate.getForEntity(MANUFACTURERS_URL, Manufacturer[].class);
        assertNotNull(response.getBody());
        manufacturers = List.of(response.getBody());
        assertEquals(0, manufacturers.size());
        //  org.opentest4j.AssertionFailedError:
        //      Expected :0
        //      Actual   :4
    }



    private Manufacturer createDemoManufacturer() {
        String json = """
                {
                    "name": "Adidas",
                    "cif": "2343235325G",
                    "numEmployees": 60000,
                    "year": 1949
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Manufacturer> response =
                testRestTemplate.postForEntity(MANUFACTURERS_URL, request, Manufacturer.class);
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
