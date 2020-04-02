package com.esgi.group5.jeeproject.models;

import lombok.Data;

@Data
public class Trade {
    public String name;

    public Trade() {
    }

    public Trade(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
