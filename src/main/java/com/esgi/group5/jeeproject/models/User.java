package com.esgi.group5.jeeproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatarUrl;
    private String email;
    @Column(unique = true)
    private String googleId;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Beer> favourites;

}
