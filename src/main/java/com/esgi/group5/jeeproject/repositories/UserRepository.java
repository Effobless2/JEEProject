package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.DAL.UserDAL;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final UserDAL userDAL;

    @Override
    public long add(User user) {
        User created = userDAL.save(user);
        return created.getId();
    }

    @Override
    public List<User> get() {
        return userDAL.findAll();
    }

    @Override
    public User get(long id) {
        Optional<User> user = userDAL.findById(id);
        return user.orElse(null);

    }
}
