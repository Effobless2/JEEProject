package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public int post(@RequestBody User user){
        return userService.add(user);
    }
}
