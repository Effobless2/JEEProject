package com.esgi.group5.jeeproject.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.UserDAO;
import com.esgi.group5.jeeproject.persistence.datatbase.parsers.UserParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface JpaUserRepository extends JpaRepository<UserDAO, Long>, UserRepository {


    default User create(User user) {
        UserDAO dao = UserParser.parse(user);
        UserDAO saved = save(dao);
        return UserParser.parse(saved);
    }

    default Collection<User> get() {
        return findAll()
                .stream()
                .map(UserParser::parse)
                .collect(Collectors.toList());
    }

    default Optional<User> get(Long id) {
        Optional<UserDAO> found = findById(id);
        if(found.isEmpty())
            return Optional.empty();
        return Optional.of(UserParser.parse(found.get()));
    }

    default void delete(Long id) {
        deleteById(id);
    }

    default Optional<User> update(User user) {
        if(user.getId() == null)
            return Optional.empty();
        update(
            user.getId(),
            user.getName(),
            user.getAvatarUrl(),
            user.getEmail()
        );
        return get(user.getId());
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