package com.esgi.group5.jeeproject.repositories.contracts;

import com.esgi.group5.jeeproject.models.User;

import java.util.List;

public interface IUserRepository{
    int add(User user);
    List<User> get();
    User get(int id);
}
