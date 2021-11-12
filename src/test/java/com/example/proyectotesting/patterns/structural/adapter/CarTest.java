package com.example.proyectotesting.patterns.structural.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
    }

    @Test
    void speedUp() {
        double speed = car.getSpeed();
        car.setSpeed(20);
        assertEquals(speed+20, car.getSpeed());
    }

    @Test
    void getSpeed() {
        car.getSpeed();
        assertNotNull(car.getSpeed());
    }

    @Test
    void setSpeed() {
        car.setSpeed(130);
        assertEquals(130, car.getSpeed());
    }
}



