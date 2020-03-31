package com.esgi.group5.jeeproject.models;

import lombok.Data;

@Data
public class Beer {
    public String name;

    public Beer() {
    }

    public Beer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
