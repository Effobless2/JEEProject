package com.esgi.group5.jeeproject.repositories.contracts;

import com.esgi.group5.jeeproject.models.Beer;

import java.util.List;

public interface IBeerRepository {
    int add(Beer beer);
    List<Beer> get();
    Beer get(int id);
}
