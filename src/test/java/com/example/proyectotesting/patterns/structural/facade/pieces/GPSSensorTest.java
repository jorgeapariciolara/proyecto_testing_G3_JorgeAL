package com.example.proyectotesting.patterns.structural.facade.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GPSSensorTest")
class GPSSensorTest {

    GPSSensor gpsSensor;

    @BeforeEach
    void setUp() {gpsSensor = new GPSSensor();}

    @Test
    @DisplayName("Comienza")
    void start() {
        try {
            gpsSensor.start();
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    @DisplayName("Para")
    void stop() {
        try {
            gpsSensor.stop();
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }
}
