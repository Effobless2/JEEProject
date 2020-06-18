package com.esgi.group5.jeeproject.domain.use_cases.users;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;

import java.util.Collection;

public class ReadUserService {
    private final UserRepository userRepository;

    public ReadUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> get(){
        return this.userRepository.get();
    }

    public User get(Long userId) {
        return this.userRepository.get(userId).orElse(null);
    }
}
