package com.esgi.group5.jeeproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "trades")
@Data
public class Trade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profilePict;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Beer> items;

    @ManyToOne
    private User responsible;
}
