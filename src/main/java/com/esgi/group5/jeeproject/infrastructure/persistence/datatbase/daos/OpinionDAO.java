package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos;

import javax.persistence.*;

@Entity
@Table(name = "opinions")
public class OpinionDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String comment;

    @ManyToOne
    @JoinColumn
    private UserDAO user;

    @ManyToOne
    @JoinColumn
    private BeerDAO beer;

    public OpinionDAO() {
    }

    public OpinionDAO(Long id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
