package com.example.proyectotesting.patterns.structural.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DigitalPhotoTest")
class DigitalPhotoTest {


    DigitalPhoto digitalPhoto;

    @BeforeEach
    void setUp() {digitalPhoto = new DigitalPhoto();}

    @Test
    @DisplayName("Devuelve info mensaje")
    void show() {assertEquals("Normal photo without edit",digitalPhoto.show());}

    @Test
    @DisplayName("Devuelve coste de la foto")
    void cost() {assertEquals(15D,digitalPhoto.cost());}
}
