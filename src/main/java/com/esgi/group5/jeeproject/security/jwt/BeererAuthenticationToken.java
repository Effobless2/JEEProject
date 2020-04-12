package com.esgi.group5.jeeproject.security.jwt;

import com.esgi.group5.jeeproject.models.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BeererAuthenticationToken extends AbstractAuthenticationToken {
    private User principal;

    public BeererAuthenticationToken(User principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return principal.getId();
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
