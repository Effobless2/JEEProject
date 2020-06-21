package com.esgi.group5.jeeproject.domain.models;

import java.util.Set;

public class User extends EntityModel {
    private String name;
    private String avatarUrl;
    private String email;
    private Set<Trade> markets;

    public User() {
        super();
    }

    public User(Long id, String name, String avatarUrl, String email, Set<Trade> markets) {
        super(id);
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.markets = markets;
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

    public Set<Trade> getMarkets() {
        return markets;
    }

    public void setMarkets(Set<Trade> markets) {
        this.markets = markets;
    }
}
