package com.example.proyectotesting.patterns.creational.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InvoiceTest")
class InvoiceTest {

    @Test
    @DisplayName("Calcular precio toral")
    void calculateTotalPrice() {
        List<Double> prices = new ArrayList<>();
        Invoice invoice = new Invoice();
        double invoiceResult= invoice.calculateTotalPrice();
        Offer offer = new Offer();
        double offerResult = offer.calculateTotalOffer();
        assertEquals(invoiceResult,offerResult);
    }
}
