package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Beer;

import java.util.List;
import java.util.Optional;

public interface IBeerService {
    long add(Beer beer);
    List<Beer> get();
    Beer get(long id);

    boolean update(Beer beer);

    boolean delete(long beerId);

    List<Beer> filter(
            Optional<String> name,
            Optional<List<String>> types,
            Optional<Double> alcoholLevel
    );
}
