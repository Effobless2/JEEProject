package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService implements ITradeService {
    private List<Trade> db = new ArrayList<>();

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
