package app.repositories;

import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryJdbc implements UserRepository {
    private NamedParameterJdbcTemplate template;

    @Autowired
    public UserRepositoryJdbc(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public User findByID(UUID id) {
        final String sqlFindByID = "SELECT id,userName,password FROM UsersSQL WHERE id=:id";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource("id",id.toString());
        Map<String, Object> userAsMap = template.queryForMap(sqlFindByID,sqlParam);
        return convertUserFromMap(userAsMap);
    }

    @Override
    public User save(User user) {
        if(Objects.isNull(user.getId())) user.setId(UUID.randomUUID());
        final String sqlSave = "INSERT INTO UsersSQL (id,userName,password) VALUES (:id, :userName, :password)";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("id",user.getId().toString());
        sqlParam.addValue("userName",user.getUsername());
        sqlParam.addValue("password",user.getMd5Password());
        template.update(sqlSave,sqlParam);
        return findByID(user.getId());
    }

    @Override
    public User delete(User user) {
        final String sqlDeleteUser = "DELETE FROM UsersSQL " +
                "WHERE id=:id AND username=:userName AND password=:password";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("id",user.getId().toString());
        sqlParam.addValue("userName",user.getUsername());
        sqlParam.addValue("password",user.getMd5Password());
        template.update(sqlDeleteUser,sqlParam);
        return user;
    }

    @Override
    public Set<User> findAll() {
        final String sqlFindAll = "SELECT id,userName,password FROM UsersSQL";
        MapSqlParameterSource sqlPlug = new MapSqlParameterSource();
        List<Map<String, Object>> allUsersAsMap = template.queryForList(sqlFindAll, sqlPlug);
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
