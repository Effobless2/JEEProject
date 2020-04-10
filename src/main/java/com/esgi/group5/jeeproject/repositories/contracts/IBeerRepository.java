package com.esgi.group5.jeeproject.repositories.contracts;

import com.esgi.group5.jeeproject.models.Beer;

import java.util.List;

public interface IBeerRepository {
    long add(Beer beer);
    List<Beer> get();
    Beer get(long id);

    boolean update(Beer beer);

    boolean delete(long beerId);
}
