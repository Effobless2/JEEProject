package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

import java.util.Optional;

public class GetBeerByIdWithTheirSellers {
    private final BeerRepository beerRepository;

    public GetBeerByIdWithTheirSellers(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer getBeerByIdWithSellers(Long beerId) {
        Optional<Beer> beer = beerRepository.getBeerByIdWithSellers(beerId);
        return beer.orElse(null);
    }
}
