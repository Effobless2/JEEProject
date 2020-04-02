package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Trade;

import java.util.List;

public interface ITradeService {
    int add(Trade trade);
    List<Trade> get();
    Trade get(int tradeId);
}
