package com.esgi.group5.jeeproject.persistence.datatbase.parsers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.BeerDAO;

public class BeerParser {

    public static Beer parse(BeerDAO beerDAO) {
        Beer result = new Beer();

        result.setId(beerDAO.getId());
        result.setAlcoholLevel(beerDAO.getAlcoholLevel());
        result.setDescription(beerDAO.getDescription());
        result.setName(beerDAO.getName());
        result.setProfilePict(beerDAO.getProfilePict());
        result.setType(beerDAO.getType());

        return result;
    }

    public static BeerDAO parse(Beer beer) {
        BeerDAO result = new BeerDAO();

        result.setId(beer.getId());
        result.setAlcoholLevel(beer.getAlcoholLevel());
        result.setDescription(beer.getDescription());
        result.setName(beer.getName());
        result.setProfilePict(beer.getProfilePict());
        result.setType(beer.getType());

        return result;
    }
}
