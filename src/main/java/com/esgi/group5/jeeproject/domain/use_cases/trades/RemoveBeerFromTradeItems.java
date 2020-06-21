package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.Optional;

public class RemoveBeerFromTradeItems {
    private final BeerRepository beerRepository;
    private final TradeRepository tradeRepository;

    public RemoveBeerFromTradeItems(BeerRepository beerRepository, TradeRepository tradeRepository) {
        this.beerRepository = beerRepository;
        this.tradeRepository = tradeRepository;
    }

    public boolean removeBeerFromTradeItems(Long tradeId, Long beerId) {
        Optional<Trade> trade = tradeRepository.getTradeByIdWithBeers(tradeId);

        if(trade.isEmpty())
            return false;

        Optional<Beer> beer = beerRepository.getBeerById(beerId);

        if(beer.isEmpty())
            return false;

        return removeBeerToTradeItems(trade.get(), beer.get());
    }

    private boolean removeBeerToTradeItems(Trade trade, Beer beer) {
        trade.removeItem(beer);
        tradeRepository.updateTradeItems(trade);
        return true;
    }
}
