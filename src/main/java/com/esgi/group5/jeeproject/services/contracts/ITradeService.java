package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Trade;

import java.util.List;
import java.util.Optional;

public interface ITradeService {
    long add(Trade trade);
    List<Trade> get();
    Trade get(long tradeId);
    boolean update(Trade trade);

    List<Trade> filter(
            Optional<String> name,
            Optional<List<String>> types,
            Optional<Double> lng,
            Optional<Double> lat
    );
}
