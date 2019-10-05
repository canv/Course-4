package app.repositories;

import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Set;
import java.util.UUID;

public class UserRepositoryJdbc implements UserRepository {
    //@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findByID(UUID id) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Set<User> findAll() {
        return null;
    }
}
