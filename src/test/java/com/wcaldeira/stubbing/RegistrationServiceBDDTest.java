package com.wcaldeira.stubbing;

import mockito.BannedUsersClient;
import mockito.RegistrationService;
import mockito.User;
import mockito.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceBDDTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BannedUsersClient bannedUsersClient;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void basicStubbingWithBDD() {
        String john = "John";
        BDDMockito
                .given(userRepository.findByUsername(john))
                .willReturn(new User());

        BDDMockito
                .given(userRepository.save(any(User.class)))
                .willAnswer(invocation -> {
                    User user = invocation.getArgument(0);
                    user.setId(20L);
                    return user;
                });

        String username = "tests";
        BDDMockito
                .given(userRepository.findByUsername(username))
                .willThrow(new RuntimeException("Tests Error"));

        System.out.println(userRepository.findByUsername(john));
        Assertions.assertThrows(RuntimeException.class, () -> System.out.println(userRepository.findByUsername(username)));
        System.out.println(userRepository.save(new User()).getId());
    }

}
