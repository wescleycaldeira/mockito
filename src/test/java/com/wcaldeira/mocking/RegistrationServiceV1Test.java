package com.wcaldeira.mocking;

import mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RegistrationServiceV1Test {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private BannedUsersClient bannedUsersClient = Mockito.mock(BannedUsersClient.class);
    private RegistrationService registrationService = new RegistrationService(userRepository, bannedUsersClient);

    @Test
    void shouldRegisterUnknownUser() {
        UserRepository realUserRepository = new JpaUserRepository();

        System.out.println(realUserRepository.toString());
        System.out.println(realUserRepository.getClass());

        System.out.println(userRepository.toString());
        System.out.println(userRepository.getClass());

        System.out.println(bannedUsersClient.toString());
        System.out.println(bannedUsersClient.getClass());
    }

}
