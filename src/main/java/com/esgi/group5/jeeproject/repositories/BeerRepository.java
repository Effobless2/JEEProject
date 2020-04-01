package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeerRepository implements IBeerRepository {
    private List<Beer> db;

    public BeerRepository() {
        db = new ArrayList<>();
    }

    @Override
    public int add(Beer beer) {
        db.add(beer);
        return db.size() - 1;
    }

    @Override
    public List<Beer> get() {
        return db;
    }

    @Override
    public Beer get(int id) {
        if(id >= db.size())
            return null;
        return db.get(id);
    }
}
