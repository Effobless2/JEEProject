package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.DAL.BeerDAL;
import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BeerRepository implements IBeerRepository {
    private final BeerDAL beerDal;

    @Override
    public long add(Beer beer) {
        Beer result = beerDal.save(beer);
        return result.getId();
    }

    @Override
    public List<Beer> get() {
        return beerDal.findAll();
    }

    @Override
    public Beer get(long id) {
        Optional<Beer> result = beerDal.findById(id);
        return result.orElse(null);
    }

    @Override
    public boolean update(Beer beer) {
        Optional<Beer> beerDB = beerDal.findById(beer.getId());
        if(beerDB.isEmpty())
            return false;
        beerDal.save(beer);
        return true;
    }

    @Override
    public boolean delete(long beerId) {
        Optional<Beer> beer = beerDal.findById(beerId);
        if(beer.isEmpty())
            return false;
        Beer b = beer.get();
        beerDal.delete(b);
        return true;
    }
}
