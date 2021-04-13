package mockito;

import java.util.List;

public class BannedUsersClient {

    public boolean isBanned(String username, Address address){
        System.out.printf("Checking if " + username + " at given address is banned");

        return false;
    }

    public int amountOfBannedAccounts() {
        return 42;
    }

    public long amountOfGloballyBannedAccounts(){
        return 42;
    }

    public double banRate(){
        return 41.42;
    }

    public List<Long> bannedUserId(){
        return List.of(1L, 2L, 3L);
    }

}
