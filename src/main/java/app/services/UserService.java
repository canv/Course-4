package app.services;

import app.models.User;

import java.util.Set;

public interface UserService {
    User getByID(String id);
    User save(User saveUser);
    Set<User> getAll();

}
