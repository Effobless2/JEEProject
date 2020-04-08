package com.esgi.group5.jeeproject.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "beers")
@Data
public class Beer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
}
