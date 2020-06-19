package com.esgi.group5.jeeproject.domain.repositories;

import com.esgi.group5.jeeproject.domain.models.Trade;

import java.util.Collection;
import java.util.Optional;

public interface TradeRepository {
    Trade createTrade(Trade trade);
    Collection<Trade> getAllTrades();
    Optional<Trade> getTradeById(Long id);
    void deleteTradeWithId(Long id);
    Optional<Trade> updateTrade(Trade trade);
}
