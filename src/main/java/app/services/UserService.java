package app.services;

import app.models.User;

import java.util.Set;

public interface UserService {
    User getByID(String userID);
    User save(User saveUser);
    User deleteUser(User deleteUser);
    Set<User> getAll();
}
