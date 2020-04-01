package com.esgi.group5.jeeproject.DAO;

import javax.persistence.*;

@Entity
@Table(name = "beer")
public class BeerDAO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
}