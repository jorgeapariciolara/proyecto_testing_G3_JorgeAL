package com.example.proyectotesting.patterns.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("DeliveredStateTest")
class DeliveredStateTest {
    DeliveredState deliveredState;
    Order order;
    @BeforeEach
    void setUp() {
        deliveredState = new DeliveredState();
        order=new Order(1L,LocalDateTime.now());
    }
    @Test
    @DisplayName("Siguiente")
    void next() {

        try {
            deliveredState.next(order);
            assertTrue(true);
        }catch(Exception error){
            assertTrue(false);
        }
    }

    @Test
    @DisplayName("Anterior")
    void previous() {
        Order mockOrder = (mock(Order.class));
        doNothing().when(mockOrder).setState(any());
        deliveredState.previous(mockOrder);
        verify(mockOrder).setState(any());

    }
}
