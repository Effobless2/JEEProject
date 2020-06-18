package com.esgi.group5.jeeproject.domain.models;

public class EntityModel {
    private Long id;

    public EntityModel() {
    }

    public EntityModel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
