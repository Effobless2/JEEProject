package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

public class CreateTradeService {
    private final TradeRepository tradeRepository;

    public CreateTradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Trade createTrade(Trade trade, User responsible) {
        //TODO: Link User to trade
        return tradeRepository.createTrade(trade);
    }
}
