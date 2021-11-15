package com.example.proyectotesting.patterns.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("ProcessingStateTest")
class ProcessingStateTest {



    @Test
    @DisplayName("Siguiente")
    void next() {
        ProcessingState Processing =new ProcessingState();
        Order mockOrder = (mock(Order.class));
        doNothing().when(mockOrder).setState(any());
        Processing.next(mockOrder);
        verify(mockOrder).setState(any());
    }

    @Test
    @DisplayName("Anterior")
    void previous() {
        ProcessingState mockProcessing = (mock(ProcessingState.class));
        Order mockOrder = (mock(Order.class));
        try {
            mockProcessing.previous(mockOrder);
            assertTrue(true);
        }catch(Exception error){
            assertTrue(false);
        }
    }
}