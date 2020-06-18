package com.esgi.group5.jeeproject.web.security.beererToken;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.web.dtos.users.UserWithTokenDTO;
import com.esgi.group5.jeeproject.web.dtos.users.parsers.UserParser;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class TokenProvider {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${beerer.admin.email}")
    private String ADMIN_EMAIL;

    private final int TOKEN_DURATION = 1000 * 60 * 60 * 10;

    public String generateToken(User user) {
        UserWithTokenDTO userWithTokenDTO = convertToUserToken(user);
        Claims claims = generateClaims(userWithTokenDTO);
        return createToken(claims, userWithTokenDTO.getId());
    }

    private UserWithTokenDTO convertToUserToken(User user) {
        UserWithTokenDTO result = UserParser.parse(user);
        if(user.getEmail().equals(ADMIN_EMAIL)) {
            result.addRoles("ROLE_ADMIN");
        } else {
            result.addRoles("ROLE_USER");
        }
        return result;
    }

    private Claims generateClaims(UserWithTokenDTO user) {
        Claims claims = Jwts.claims().setSubject(user.getId().toString());
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("avatarUrl", user.getAvatarUrl());
        claims.put("roles", user.getRoles());
        return claims;
    }

    private String createToken(Claims claims, Long id) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_DURATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                System.out.println("Expired JWT token.");
                return false;
            }
            return true;
        } catch (MalformedJwtException | SignatureException e) {
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

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private Long getUserId(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject());
    }

    private UserWithTokenDTO getUserFromToken(String token) {
        if(token == null)
            return null;
        UserWithTokenDTO u = new UserWithTokenDTO();
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        u.setId(Long.parseLong(claims.get("id").toString()));
        u.setName(claims.get("name").toString());
        u.setAvatarUrl(claims.get("avatarUrl").toString());
        u.setEmail(claims.get("email").toString());
        u.setRoles(getRoles(claims));
        return u;
    }
    private List<String> getRoles(Claims claims){
        return ((List<String>)claims.get("roles"));
    }

    public UserWithTokenDTO getUser(HttpServletRequest req){
        String token = resolveToken(req);
        if (token == null)
            return null;
        return getUserFromToken(token);
    }

    public Authentication getAuthentication(String token) {
        UserWithTokenDTO user = getUserFromToken(token);
        Collection<GrantedAuthority> authorities = getAuthorities(user);
        return new BeererAuthenticationToken(user, authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(UserWithTokenDTO user){
        return user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(toList());
    }
}
