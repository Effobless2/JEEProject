package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import com.esgi.group5.jeeproject.services.contracts.IBeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeerService implements IBeerService {
    private final IBeerRepository repository;

    @Override
    public long add(Beer beer) {
        return repository.add(beer);
    }

    @Override
    public List<Beer> get() {
        return repository.get();
    }

    @Override
    public Beer get(long id) {
        return repository.get(id);
    }
}
