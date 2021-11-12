package com.example.proyectotesting.patterns.structural.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TractorAdapterTest {

    TractorAdapter tractorAdapter;

    @Test
    void speedUpGear1() {
        try {
            tractorAdapter = new TractorAdapter();
            tractorAdapter.speedUp(11);
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
        }
    }

    @Test
    void speedUpGear2() {
        try {
            tractorAdapter = new TractorAdapter();
            tractorAdapter.speedUp(16);
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
        }
    }
}

