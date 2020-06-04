package com.esgi.group5.jeeproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String label;
}
