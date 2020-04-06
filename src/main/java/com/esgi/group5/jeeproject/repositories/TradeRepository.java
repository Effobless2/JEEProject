package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
public class TradeRepository implements ITradeRepository {
    private List<Trade> db = new ArrayList<>();

    @Override
    public long add(Trade trade) {
        db.add(trade);
        return db.size() - 1;
    }

    @Override
    public List<Trade> get() {
        return db;
    }

    @Override
    public Trade get(long tradeId) {
        if(tradeId >= db.size())
            return null;
        return db.get((int) tradeId);
    }
}
