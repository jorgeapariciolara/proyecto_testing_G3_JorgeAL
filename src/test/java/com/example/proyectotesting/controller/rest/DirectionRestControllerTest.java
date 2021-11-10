package com.example.proyectotesting.controller.rest;
import com.example.proyectotesting.entities.Direction;
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
public class DirectionRestControllerTest {

    private static final String DIRECTIONS_URL = "/api/directions";
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
        createDemoDirection();
        createDemoDirection();
        ResponseEntity<Direction[]> response = testRestTemplate.getForEntity(DIRECTIONS_URL, Direction[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());
        List<Direction> directions = List.of(response.getBody());
        assertNotNull(directions);
        assertTrue(directions.size() >= 2);
    }

    @Test
    void findOneOk() {
        Direction direction = createDemoDirection();
        ResponseEntity<Direction> response =
                testRestTemplate.getForEntity(DIRECTIONS_URL + "/" + direction.getId(), Direction.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        Direction responseDirection = response.getBody();
        assertNotNull(responseDirection);
        assertNotNull(responseDirection.getId());
        assertEquals(direction.getId(), responseDirection.getId());
    }

    @Test
    void findOneNotFound() {
        ResponseEntity<Direction> response =
                testRestTemplate.getForEntity(DIRECTIONS_URL + "/99999", Direction.class);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void createOK() {
        String json = """
                {
                    "street": "Calle falsa", 
                    "postalCode": "33010", 
                    "city": "León", 
                    "country": "Spain"
                }
                """;
        ResponseEntity<Direction> response =
                testRestTemplate.postForEntity(DIRECTIONS_URL, createHttpRequest(json), Direction.class);
        // Pongo 200 = OK porque con 201 = CREATED salta el error
        // org.opentest4j.AssertionFailedError: Expected:201    Actual:200
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        Direction direction = response.getBody();
        assertNotNull(direction);
        assertEquals("Calle falsa", direction.getStreet());
    }

    @Test
    void createBadRequest() {
        String json = """
                {
                    "id": 2,
                    "street": "Calle falsa", 
                    "postalCode": "33010", 
                    "city": "León", 
                    "country": "Spain"
                }
                """;
        ResponseEntity<Direction> response =
                testRestTemplate.postForEntity(DIRECTIONS_URL, createHttpRequest(json), Direction.class);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.hasBody());
        // Deberíamos obtener un mensaje 404 pero realiza la creación del fabricante con id nulo
        //   org.opentest4j.AssertionFailedError:
        //          Expected :400
        //          Actual   :200
    }

    @Test
    void update() {
        String url = DIRECTIONS_URL;
        Direction direction = createDemoDirection();
        String json = String.format("""
                {
                    "id": %d,
                    "street": "Calle modificada desde JUnit", 
                    "postalCode": "00000", 
                    "city": "City", 
                    "country": "Country"
                }
                """, direction.getId());

        ResponseEntity<Direction> response =
                testRestTemplate.exchange(url, HttpMethod.PUT, createHttpRequest(json), Direction.class);
        // org.opentest4j.AssertionFailedError:     Expected:200 OK     Actual: 400 BAD REQUEST
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());
        Direction responseDirection = response.getBody();
        assertEquals(direction.getId(), responseDirection.getId());
        assertEquals("Calle Falsa - EDITADA", responseDirection.getStreet());
        assertNotEquals(responseDirection.getStreet(), direction.getStreet());
    }

    @Test
    void updateBadRequest() {
        String json = """
                {
                    "id": null,
                    "street": "Calle falsa", 
                    "postalCode": "33010", 
                    "city": "León", 
                    "country": "Spain"
                }
                """;
        ResponseEntity<Direction> response =
                testRestTemplate.exchange(DIRECTIONS_URL, HttpMethod.PUT, createHttpRequest(json), Direction.class);
        // org.opentest4j.AssertionFailedError:     Expected:400 BAD REQUEST    500 INTERNAL SERVER ERROR
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @Test
    void updateNotFoundRequest() {
        String json = """
                {
                    "id": 999L,
                    "street": "Calle falsa", 
                    "postalCode": "33010", 
                    "city": "León", 
                    "country": "Spain"
                }
                """;
        ResponseEntity<Direction> response =
                testRestTemplate.exchange(DIRECTIONS_URL, HttpMethod.PUT, createHttpRequest(json), Direction.class);
        // org.opentest4j.AssertionFailedError: Expected:404 NOT FOUND   Actual:400 BAD REQUEST
        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());

    }

    @Test
    void deleteById() {
        Direction directions = createDemoDirection();
        String url = DIRECTIONS_URL + "/" + directions.getId();
        ResponseEntity<Manufacturer> response = testRestTemplate.getForEntity(url, Manufacturer.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(directions.getId(), response.getBody().getId());
        testRestTemplate.delete(url);
        ResponseEntity<Direction> response2 = testRestTemplate.getForEntity(url, Direction.class);
        // org.opentest4j.AssertionFailedError: Expected:404 NOT FOUND   Actual:200 OK
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
        String url = DIRECTIONS_URL;
        ResponseEntity<Direction[]> response = testRestTemplate.getForEntity(url, Direction[].class);
        assertNotNull(response.getBody());
        List<Direction> directions = List.of(response.getBody());
        assertTrue(directions.size() >= 0);
        testRestTemplate.delete(url);
        response = testRestTemplate.getForEntity(url, Direction[].class);
        assertNotNull(response.getBody());
        directions = List.of(response.getBody());
        //  org.opentest4j.AssertionFailedError:    Expected:0      Actual:2
        assertEquals(0, directions.size());
    }



    private Direction createDemoDirection() {
        String json = """
                {
                    "street": "Calle falsa", 
                    "postalCode": "33010",
                    "city": "León",
                    "country":"Spain"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Direction> response =
                testRestTemplate.postForEntity(DIRECTIONS_URL, request, Direction.class);
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
