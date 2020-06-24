package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

public class CreateTrade {
    private final TradeRepository tradeRepository;

    public CreateTrade(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Trade execute(Trade trade, User responsible) {
        trade.setResponsible(responsible);
        return tradeRepository.createTrade(trade);
    }
}
