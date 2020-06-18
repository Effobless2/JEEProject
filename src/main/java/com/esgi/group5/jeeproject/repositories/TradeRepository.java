package com.esgi.group5.jeeproject.repositories;
/*
import com.esgi.group5.jeeproject.persistence.datatbase.repositories.JpaTradeRepository;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.TradeDAO;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TradeRepository implements ITradeRepository {
    private final JpaTradeRepository jpaTradeRepository;

    @Override
    public long add(TradeDAO trade) {
        TradeDAO result = jpaTradeRepository.save(trade);
        return result.getId();
    }

    @Override
    public List<TradeDAO> get() {
        return jpaTradeRepository.findAll();
    }

    @Override
    public TradeDAO get(long tradeId) {
        Optional<TradeDAO> result = jpaTradeRepository.findById(tradeId);
        return result.orElse(null);
    }

    @Override
    public boolean update(TradeDAO trade) {
        Optional<TradeDAO> t = jpaTradeRepository.findById(trade.getId());
        if(t.isEmpty())
            return false;
        jpaTradeRepository.save(trade);
        return true;
    }
}
*/