package com.esgi.group5.jeeproject.repositories.contracts;

import com.esgi.group5.jeeproject.models.User;

import java.util.List;

public interface IUserRepository{
    User add(User user);
    List<User> get();
    User get(long id);

    User getByGoogleId(String googleId);

    boolean update(User user);
}
