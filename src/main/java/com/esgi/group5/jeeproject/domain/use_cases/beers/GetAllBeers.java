package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

import java.util.Collection;

public class GetAllBeers {
    private final BeerRepository beerRepository;

    public GetAllBeers(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Collection<Beer> execute(){
        return beerRepository.getBeerWithSellers();
    }
}
