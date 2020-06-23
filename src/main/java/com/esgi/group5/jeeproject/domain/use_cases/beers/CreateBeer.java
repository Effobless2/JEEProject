package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

public class CreateBeer {
    private final BeerRepository beerRepository;

    public CreateBeer(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer createBeer(Beer beer) {
        return beerRepository.createBeer(beer);
    }
}
