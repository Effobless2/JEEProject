package com.esgi.group5.jeeproject.security;

import com.esgi.group5.jeeproject.security.jwt.BeererAuthenticationTokenFilter;
import com.esgi.group5.jeeproject.security.jwt.contracts.IBeererTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final IBeererTokenService beererTokenService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        BeererAuthenticationTokenFilter beererAuthenticationTokenFilter = new BeererAuthenticationTokenFilter(beererTokenService);
        http
                .httpBasic().disable()
                .csrf().disable()
                .addFilterAfter(beererAuthenticationTokenFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/users/auth").permitAll()
                    .antMatchers(HttpMethod.PUT, "/users/").denyAll()
                    .antMatchers(HttpMethod.DELETE, "/users/").denyAll()

                    .antMatchers(HttpMethod.GET, "/trades/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/trades/**").hasRole("USER")
                    .antMatchers(HttpMethod.PUT,"/trades/**").hasRole("USER")
                    .antMatchers(HttpMethod.PATCH,"/trades/image/**").hasRole("USER")

                    .antMatchers(HttpMethod.GET,"/opinions/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/opinion/**").hasRole("USER")
                    .antMatchers(HttpMethod.PUT, "/opinion/**").hasRole("USER")
                    .antMatchers(HttpMethod.DELETE, "/opinion/**").hasRole("USER")

                    .antMatchers(HttpMethod.GET, "/beers/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/beers/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/beers/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/beers/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PATCH, "/beers/image/**").hasRole("ADMIN");
    }
}

