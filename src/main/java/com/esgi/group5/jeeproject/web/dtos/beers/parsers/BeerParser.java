package com.esgi.group5.jeeproject.web.dtos.beers.parsers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.web.dtos.beers.EditBeerDTO;

public class BeerParser {
    public static Beer parse(EditBeerDTO editBeerDTO){
        Beer result = new Beer();

        result.setAlcoholLevel(editBeerDTO.getAlcoholLevel());
        result.setDescription(editBeerDTO.getDescription());
        result.setName(editBeerDTO.getName());
        result.setProfilePict(editBeerDTO.getProfilePict());
        result.setType(editBeerDTO.getType());

        return result;
    }
}
