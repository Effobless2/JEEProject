package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.User;

import java.util.List;

public interface IUserService {
    int add(User user);
    List<User> get();
    User get(int id);
}
