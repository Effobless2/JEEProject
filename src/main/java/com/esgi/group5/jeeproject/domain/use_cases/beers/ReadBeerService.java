package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReadBeerService {
    private final BeerRepository beerRepository;

    public ReadBeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Collection<Beer> getAllBeers(){
        return beerRepository.getAllBeers();
    }

    public Beer getBeerById(Long id){
        return beerRepository.getBeerById(id).orElse(null);
    }

    public Collection<Beer> filter(Optional<String> name, Optional<List<String>> types, Optional<Double> alcoholLevel) {
        return getAllBeers()
                .stream()
                .filter(beer -> beer.isMatchingFilters(name, types, alcoholLevel))
                .collect(Collectors.toList());
    }
}
