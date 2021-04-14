package mocking;

import mockito.BannedUsersClient;
import mockito.RegistrationService;
import mockito.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// JUnit 4 @RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class RegistrationServiceV3Test {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BannedUsersClient bannedUsersClient;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void shouldRegisterUnknownUser(){
        System.out.println(userRepository.getClass());

        System.out.println(bannedUsersClient.getClass());

        System.out.println(registrationService.toString());
    }


}
