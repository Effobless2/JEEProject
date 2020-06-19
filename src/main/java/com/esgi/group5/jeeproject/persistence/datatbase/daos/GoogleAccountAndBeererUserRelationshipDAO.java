package com.esgi.group5.jeeproject.persistence.datatbase.daos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "google_account_and_beerer_user_rel")
public class GoogleAccountAndBeererUserRelationshipDAO {
    @Id
    private String googleId;
    private Long beererId;

    public GoogleAccountAndBeererUserRelationshipDAO() {
    }

    public GoogleAccountAndBeererUserRelationshipDAO(String googleId, Long beererId) {
        this.googleId = googleId;
        this.beererId = beererId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public Long getBeererId() {
        return beererId;
    }

    public void setBeererId(Long beererId) {
        this.beererId = beererId;
    }
}
