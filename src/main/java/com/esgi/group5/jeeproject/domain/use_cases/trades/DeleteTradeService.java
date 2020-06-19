package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

public class DeleteTradeService {
    private final TradeRepository tradeRepository;

    public DeleteTradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public boolean deleteTrade(Long tradeId, User user) {
        //TODO: Check if user is the responsible of the concerned Trade
        tradeRepository.deleteTradeWithId(tradeId);
        return true;
    }
}
