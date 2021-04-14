package com.wcaldeira.stubbing;

import com.wcaldeira.Utils;
import mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
// Doesn't valid id the stub method is being called
// @MockitoSettings(strictness = Strictness.LENIENT)
public class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BannedUsersClient bannedUsersClient;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void defaultBehaviour() {
        System.out.println(userRepository.findByUsername("mike"));
        System.out.println(userRepository.save(new User()));
        System.out.println(bannedUsersClient.isBanned("mike", new Address()));
        System.out.println(bannedUsersClient.amountOfBannedAccounts());
        System.out.println(bannedUsersClient.amountOfGloballyBannedAccounts());
        System.out.println(bannedUsersClient.banRate());
        System.out.println(bannedUsersClient.bannedUserId());
    }

    @Test
    void basicStubbing() {
//        Mockito.when: uses .equals() to compare objects
        when(bannedUsersClient.isBanned("duke", new Address())).thenReturn(true);

        System.out.println(bannedUsersClient.isBanned("duke", new Address()));
        System.out.println(bannedUsersClient.isBanned("duke", null));
        System.out.println(bannedUsersClient.isBanned("mike", new Address()));
    }

    @Test
    void basicStubbingWithArgumentMatchers() {
        // Don't work because mockito not acept only one matcher
//        Mockito.when(bannedUsersClient
//                .isBanned("duke", ArgumentMatchers.any(Address.class))).thenReturn(true);

        when(bannedUsersClient
                .isBanned(ArgumentMatchers.eq("duke"), ArgumentMatchers.any(Address.class))).thenReturn(true);

        when(bannedUsersClient
                .isBanned(ArgumentMatchers.anyString(), ArgumentMatchers.isNull())).thenReturn(true);

        // Using function inside matcher
        when(bannedUsersClient
                .isBanned(ArgumentMatchers.argThat(s -> s.length() <= 3), ArgumentMatchers.isNull())).thenReturn(false);

        System.out.println(bannedUsersClient.isBanned("duke", new Address()));
        System.out.println(bannedUsersClient.isBanned("shdshfhsdlf", null));
        System.out.println(bannedUsersClient.isBanned("foo", null));
    }

    @Test
    void basicStubbingUsageThrows() {
        when(bannedUsersClient.isBanned(eq("duke"), any())).thenThrow(new RuntimeException("Remote system is down!"));

        System.out.println(bannedUsersClient.isBanned("mike", null));

        assertThrows(RuntimeException.class, () ->
                System.out.println(bannedUsersClient.isBanned("duke", new Address())));
    }

    @Test
    void basicStubbingUsageCallRealMethod() {
        when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenCallRealMethod();

        System.out.println(bannedUsersClient.isBanned("duke", new Address()));
    }

    @Test
    void basicStubbingUsageThenAnswer() {
        // .thenAnswer is good when you need more control of the mock object
        when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenAnswer(invocation -> {
            String username = invocation.getArgument(0);
            Address address = invocation.getArgument(1);
            return username.contains("d") && address.getCity().contains("d");
        });

        Address address = new Address();
        address.setCity("Berlin");

        System.out.println(bannedUsersClient.isBanned("duke", address));

        Address addressTwo = new Address();
        addressTwo.setCity("London");

        System.out.println(bannedUsersClient.isBanned("duke", addressTwo));

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(42L);
            return user;
        });

        System.out.println(userRepository.save(new User()).getId());
    }

    @Test
    void shouldNotAllowRegistrationOfBannedUsers() {
        when(bannedUsersClient.isBanned(eq("duke"), any(Address.class))).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> registrationService.registerUser("duke", Utils.createContactInformation("test@mockito.org")));
    }


}
