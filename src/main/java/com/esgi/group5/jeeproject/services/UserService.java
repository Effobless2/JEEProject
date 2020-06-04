package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Role;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import com.esgi.group5.jeeproject.services.contracts.IRoleService;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository repository;
    private final IRoleService roleService;

    @Override
    public User add(User user) {
        return repository.add(user);
    }

    @Override
    public List<User> get() {
        return repository.get();
    }

    @Override
    public User get(Long id) {
        return repository.get(id);
    }

    @Override
    public User connect(User user) {
        User result;
        User searchResult = repository.getByGoogleId(user.getGoogleId());
        if(searchResult == null){
            Collection<Role> roles = new ArrayList<>();
            roles.add(roleService.getUserRole());
            user.setRoles(roles);
            result = repository.add(user);
        } else {
            searchResult.setEmail(user.getEmail());
            searchResult.setName(user.getName());
            searchResult.setAvatarUrl(user.getAvatarUrl());
            repository.update(searchResult);
            result = searchResult;
        }
        return result;

    }
}
