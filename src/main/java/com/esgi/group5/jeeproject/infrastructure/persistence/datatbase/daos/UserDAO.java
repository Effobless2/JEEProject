package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatarUrl;
    private String email;

    @OneToMany(mappedBy = "responsible", fetch = FetchType.LAZY)
    private Set<TradeDAO> markets;
/*
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<BeerDAO> favourites;
*/
    public UserDAO() {
    }

    public UserDAO(Long id, String name, String avatarUrl, String email, Set<TradeDAO> markets) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.markets = markets;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<TradeDAO> getMarkets() {
        return markets;
    }

    public void setMarkets(Set<TradeDAO> markets) {
        this.markets = markets;
    }
}
