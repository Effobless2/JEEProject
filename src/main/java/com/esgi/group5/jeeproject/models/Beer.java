package com.esgi.group5.jeeproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "beers")
@Data
public class Beer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profilePict;
    private String type;
    private double alcoholLevel;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private Collection<Trade> trades;

    @JsonIgnore
    @ManyToMany(mappedBy = "favourites", fetch = FetchType.LAZY)
    private Collection<User> likedBy;

}
