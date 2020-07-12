package com.esgi.group5.jeeproject.infrastructure.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class TestController {
    @GetMapping
    public ResponseEntity<?> get(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello World 2");
    }
}
