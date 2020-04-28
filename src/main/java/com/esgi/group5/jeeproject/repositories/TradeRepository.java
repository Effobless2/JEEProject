package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.DAL.TradeDAL;
import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TradeRepository implements ITradeRepository {
    private final TradeDAL tradeDAL;

    @Override
    public long add(Trade trade) {
        Trade result = tradeDAL.save(trade);
        return result.getId();
    }

    @Override
    public List<Trade> get() {
        return tradeDAL.findAll();
    }

    @Override
    public Trade get(long tradeId) {
        Optional<Trade> result = tradeDAL.findById(tradeId);
        return result.orElse(null);
    }

    @Override
    public boolean update(Trade trade) {
        Optional<Trade> t = tradeDAL.findById(trade.getId());
        if(t.isEmpty())
            return false;
        tradeDAL.save(trade);
        return true;
    }
}
