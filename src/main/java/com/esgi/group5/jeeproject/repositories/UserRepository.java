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
    public User add(User user) {
        return userDAL.save(user);
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

    @Override
    public User getByGoogleId(String googleId) {
        Optional<User> user = userDAL.findUserByGoogleId(googleId);
        return user.orElse(null);
    }

    @Override
    public boolean update(User user) {
        Optional<User> search = userDAL.findById(user.getId());
        if(search.isEmpty())
            return false;
        userDAL.save(user);
        return true;
    }
}
