package com.esgi.group5.jeeproject.infrastructure.web.security;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.use_cases.users.ReadUser;
import com.esgi.group5.jeeproject.domain.use_cases.users.RegisterUser;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.users.GoogleAccountDTO;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.users.parsers.UserParser;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.GoogleAccountAndBeererUserRelationshipDAO;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.repositories.GoogleAccountRepository;
import com.esgi.group5.jeeproject.infrastructure.web.security.beererToken.BeererAuthenticationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationFromGoogleService {
    private final BeererAuthenticationRepository beererAuthenticationRepository;
    private final RegisterUser registerUser;
    private final ReadUser readUser;

    public AuthenticationFromGoogleService(GoogleAccountRepository beererAuthenticationRepository, RegisterUser registerUser, ReadUser readUser) {
        this.beererAuthenticationRepository = beererAuthenticationRepository;
        this.registerUser = registerUser;
        this.readUser = readUser;
    }

    public User authenticateUser(GoogleAccountDTO googleAccountDTO) {
        Optional<GoogleAccountAndBeererUserRelationshipDAO> relationship = getRelatedUserAccount(googleAccountDTO.getGoogleId());
        User user;
        if(relationship.isEmpty()) {
            user = createAccountForGoogleAccount(googleAccountDTO);
        }
        else {
            user = connectUser(relationship.get());
        }
        return user;
    }

    private User connectUser(GoogleAccountAndBeererUserRelationshipDAO relationship) {
        User user = readUser.getUserById(relationship.getBeererId());
        return user;
    }

    public Optional<GoogleAccountAndBeererUserRelationshipDAO> getRelatedUserAccount(String googleId){
        return beererAuthenticationRepository.findUserByGoogleId(googleId);
    }

    public User createAccountForGoogleAccount(GoogleAccountDTO googleAccountDTO) {
        User user = UserParser.parse(googleAccountDTO);
        User registered = registerUser.register(user);
        GoogleAccountAndBeererUserRelationshipDAO dao = new GoogleAccountAndBeererUserRelationshipDAO(
                googleAccountDTO.getGoogleId(),
                registered.getId()
        );
        GoogleAccountAndBeererUserRelationshipDAO registeredRelation = beererAuthenticationRepository.create(dao);
        return registered;
    }
}
