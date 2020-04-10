package com.esgi.group5.jeeproject.DAL;

import com.esgi.group5.jeeproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAL extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.googleId = :googleId")
    Optional<User> findUserByGoogleId(@Param("googleId") String googleId);
}