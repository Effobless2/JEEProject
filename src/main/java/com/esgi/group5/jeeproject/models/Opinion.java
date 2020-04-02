package com.esgi.group5.jeeproject.models;

import lombok.Data;

@Data
public class Opinion {
    private String name;

    public Opinion() {
    }

    public Opinion(String name) {
        this.name = name;
    }
}
