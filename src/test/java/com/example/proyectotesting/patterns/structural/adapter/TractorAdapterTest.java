package com.example.proyectotesting.patterns.structural.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TractorAdapterTest {
    TractorAdapter tractorAdapter;

    @Test
    @DisplayName("Subir velocidad 1")
    void speedUp1() {
        try {
            tractorAdapter = new TractorAdapter();
            tractorAdapter.speedUp(1);
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            fail();
        }
    }
    @Test
    @DisplayName("Subir velocidad 2")
    void speedUp2() {
        try {
            tractorAdapter = new TractorAdapter();
            tractorAdapter.speedUp(20);
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }
}

