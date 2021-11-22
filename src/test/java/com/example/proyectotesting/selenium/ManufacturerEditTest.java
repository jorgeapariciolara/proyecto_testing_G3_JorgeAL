package com.example.proyectotesting.selenium;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManufacturerEditTest extends BaseTest{

    private static final String URL="http://localhost:8080/manufacturers/1/edit";

    @Test
    @DisplayName("Titulo Manufacturer")
    void Titulotest(){

        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Manufacturer Edition | Aswesome App",title);
    }
}








