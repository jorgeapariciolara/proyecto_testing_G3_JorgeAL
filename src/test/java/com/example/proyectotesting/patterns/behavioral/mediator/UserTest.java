package com.example.proyectotesting.patterns.behavioral.mediator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("UserTest")
class UserTest {
    User user = new User(new Telegram(),"name");
    @Test
    @DisplayName("Enviar mensaje")
    void send() {

        user.mediator = mock(Telegram.class);
        doNothing().when(user.mediator).sendMessage("msg",user);
        user.send("msg");
        verify(user.mediator).sendMessage("msg",user);
    }

    @Test
    @DisplayName("Recibir mensaje")
    void receive() {
        assertDoesNotThrow(() ->user.receive("msg"));
    }
}
