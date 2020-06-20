package com.esgi.group5.jeeproject.repositories;
/*
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.repositories.JpaBeerRepository;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.BeerDAO;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.TradeDAO;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.UserDAO;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BeerRepository implements IBeerRepository {
    private final JpaBeerRepository jpaBeerRepository;

    @Override
    public long add(BeerDAO beer) {
        BeerDAO result = jpaBeerRepository.save(beer);
        return result.getId();
    }

    @Override
    public List<BeerDAO> get() {
        return jpaBeerRepository.findAll();
    }

    @Override
    public BeerDAO get(long id) {
        Optional<BeerDAO> result = jpaBeerRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public boolean update(BeerDAO beer) {
        Optional<BeerDAO> beerDB = jpaBeerRepository.findById(beer.getId());
        if(beerDB.isEmpty())
            return false;
        jpaBeerRepository.save(beer);
        return true;
    }

    @Override
    public boolean delete(long beerId) {
        Optional<BeerDAO> beer = jpaBeerRepository.findById(beerId);
        if(beer.isEmpty())
            return false;
        BeerDAO b = beer.get();
        Collection<UserDAO> users = b.getLikedBy();
        if(users != null){
            users.forEach((UserDAO user) -> {
                Collection<BeerDAO> beers = user.getFavourites();
                beers.remove(b);
            });
        }
        Collection<TradeDAO> trades = b.getTrades();
        if(trades != null){
            trades.forEach((TradeDAO trade) -> {
                Collection<BeerDAO> beers = trade.getItems();
                beers.remove(b);
            });
        }
        jpaBeerRepository.deleteById(b.getId());
        return true;
    }
}*/
