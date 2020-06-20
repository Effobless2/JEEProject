package com.esgi.group5.jeeproject.domain.use_cases.users;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;

import java.util.Collection;

public class ReadUser {
    private final UserRepository userRepository;

    public ReadUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> getAllUsers(){
        return this.userRepository.getAllUsers();
    }

    public User getUserById(Long userId) {
        return this.userRepository.getUserById(userId).orElse(null);
    }
}
