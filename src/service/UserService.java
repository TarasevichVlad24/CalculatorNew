package service;

import entity.User;
import storage.InMemoryUserStorage;

import java.util.Optional;

public class UserService {

    private final InMemoryUserStorage storage = new InMemoryUserStorage();
    public void create(User user){
        storage.save(user);

    }
    public Optional<User> findByUserName(String username){
        return storage.getByUserName(username);
    }
}
