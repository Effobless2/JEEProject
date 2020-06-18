package com.esgi.group5.jeeproject.web.controllers;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.web.dtos.users.GoogleAccountDTO;
import com.esgi.group5.jeeproject.web.security.AuthenticationFromGoogleService;
import com.esgi.group5.jeeproject.web.security.googleToken.JWTGoogleService;
import com.esgi.group5.jeeproject.web.security.beererToken.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final JWTGoogleService jwtGoogleService;
    private final TokenProvider tokenService;
    private final AuthenticationFromGoogleService authenticationFromGoogleService;

    public UserController(JWTGoogleService jwtGoogleService, TokenProvider tokenService, AuthenticationFromGoogleService authenticationFromGoogleService) {
        this.jwtGoogleService = jwtGoogleService;
        this.tokenService = tokenService;
        this.authenticationFromGoogleService = authenticationFromGoogleService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authentication(HttpServletRequest request, @RequestBody @Valid String googleToken){

        Optional<GoogleAccountDTO> googleAccountDTO = jwtGoogleService.getGoogleAccountFromToken(googleToken);

        if(googleAccountDTO.isEmpty())
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();

        User connectedUser = authenticationFromGoogleService.authenticateUser(googleAccountDTO.get());
        String token = tokenService.generateToken(connectedUser);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(token);
    }
}
