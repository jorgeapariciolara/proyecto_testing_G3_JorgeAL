package com.example.proyectotesting.patterns.structural.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TractorTest {

    Tractor tractor;

    @BeforeEach
    void setUp() {
        tractor = new Tractor();
    }

    @Test
    void getSpeed() {
        tractor.getSpeed();
        assertNotNull(tractor.getSpeed());
    }

    @Test
    void setSpeed() {
        tractor.setSpeed(50);
        assertEquals(50, tractor.getSpeed());
    }

    @Test
    void changeModeNullGear() {
        tractor.setSpeed(0);
        tractor.changeMode(0);
        assertEquals(0,tractor.getSpeed());
    }

    @Test
    void changeModeGear1() {
        tractor.changeMode(1);
        assertEquals(5,tractor.getSpeed());
    }

    @Test
    void changeModeGear2() {
        tractor.changeMode(2);
        assertEquals(15,tractor.getSpeed());
    }
}

