package com.esgi.group5.jeeproject.models;

import lombok.Data;

@Data
public class User {
    public String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
