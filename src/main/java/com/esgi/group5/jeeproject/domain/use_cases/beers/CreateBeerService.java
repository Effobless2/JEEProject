package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

public class CreateBeerService {
    private final BeerRepository beerRepository;

    public CreateBeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer createBeer(Beer beer) {
        return beerRepository.createBeer(beer);
    }
}
