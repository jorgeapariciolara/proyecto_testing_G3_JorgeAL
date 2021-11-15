package com.example.proyectotesting.patterns.behavioral.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("ShippedStateTest")
class ShippedStateTest {

    @Test
    @DisplayName("Siguiente")
    void next() {

        ShippedState shippedState =new ShippedState();
        Order mockOrder = (mock(Order.class));
        doNothing().when(mockOrder).setState(any());
        shippedState.next(mockOrder);
        verify(mockOrder).setState(any());
    }

    @Test
    @DisplayName("Anterior")
    void previous() {

        ShippedState shippedState =new ShippedState();
        Order mockOrder = (mock(Order.class));
        doNothing().when(mockOrder).setState(any());
        shippedState.previous(mockOrder);
        verify(mockOrder).setState(any());
    }
}