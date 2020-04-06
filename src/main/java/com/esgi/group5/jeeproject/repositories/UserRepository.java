package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.DAL.UserRepositoryDAL;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final UserRepositoryDAL userRepositoryDAL;

    @Override
    public int add(User user) {
        User created = userRepositoryDAL.save(user);
        return Math.toIntExact(created.getId());
    }

    @Override
    public List<User> get() {
        return userRepositoryDAL.findAll();
    }

    @Override
    public User get(int id) {
        Optional<User> user = userRepositoryDAL.findById((long) id);
        return user.orElse(null);

    }
}
