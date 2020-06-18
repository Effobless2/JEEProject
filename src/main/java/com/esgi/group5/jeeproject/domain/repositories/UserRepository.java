package com.esgi.group5.jeeproject.domain.repositories;
import com.esgi.group5.jeeproject.domain.models.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    User create(User user);
    Collection<User> get();
    Optional<User> get(Long id);
    void delete(Long id);
    Optional<User> update(User user);
}
