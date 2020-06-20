package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.UserDAO;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers.UserParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface JpaUserRepository extends JpaRepository<UserDAO, Long>, UserRepository {


    default User createUser(User user) {
        UserDAO dao = UserParser.parse(user);
        UserDAO saved = save(dao);
        return UserParser.parse(saved);
    }

    default Collection<User> getAllUsers() {
        return findAll()
                .stream()
                .map(UserParser::parse)
                .collect(Collectors.toList());
    }

    default Optional<User> getUserById(Long id) {
        Optional<UserDAO> found = findById(id);
        if(found.isEmpty())
            return Optional.empty();
        return Optional.of(UserParser.parse(found.get()));
    }

    default void deleteUserById(Long id) {
        deleteById(id);
    }

    default Optional<User> updateUser(User user) {
        if(user.getId() == null)
            return Optional.empty();
        update(
            user.getId(),
            user.getName(),
            user.getAvatarUrl(),
            user.getEmail()
        );
        return getUserById(user.getId());
    }
    @Transactional
    @Modifying
    @Query(value = "UPDATE UserDAO user " +
            "SET user.name = :name," +
            "user.avatarUrl = :avatarUrl," +
            "user.email = :email " +
            "WHERE user.id = :id")
    void update(Long id,
                String name,
                String avatarUrl,
                String email);
}