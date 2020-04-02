package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService implements ITradeService {
    public ITradeRepository repository;

    @Autowired
    public TradeService(ITradeRepository repository) {
        this.repository = repository;
    }

    @Override
    public int add(Trade trade) {
        return repository.add(trade);
    }

    @Override
    public List<Trade> get() {
        return repository.get();
    }

    @Override
    public Trade get(int tradeId) {
        return repository.get(tradeId);
    }
}
