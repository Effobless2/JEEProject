package com.esgi.group5.jeeproject.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    public String name;
    public String password;
    public String email;
}
