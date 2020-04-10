package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Beer;

import java.util.List;

public interface IBeerService {
    long add(Beer beer);
    List<Beer> get();
    Beer get(long id);

    boolean update(Beer beer);

    boolean delete(long beerId);
}
