package com.esgi.group5.jeeproject.security.jwt;

import com.esgi.group5.jeeproject.models.Role;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.security.jwt.contracts.IBeererTokenService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BeererTokenService implements IBeererTokenService {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    public String generateToken(User user) {
        Claims claims = generateClaims(user);
        return createToken(claims, user.getId());
    }

    private Claims generateClaims(User user){
        Claims claims = Jwts.claims().setSubject(user.getId().toString());
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("avatarUrl", user.getAvatarUrl());
        claims.put("roles", user.getRoles().stream().map(Role::getLabel).collect(Collectors.toList()));
        return claims;
    }

    private String createToken(Claims claims, Long id) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
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

    private User getUserFromToken(String token) {
        if(token == null)
            return null;
        User u = new User();
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        u.setId(Long.parseLong(claims.get("id").toString()));
        u.setName(claims.get("name").toString());
        u.setAvatarUrl(claims.get("avatarUrl").toString());
        u.setEmail(claims.get("email").toString());
        u.setRoles(getRoles(claims));
        return u;
    }
    private Collection<Role> getRoles(Claims claims){
        return ((List<String>)claims.get("roles")).stream().map(r -> {
            Role role = new Role();
            role.setLabel(r);
            return role;
        }).collect(Collectors.toList());
    }

    public User getUser(HttpServletRequest req){
        String token = resolveToken(req);
        if (token == null)
            return null;
        return getUserFromToken(token);
    }

    public Authentication getAuthentication(String token) {
        User user = getUserFromToken(token);
        Collection<GrantedAuthority> authorities = getAuthorities(user);
        return new BeererAuthenticationToken(user, authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(User user){
        ArrayList<String> roles = new ArrayList<>();
        for(Role role: user.getRoles())
            roles.add(role.getLabel());
        return roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }
}
