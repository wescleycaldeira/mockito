package com.wcaldeira.stubbing;

import mockito.BannedUsersClient;
import mockito.EventNotifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventNotifierTest {

    @Mock
    private EventNotifier eventNotifier;

    @Mock
    private BannedUsersClient bannedUsersClient;

    @Test
    void voidMethodStubbing() {
        // Don't work for void methods
        // Mockito.when(eventNotifier.notifyNewUserCreation("duke")).thenReturn("duke");

        String username = "John";
        Mockito
                .doNothing()
                .doThrow(new RuntimeException("Tests error"))
                .when(eventNotifier).notifyNewUserCreation(username);

        eventNotifier.notifyNewUserCreation(username);
        Assertions.assertThrows(RuntimeException.class, () -> eventNotifier.notifyNewUserCreation(username));
    }
}
