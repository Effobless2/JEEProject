package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

public class DeleteTrade {
    private final TradeRepository tradeRepository;

    public DeleteTrade(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public boolean deleteTrade(Long tradeId, User user) {
        //TODO: Check if user is the responsible of the concerned Trade
        tradeRepository.deleteTradeById(tradeId);
        return true;
    }
}
