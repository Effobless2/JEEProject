package com.esgi.group5.jeeproject.infrastructure.web.dtos.beers;

public class EditBeerDTO {
    private String name;
    private String profilePict;
    private String type;
    private double alcoholLevel;
    private String description;

    public EditBeerDTO() {
    }

    public EditBeerDTO(String name, String profilePict, String type, double alcoholLevel, String description) {
        this.name = name;
        this.profilePict = profilePict;
        this.type = type;
        this.alcoholLevel = alcoholLevel;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePict() {
        return profilePict;
    }

    public void setProfilePict(String profilePict) {
        this.profilePict = profilePict;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAlcoholLevel() {
        return alcoholLevel;
    }

    public void setAlcoholLevel(double alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
