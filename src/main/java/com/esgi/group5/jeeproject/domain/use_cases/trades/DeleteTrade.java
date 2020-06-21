package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.Optional;

public class DeleteTrade {
    private final TradeRepository tradeRepository;

    public DeleteTrade(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public boolean deleteTrade(Long tradeId, User user) {
        Optional<Trade> trade = tradeRepository.getTradeById(tradeId);
        if (trade.isEmpty())
            return false;
        if (trade.get().getResponsible().getId() != user.getId())
            return false;
        tradeRepository.deleteTradeById(tradeId);
        return true;
    }
}
