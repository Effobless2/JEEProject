package com.esgi.group5.jeeproject.domain.models;

public class User extends EntityModel {
    private String name;
    private String avatarUrl;
    private String email;

    public User() {
        super();
    }

    public User(Long id, String name, String avatarUrl, String email) {
        super(id);
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.email = email;
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
}
