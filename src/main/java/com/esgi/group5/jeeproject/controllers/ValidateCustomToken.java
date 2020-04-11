package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.security.JwtUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/validateToken")
@RequiredArgsConstructor
public class ValidateCustomToken {
    public final JwtUtilService jwtUtil;

    @PostMapping
    public ResponseEntity<?> ValidateToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if(jwtUtil.validateToken(token)){
            return ResponseEntity.ok("token OK : " + token);
        }else{
            return ResponseEntity.status(403).body("access denied");
        }

    }
}
