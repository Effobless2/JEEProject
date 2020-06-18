package com.esgi.group5.jeeproject.web.security.beererToken;

import com.esgi.group5.jeeproject.web.dtos.users.UserWithTokenDTO;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BeererAuthenticationToken extends AbstractAuthenticationToken {
    private UserWithTokenDTO principal;

    public BeererAuthenticationToken(UserWithTokenDTO principal, Collection<? extends GrantedAuthority> authorities) {
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
