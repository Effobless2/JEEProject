package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository repository;

    @Override
    public User add(User user) {
        return repository.add(user);
    }

    @Override
    public List<User> get() {
        return repository.get();
    }

    @Override
    public User get(long id) {
        return repository.get(id);
    }

    @Override
    public User connect(User user) {
        User result;
        User searchResult = repository.getByGoogleId(user.getGoogleId());
        if(searchResult == null){
            result = repository.add(user);
        } else {
            user.setId(searchResult.getId());
            repository.update(user);
            result = user;
        }
        return result;

    }
}
