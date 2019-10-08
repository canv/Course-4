package app.repositories;

import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryJdbc implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByID(UUID id) {
        Map<String, Object> userAsMap = jdbcTemplate.queryForMap(
                "SELECT id,userName,password FROM UsersSQL WHERE id=?", id.toString());
        return convertUserFromMap(userAsMap);
    }

    @Override
    public User save(User user) {
        if(Objects.isNull(user.getId())) user.setId(UUID.randomUUID()); // don't forget to fix it
        jdbcTemplate.update("INSERT INTO UsersSQL (id,userName,password) VALUES (?,?,?)",
                user.getId().toString(), user.getUsername(), user.getMd5Password());
        return findByID(user.getId());
    }

    @Override
    public Set<User> findAll() {
        List<Map<String, Object>> allUsersAsMap = jdbcTemplate.queryForList("SELECT id,userName,password FROM UsersSQL");
        return allUsersAsMap.stream()
                .map(this::convertUserFromMap)
                .collect(Collectors.toSet());
    }

    private User convertUserFromMap(Map<String, Object> userAsMap) {
        return new User(
                UUID.fromString(userAsMap.get("id").toString()),
                userAsMap.get("userName").toString(),
                userAsMap.get("password").toString()
        );
    }
}
