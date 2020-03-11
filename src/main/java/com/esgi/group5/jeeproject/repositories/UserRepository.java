package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository implements IUserRepository {
    private List<User> db;

    public UserRepository() {
        db = new ArrayList<>();
    }

    @Override
    public int add(User user) {
        db.add(user);
        return db.size() - 1;
    }

    @Override
    public List<User> get() {
        return db;
    }

    @Override
    public User get(int id) {
        return db.get(id);
    }
}
