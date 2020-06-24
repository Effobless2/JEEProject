package com.esgi.group5.jeeproject.domain.use_cases.users;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;

import java.util.Collection;

public class GetAllUsers {
    private final UserRepository userRepository;

    public GetAllUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> execute(){
        return this.userRepository.getAllUsers();
    }
}
