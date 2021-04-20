package com.wcaldeira.otherfeatures;

import mockito.Address;
import mockito.ContactInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeepStubs {

    @Test
    void withoutDeepStubs() {
        ContactInformation contactInformation = Mockito.mock(ContactInformation.class);
        Address address = Mockito.mock(Address.class);

        Mockito.when(contactInformation.getAddress()).thenReturn(address);
        Mockito.when(address.getCity()).thenReturn("Brasilia");

        System.out.println(contactInformation.getAddress().getCity());
    }

    @Test
    void deepStubs(){
        ContactInformation contactInformation = Mockito.mock(ContactInformation.class, Answers.RETURNS_DEEP_STUBS);

        Mockito.when(contactInformation.getAddress().getCity()).thenReturn("Rio de Janeiro");

        System.out.println(contactInformation.getAddress().getCity());
    }

}
