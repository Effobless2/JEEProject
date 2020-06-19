package com.esgi.group5.jeeproject.infrastructure.web.dtos.users;

import java.util.ArrayList;
import java.util.List;

public class UserWithTokenDTO {
    private Long id;
    private String name;
    private String avatarUrl;
    private String email;
    private List<String> roles;

    public UserWithTokenDTO() {
        roles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addRoles(String role) {
        if(roles.contains(role))
            return;
        roles.add(role);
    }
}
