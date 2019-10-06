package app.repositories;

import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Repository
public class UserRepositoryJdbc implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("" +
                "select id,userName from Users where id='f0425370-d93b-4c03-bc74-925f49cafd67'");
        System.out.println(stringObjectMap.get("id"));
    }

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
