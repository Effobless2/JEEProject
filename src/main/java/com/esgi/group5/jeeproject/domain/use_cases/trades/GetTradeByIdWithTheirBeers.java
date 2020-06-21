package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.Optional;

public class GetTradeByIdWithTheirBeers {
    private final TradeRepository tradeRepository;

    public GetTradeByIdWithTheirBeers(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Trade getTradeByIdWithTheirBeers(Long tradeId) {
        Optional<Trade> trade = tradeRepository.getTradeByIdWithBeers(tradeId);
        return trade.orElse(null);
    }
}
