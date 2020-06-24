package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

public class DeleteBeer {
    private final BeerRepository beerRepository;

    public DeleteBeer(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public boolean execute(Long beerId){
        beerRepository.deleteBeerById(beerId);
        return true;
    }
}
