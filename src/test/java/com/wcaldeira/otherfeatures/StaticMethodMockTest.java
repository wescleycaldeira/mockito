package com.wcaldeira.otherfeatures;

import com.wcaldeira.Utils;
import mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StaticMethodMockTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BannedUsersClient bannedUsersClient;

    @InjectMocks
    private RegistrationService registrationService;

    private LocalDateTime defaultLocalDateTime = LocalDateTime.of(2020, 1, 1, 12, 0);

    @Test
    void mockStaticMelhod() {
        System.out.println(LocalDateTime.now());

        try (MockedStatic<LocalDateTime> localDateTimeMockedStatic = mockStatic(LocalDateTime.class)){

            localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenReturn(false);
            when(userRepository.findByUsername("duke")).thenReturn(null);
            when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
                User user = invocation.getArgument(0);
                user.setId(42L);
                return user;
            });

            final User user = registrationService.registerUser("duke", Utils.createContactInformation("duke@mockito.org"));

            System.out.println(user.getCreatedAt());
            System.out.println(LocalDateTime.now());
        }

        System.out.println(LocalDateTime.now());
    }

}
