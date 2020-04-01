package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import com.esgi.group5.jeeproject.services.contracts.IBeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService implements IBeerService {
    public final IBeerRepository repository;

    @Autowired
    public BeerService(IBeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public int add(Beer beer) {
        return repository.add(beer);
    }

    @Override
    public List<Beer> get() {
        return repository.get();
    }

    @Override
    public Beer get(int id) {
        return repository.get(id);
    }
}
