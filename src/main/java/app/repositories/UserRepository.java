package app.repositories;

import app.models.User;

import java.util.Set;
import java.util.UUID;

public interface UserRepository {
    User findByID(UUID id);
    User save(User user);
    User delete(User user);
    Set<User> findAll();
}
