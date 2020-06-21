package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.TradeDAO;

import java.util.stream.Collectors;

public class TradeWithBeerParser {
    public static Trade parse(TradeDAO tradeDAO) {
        Trade result = TradeParser.parse(tradeDAO);
        result.setItems(
            tradeDAO
                .getItems()
                .stream()
                .map(BeerParser::parse)
                .collect(Collectors.toSet()));

        return result;
    }

    public static TradeDAO parse(Trade trade) {
        TradeDAO result = TradeParser.parse(trade);
        result.setItems(
            trade
                .getItems()
                .stream()
                .map(BeerParser::parse)
                .collect(Collectors.toSet()));

        return result;
    }
}
