package app.repositories;

import app.models.User;

import java.util.Set;
import java.util.UUID;

public interface UserRepository {
    User findByID(UUID id);
    User saveUser(User user);
    User deleteUser(User user);
    Set<User> findAll();
}
