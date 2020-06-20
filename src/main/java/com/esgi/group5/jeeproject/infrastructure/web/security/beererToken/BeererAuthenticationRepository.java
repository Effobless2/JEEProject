package com.esgi.group5.jeeproject.infrastructure.web.security.beererToken;

import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.GoogleAccountAndBeererUserRelationshipDAO;

import java.util.Optional;

public interface BeererAuthenticationRepository {
    Optional<GoogleAccountAndBeererUserRelationshipDAO> findUserByGoogleId(String googleId);
    GoogleAccountAndBeererUserRelationshipDAO create(GoogleAccountAndBeererUserRelationshipDAO relationship);
}
