package com.esgi.group5.jeeproject.persistence.datatbase.daos;

import javax.persistence.*;

@Entity
@Table(name = "beers")
public class BeerDAO{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profilePict;
    private String type;
    private double alcoholLevel;
    private String description;
/*
    @JsonIgnore
    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private Collection<TradeDAO> trades;

    @JsonIgnore
    @ManyToMany(mappedBy = "favourites", fetch = FetchType.LAZY)
    private Collection<UserDAO> likedBy;
*/
    public BeerDAO() {
    }

    public BeerDAO(Long id, String name, String profilePict, String type, double alcoholLevel, String description) {
        this.id = id;
        this.name = name;
        this.profilePict = profilePict;
        this.type = type;
        this.alcoholLevel = alcoholLevel;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
