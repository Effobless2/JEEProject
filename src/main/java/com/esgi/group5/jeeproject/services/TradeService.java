package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService implements ITradeService {
    private final ITradeRepository repository;

    @Override
    public long add(Trade trade) {
        return repository.add(trade);
    }

    @Override
    public List<Trade> get() {
        return repository.get();
    }

    @Override
    public Trade get(long tradeId) {
        return repository.get(tradeId);
    }

    @Override
    public boolean update(Trade trade) {
        return repository.update(trade);
    }
}
