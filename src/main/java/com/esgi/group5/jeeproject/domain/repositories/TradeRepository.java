package com.esgi.group5.jeeproject.domain.repositories;

import com.esgi.group5.jeeproject.domain.models.Trade;

import java.util.Collection;
import java.util.Optional;

public interface TradeRepository {
    Trade create(Trade trade);
    Collection<Trade> get();
    Optional<Trade> get(Long id);
    void delete(Long id);
    Optional<Trade> update(Trade trade);
}
