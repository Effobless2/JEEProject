package com.esgi.group5.jeeproject.security;

import com.esgi.group5.jeeproject.models.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtilService {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("avatarUrl", user.getAvatarUrl());
        return createToken(claims, user.getId());
    }

    private String createToken(Map<String, Object> claims, Long id) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature.");
            return false;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token.");
            return false;
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
            return false;
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
            return false;
        }
    }
}
