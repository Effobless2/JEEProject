package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

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
        long id = userService.add(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }
}
