package com.esgi.group5.jeeproject.security.jwt.contracts;

import com.esgi.group5.jeeproject.models.User;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IBeererTokenService {
    String generateToken(User user);

    String resolveToken(HttpServletRequest httpServletRequest);

    boolean validateToken(String token);

    Authentication getAuthentication(String token);

    User getUser(HttpServletRequest req);
}
