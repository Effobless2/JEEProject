package com.esgi.group5.jeeproject.repositories;
/*
import com.esgi.group5.jeeproject.persistence.datatbase.repositories.JpaUserRepository;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.UserDAO;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserDAO add(UserDAO user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public List<UserDAO> get() {
        return jpaUserRepository.findAll();
    }

    @Override
    public UserDAO get(long id) {
        Optional<UserDAO> user = jpaUserRepository.findById(id);
        return user.orElse(null);

    }

    @Override
    public UserDAO getByGoogleId(String googleId) {
        Optional<UserDAO> user = jpaUserRepository.findUserByGoogleId(googleId);
        return user.orElse(null);
    }

    @Override
    public boolean update(UserDAO user) {
        Optional<UserDAO> search = jpaUserRepository.findById(user.getId());
        if(search.isEmpty())
            return false;
        jpaUserRepository.save(user);
        return true;
    }
}
*/