package com.example.proyectotesting.patterns.behavioral.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("ShopCartTest")
class ShopCartTest {

    ShopCart shopCart;
    Product product;

    @BeforeEach
    void setUp(){
        shopCart = new ShopCart();
        product = mock(Product.class);
    }

    @Test
    @DisplayName("Añade un producto a la lista")
    void addProduct() {
        try {
            shopCart.addProduct(product);
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            System.out.println("Error while adding");
            assertTrue(false);
        }
    }

    @Test
    @DisplayName("Elimina un producto de la lista")
    void removeProduct() {
        try {
            shopCart.addProduct(product);
            shopCart.removeProduct(product);
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            System.out.println("Error while removing");
            assertTrue(false);
        }
    }

    @Test
    @DisplayName("Confirmacion de pago")
    void pay() {
        PaymentStrategy payment = (mock(PayPalStrategy.class));
        doNothing().when(payment).pay(anyDouble());
        shopCart.pay(payment);
        verify(payment).pay(anyDouble());
    }
}
