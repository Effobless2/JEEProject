package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.exceptions.BeerDoesntExistException;
import com.esgi.group5.jeeproject.domain.exceptions.TradeDoesntExistException;
import com.esgi.group5.jeeproject.domain.exceptions.UserNotAllowedToManageStocksException;
import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
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

    public boolean execute(Long tradeId, Long beerId, User userWhoAddsBeer)
            throws TradeDoesntExistException, UserNotAllowedToManageStocksException, BeerDoesntExistException {
        Optional<Trade> trade = tradeRepository.getTradeByIdWithBeers(tradeId);

        if(trade.isEmpty())
            throw new TradeDoesntExistException();

        if (userWhoAddsBeer == null || userWhoAddsBeer.getId() != trade.get().getResponsible().getId())
            throw new UserNotAllowedToManageStocksException();
        Optional<Beer> beer = beerRepository.getBeerById(beerId);

        if(beer.isEmpty())
            throw new BeerDoesntExistException();

        return removeBeerToTradeItems(trade.get(), beer.get());
    }

    private boolean removeBeerToTradeItems(Trade trade, Beer beer) {
        trade.removeItem(beer);
        tradeRepository.updateTradeItems(trade);
        return true;
    }
}
