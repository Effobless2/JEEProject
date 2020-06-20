package com.esgi.group5.jeeproject.domain.repositories;

import com.esgi.group5.jeeproject.domain.models.Beer;

import java.util.Collection;
import java.util.Optional;

public interface BeerRepository {
    Beer createBeer(Beer beer);
    Collection<Beer> getAllBeers();
    Optional<Beer> getBeerById(Long id);
    void deleteBeerById(Long id);
    Optional<Beer> updateBeer(Beer beer);
}
