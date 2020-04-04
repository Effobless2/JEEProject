package com.esgi.group5.jeeproject.models;

import lombok.Data;

@Data
public class User {
    public String name;
    public String password;
    public String email;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
