package com.esgi.group5.jeeproject.web.controllers;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.use_cases.users.ReadUserService;
import com.esgi.group5.jeeproject.web.dtos.users.GoogleAccountDTO;
import com.esgi.group5.jeeproject.web.security.AuthenticationFromGoogleService;
import com.esgi.group5.jeeproject.web.security.googleToken.JWTGoogleService;
import com.esgi.group5.jeeproject.web.security.beererToken.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final JWTGoogleService jwtGoogleService;
    private final TokenProvider tokenService;
    private final AuthenticationFromGoogleService authenticationFromGoogleService;
    private final ReadUserService readUserService;

    public UserController(JWTGoogleService jwtGoogleService, TokenProvider tokenService, AuthenticationFromGoogleService authenticationFromGoogleService, ReadUserService readUserService) {
        this.jwtGoogleService = jwtGoogleService;
        this.tokenService = tokenService;
        this.authenticationFromGoogleService = authenticationFromGoogleService;
        this.readUserService = readUserService;
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

        Map<String, Object> bodyResponse = new HashMap<>();
        bodyResponse.put("token", token);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bodyResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        Collection<User> users = readUserService.get();

        if(users.isEmpty()) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(users);
    }
}
