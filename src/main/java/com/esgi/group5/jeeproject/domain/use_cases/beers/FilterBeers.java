package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilterBeers {
    private final BeerRepository beerRepository;

    public FilterBeers(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Collection<Beer> execute(Optional<String> name, Optional<List<String>> types, Optional<Double> alcoholLevel) {
        return beerRepository.getBeerWithSellers()
                .stream()
                .filter(beer -> beer.isMatchingFilters(name, types, alcoholLevel))
                .collect(Collectors.toList());
    }
}
