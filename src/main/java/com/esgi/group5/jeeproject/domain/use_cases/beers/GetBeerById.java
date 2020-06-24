package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

public class GetBeerById {
    private final BeerRepository beerRepository;

    public GetBeerById(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer execute(Long id){
        return beerRepository.getBeerById(id).orElse(null);
    }
}
