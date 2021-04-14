package mocking;

import mockito.BannedUsersClient;
import mockito.RegistrationService;
import mockito.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RegistrationServiceV2Test {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BannedUsersClient bannedUsersClient;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
//        Strategy of @InjectMocks doesn't work
//        this.registrationService = new RegistrationService(userRepository, bannedUsersClient);
    }

    @Test
    void shouldRegisterUnknownUser(){
        System.out.println(userRepository.getClass());

        System.out.println(bannedUsersClient.getClass());

        System.out.println(registrationService.toString());
    }

}
