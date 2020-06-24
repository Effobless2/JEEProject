package com.esgi.group5.jeeproject.domain.models;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Trade extends EntityModel {
    private String name = "";
    private String profilePict = "";
    private String type = "";
    private double longitude;
    private double latitude;
    private String address = "";
    private String description = "";
    private Set<Beer> items = new HashSet<>();
    private User responsible;

    public Trade() {
        super();
    }

    public Trade(Long id, String name, String profilePict, String type, double longitude, double latitude, String address, String description, Set<Beer> items, User responsible) {
        super(id);
        this.name = name;
        this.profilePict = profilePict;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.description = description;
        this.items = items;
        this.responsible = responsible;
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

    public Set<Beer> getItems() {
        return items;
    }

    public void setItems(Set<Beer> items) {
        this.items = items;
    }

    public void addItem(Beer beer) {
        items.add(beer);
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public void removeItem(Beer beer) {
        if(beer.getId() == null)
            return;
        Optional<Beer> existing = items
                .stream()
                .filter(b -> b.getId().equals(beer.getId()))
                .findFirst();
        existing.ifPresent(value -> items.remove(value));
    }

    public boolean isMatchingFilters(Optional<String> name, Optional<List<String>> types) {
        return isMatchingName(name) &&
                isMatchingType(types);
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
}
