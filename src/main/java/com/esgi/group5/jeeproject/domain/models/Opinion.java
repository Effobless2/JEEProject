package com.esgi.group5.jeeproject.domain.models;

public class Opinion extends EntityModel {
    private String name;
    private String comment;

    public Opinion() {
        super();
    }

    public Opinion(Long id, String name, String comment) {
        super(id);
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
