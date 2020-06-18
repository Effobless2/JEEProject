package com.esgi.group5.jeeproject.domain.repositories;

import com.esgi.group5.jeeproject.domain.models.Beer;

import java.util.Collection;
import java.util.Optional;

public interface BeerRepository {
    Beer create(Beer beer);
    Collection<Beer> get();
    Optional<Beer> get(Long id);
    void delete(Long id);
    Optional<Beer> update(Beer beer);
}
