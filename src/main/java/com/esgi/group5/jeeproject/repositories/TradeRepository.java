package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;

import java.util.ArrayList;
import java.util.List;

public class TradeRepository implements ITradeRepository {
    private List<Trade> db;

    public TradeRepository() {
        db = new ArrayList<>();
    }

    @Override
    public int add(Trade trade) {
        db.add(trade);
        return db.size() - 1;
    }

    @Override
    public List<Trade> get() {
        return db;
    }

    @Override
    public Trade get(int tradeId) {
        if(tradeId >= db.size())
            return null;
        return db.get(tradeId);
    }
}
