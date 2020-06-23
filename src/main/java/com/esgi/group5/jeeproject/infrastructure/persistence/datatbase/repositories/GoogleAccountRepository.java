package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.GoogleAccountAndBeererUserRelationshipDAO;
import com.esgi.group5.jeeproject.infrastructure.web.security.beererToken.BeererAuthenticationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleAccountRepository extends JpaRepository<GoogleAccountAndBeererUserRelationshipDAO, String>, BeererAuthenticationRepository {

    default Optional<GoogleAccountAndBeererUserRelationshipDAO> findUserByGoogleId(String googleId) {
        return findById(googleId);
    }

    default GoogleAccountAndBeererUserRelationshipDAO create(GoogleAccountAndBeererUserRelationshipDAO relationship) {
        return save(relationship);
    }
}
