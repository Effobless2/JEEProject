package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.BeerDAO;

import java.util.stream.Collectors;

public class BeerWithSellersParser {
    public static Beer parse(BeerDAO beerDAO) {
        Beer result = BeerParser.parse(beerDAO);
        result.setSellers(beerDAO
                .getSellers()
                .stream()
                .map(TradeParser::parse)
                .collect(Collectors.toSet()));

        return result;
    }

    public static BeerDAO parse(Beer beer) {
        BeerDAO result = BeerParser.parse(beer);
        result.setSellers(beer.getSellers()
                .stream()
                .map(TradeParser::parse)
                .collect(Collectors.toSet()));
        return result;
    }
}
