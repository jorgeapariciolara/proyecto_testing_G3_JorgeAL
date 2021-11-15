package com.example.proyectotesting.patterns.creational.prototype;

import com.example.proyectotesting.patterns.creational.factory.EmpleadoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CircleTest")
class CircleTest {
    Circle circle;
    @BeforeEach
    void setUp() {
        circle = new Circle("rojo",10);
    }
    @Test
    @DisplayName("Copiar")
    void copy() {
        Shape circulo=circle.copy();
        assertEquals("Circle",circulo.getClass().getSimpleName());
        assertEquals(circulo.getColor(),circle.getColor());
    }
}
