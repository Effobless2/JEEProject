package com.esgi.group5.jeeproject.persistence.datatbase.daos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "trades")
public class TradeDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profilePict;
    private String type;
    private double longitude;
    private double latitude;
    private String address;
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<BeerDAO> items;

    @ManyToOne
    private UserDAO responsible;


    public TradeDAO() {
    }

    public TradeDAO(Long id, String name, String profilePict, String type, double longitude, double latitude, String address, String description) {
        this.id = id;
        this.name = name;
        this.profilePict = profilePict;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
