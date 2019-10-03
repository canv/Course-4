package app.repositories;


import app.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositorySafeToMap implements UserRepository {
    private final Map<UUID, User> uuidUserMap;

    public UserRepositorySafeToMap() {
        this.uuidUserMap = new HashMap<>();
    }

    @Override
    public User findByID(UUID id){
        return  uuidUserMap.get(id);
    }

    @Override
    public User save(User user){
        uuidUserMap.put(user.getId(),user);
        return user;
    }

    @Override
    public Set<User> findAll(){
        return new HashSet<>(uuidUserMap.values());
    }
}
