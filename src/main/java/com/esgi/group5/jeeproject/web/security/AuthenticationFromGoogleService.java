package com.esgi.group5.jeeproject.web.security;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.use_cases.users.ReadUserService;
import com.esgi.group5.jeeproject.domain.use_cases.users.RegisterUserService;
import com.esgi.group5.jeeproject.web.dtos.users.GoogleAccountDTO;
import com.esgi.group5.jeeproject.web.dtos.users.parsers.UserParser;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.GoogleAccountAndBeererUserRelationshipDAO;
import com.esgi.group5.jeeproject.persistence.datatbase.repositories.GoogleAccountRepository;
import com.esgi.group5.jeeproject.web.security.beererToken.BeererAuthenticationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationFromGoogleService {
    private final BeererAuthenticationRepository googleAccountRepository;
    private final RegisterUserService registerUserService;
    private final ReadUserService readUserService;

    public AuthenticationFromGoogleService(GoogleAccountRepository googleAccountRepository, RegisterUserService registerUserService, ReadUserService readUserService) {
        this.googleAccountRepository = googleAccountRepository;
        this.registerUserService = registerUserService;
        this.readUserService = readUserService;
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
        User user = readUserService.get(relationship.getBeererId());
        return user;
    }

    public Optional<GoogleAccountAndBeererUserRelationshipDAO> getRelatedUserAccount(String googleId){
        return googleAccountRepository.findUserByGoogleId(googleId);
    }

    public User createAccountForGoogleAccount(GoogleAccountDTO googleAccountDTO) {
        User user = UserParser.parse(googleAccountDTO);
        User registered = registerUserService.register(user);
        GoogleAccountAndBeererUserRelationshipDAO dao = new GoogleAccountAndBeererUserRelationshipDAO(
                googleAccountDTO.getGoogleId(),
                registered.getId()
        );
        GoogleAccountAndBeererUserRelationshipDAO registeredRelation = googleAccountRepository.create(dao);
        return registered;
    }
}
