package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

public class GetTradeById {
    private final TradeRepository tradeRepository;

    public GetTradeById(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Trade execute(Long id){
        return tradeRepository.getTradeById(id).orElse(null);
    }
}
