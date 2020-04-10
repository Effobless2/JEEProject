package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.security.InvalidGoogleTokenException;
import com.esgi.group5.jeeproject.security.JWTGoogleService;
import com.esgi.group5.jeeproject.security.JwtUtilService;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final JWTGoogleService jwtGoogleService;
    private final JwtUtilService jwtUtil;

    @PostMapping("/auth")
    public ResponseEntity<?> authentication(HttpServletRequest request, @RequestBody @Valid String googleToken){
        try {
            GoogleIdToken googleIdToken = jwtGoogleService.tokenVerification(googleToken.substring(6));
            if(googleIdToken == null){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Parsing Error");
            }

            User user = jwtGoogleService.convertGoogleTokenToUser(googleIdToken);
            user = userService.connect(user);

            String token = jwtUtil.generateToken(user);
            Map<String, Object> bodyResponse = new HashMap<>();
            bodyResponse.put("token", token);


            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bodyResponse);

        } catch (InvalidGoogleTokenException e) {
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public List<User> get(){
        return userService.get();
    }

    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") int userId){
        return userService.get(userId);
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid User user){
        User result = userService.add(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result.getId());
    }
}
