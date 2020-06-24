package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.exceptions.TradeDoesntExistException;
import com.esgi.group5.jeeproject.domain.exceptions.UserNotAllowedToDeleteTradeException;
import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.Optional;

public class DeleteTrade {
    private final TradeRepository tradeRepository;

    public DeleteTrade(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public boolean execute(Long tradeId, User user) throws TradeDoesntExistException, UserNotAllowedToDeleteTradeException {
        Optional<Trade> trade = tradeRepository.getTradeById(tradeId);
        if (trade.isEmpty())
            throw new TradeDoesntExistException();
        if (trade.get().getResponsible().getId() != user.getId())
            throw new UserNotAllowedToDeleteTradeException();
        tradeRepository.deleteTradeById(tradeId);
        return true;
    }
}
