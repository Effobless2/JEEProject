package com.esgi.group5.jeeproject.domain.repositories;
import com.esgi.group5.jeeproject.domain.models.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    User createUser(User user);
    Collection<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUserById(Long id);
    Optional<User> updateUser(User user);
}
