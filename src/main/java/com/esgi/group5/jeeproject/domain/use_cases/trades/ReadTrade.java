package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReadTrade {
    private final TradeRepository tradeRepository;

    public ReadTrade(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Collection<Trade> getAllTrades(){
        return tradeRepository.getAllTrades();
    }

    public Trade getTradeById(Long id){
        return tradeRepository.getTradeById(id).orElse(null);
    }
}
