package com.esgi.group5.jeeproject.web.security.database.repository;

import com.esgi.group5.jeeproject.web.security.database.daos.GoogleAccountAndBeererUserRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleAccountRepository extends JpaRepository<GoogleAccountAndBeererUserRelationship, String> {
    @Query("SELECT u FROM GoogleAccountAndBeererUserRelationship u WHERE u.googleId = :googleId")
    Optional<GoogleAccountAndBeererUserRelationship> findUserByGoogleId(@Param("googleId") String googleId);

    default GoogleAccountAndBeererUserRelationship create(GoogleAccountAndBeererUserRelationship relationship) {
        return save(relationship);
    }
}
