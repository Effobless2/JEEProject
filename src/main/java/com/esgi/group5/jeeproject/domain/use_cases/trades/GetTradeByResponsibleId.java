package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class GetTradeByResponsibleId {
    private final TradeRepository tradeRepository;

    public GetTradeByResponsibleId(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Collection<Trade> execute(User responsibleUser) {
        if(responsibleUser == null || responsibleUser.getId() == null) {
            return new ArrayList<>();
        }
        return tradeRepository
                .getAllTradeWithBeers()
                .stream()
                .filter(trade -> trade.getResponsible().getId() == responsibleUser.getId())
                .collect(Collectors.toList());
    }
}
