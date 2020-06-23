package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReadBeer {
    private final BeerRepository beerRepository;

    public ReadBeer(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Collection<Beer> getAllBeers(){
        return beerRepository.getAllBeers();
    }

    public Beer getBeerById(Long id){
        return beerRepository.getBeerById(id).orElse(null);
    }
}
