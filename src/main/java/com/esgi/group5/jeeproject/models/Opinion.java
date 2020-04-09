package com.esgi.group5.jeeproject.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "opinions")
@Data
public class Opinion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Beer beer;
}
