package com.esgi.group5.jeeproject.domain.models;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Beer extends EntityModel {
    private String name;
    private String profilePict;
    private String type;
    private double alcoholLevel;
    private String description;
    private Set<Trade> sellers = new HashSet<>();

    public Beer() {
        super();
    }

    public Beer(Long id, String name, String profilePict, String type, double alcoholLevel, String description, Set<Trade> sellers) {
        super(id);
        this.name = name;
        this.profilePict = profilePict;
        this.type = type;
        this.alcoholLevel = alcoholLevel;
        this.description = description;
        this.sellers = sellers;
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

    public boolean isMatchingFilters(Optional<String> name, Optional<List<String>> types, Optional<Double> alcoholLevel) {
        return isMatchingName(name) &&
                isMatchingType(types) &&
                isAlcoholLevelNear(alcoholLevel);
    }

    private boolean isMatchingName(Optional<String> name) {
        if(name.isEmpty())
            return true;
        return this.name.toLowerCase()
                .contains(name.get().toLowerCase());
    }

    private boolean isMatchingType(Optional<List<String>> types) {
        if (types.isEmpty() || types.get().isEmpty())
            return true;
        return types.get()
            .stream()
            .map(String::toLowerCase)
            .anyMatch(this.type.toLowerCase()::equals);
    }

    private boolean isAlcoholLevelNear(Optional<Double> alcoholLevel) {
        if(alcoholLevel.isEmpty())
            return true;
        return this.alcoholLevel >= alcoholLevel.get() - 1.0d &&
                this.alcoholLevel <= alcoholLevel.get() + 1.0d;
    }

    public Set<Trade> getSellers() {
        return sellers;
    }

    public void setSellers(Set<Trade> sellers) {
        this.sellers = sellers;
    }
}
