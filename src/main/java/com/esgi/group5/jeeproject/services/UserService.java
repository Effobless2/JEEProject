package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    IUserRepository repository;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public int add(User user) {
        return repository.add(user);
    }

    @Override
    public List<User> get() {
        return repository.get();
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }
}
