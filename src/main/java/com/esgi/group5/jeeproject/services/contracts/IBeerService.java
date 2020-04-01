package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Beer;

import java.util.List;

public interface IBeerService {
    int add(Beer beer);
    List<Beer> get();
    Beer get(int id);
}
