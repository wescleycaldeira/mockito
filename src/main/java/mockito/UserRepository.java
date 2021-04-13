package mockito;

public interface UserRepository {

    User save (User user);

    User findByUsername (String username);
}
