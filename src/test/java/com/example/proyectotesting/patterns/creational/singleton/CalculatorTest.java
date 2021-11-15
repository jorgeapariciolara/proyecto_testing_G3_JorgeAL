package com.example.proyectotesting.patterns.creational.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("CalculatorTest")
class CalculatorTest {

    @Test
    @DisplayName("getCalculator")
    void getCalculator() {
        Calculator calculator = null;
        assertNotNull(calculator.getCalculator());
    }

    @Test
    @DisplayName("suma")
    void sumOkTest() {
        int num1=5;
        int num2=2;
        Calculator calculator = new Calculator();
        int result= calculator.sum(num1, num2);
        assertEquals (7,  result);
    }
}
