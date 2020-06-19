package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

public class DeleteBeerService {
    private final BeerRepository beerRepository;

    public DeleteBeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public boolean deleteBeer(Long beerId){
        beerRepository.deleteBeerWithId(beerId);
        return true;
    }
}
