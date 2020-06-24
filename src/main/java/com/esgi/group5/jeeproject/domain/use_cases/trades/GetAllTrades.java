package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.Collection;

public class GetAllTrades {
    private final TradeRepository tradeRepository;

    public GetAllTrades(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Collection<Trade> execute(){
        return tradeRepository.getAllTrades();
    }
}
