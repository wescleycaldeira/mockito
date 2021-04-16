package com.wcaldeira.otherfeatures;

import mockito.BannedUsersClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith({MockitoExtension.class})
public class SkyTest {

    // @Spy - Preserve the real implemetation
    @Spy
    private BannedUsersClient bannedUsersClient;

    @Test
    void spies(){
        Mockito.when(bannedUsersClient.amountOfBannedAccounts()).thenReturn(300);


        System.out.println(bannedUsersClient.banRate());
        System.out.println(bannedUsersClient.amountOfBannedAccounts());
        System.out.println(bannedUsersClient.bannedUserId());
    }

    void spiesGotcha() {
        List spiedList = Mockito.spy(ArrayList.class);

//        Don`t work because the stubbing occurs after the calling
//        Mockito.when(spiedList.get(0)).thenReturn("spy");

        Mockito.doReturn("spy").when(spiedList).get(0);

        System.out.println(spiedList.get(0));
    }

}
