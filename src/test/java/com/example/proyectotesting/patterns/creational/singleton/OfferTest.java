package com.example.proyectotesting.patterns.creational.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OfferTest")
class OfferTest {
    Calculator calculator;

    @Test
    void calculateTotalOffer() {

        List<Double> prices = new ArrayList<>();
        Calculator calculator = new Calculator();

        Offer offer = new Offer();
        double result= offer.calculateTotalOffer();

        assertTrue(result==0d);
    }
}
