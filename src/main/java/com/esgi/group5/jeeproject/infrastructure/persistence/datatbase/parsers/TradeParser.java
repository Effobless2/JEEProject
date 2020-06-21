package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.TradeDAO;

public class TradeParser {

    public static Trade parse(TradeDAO tradeDAO) {
        Trade result = new Trade();

        result.setId(tradeDAO.getId());
        result.setName(tradeDAO.getName());
        result.setDescription(tradeDAO.getDescription());
        result.setAddress(tradeDAO.getAddress());
        result.setLatitude(tradeDAO.getLatitude());
        result.setLongitude(tradeDAO.getLongitude());
        result.setProfilePict(result.getProfilePict());
        result.setType(result.getType());
        result.setResponsible(UserParser.parse(tradeDAO.getResponsible()));

        return result;
    }

    public static TradeDAO parse(Trade trade) {
        TradeDAO result = new TradeDAO();

        result.setId(trade.getId());
        result.setName(trade.getName());
        result.setDescription(trade.getDescription());
        result.setAddress(trade.getAddress());
        result.setLatitude(trade.getLatitude());
        result.setLongitude(trade.getLongitude());
        result.setProfilePict(result.getProfilePict());
        result.setType(result.getType());
        result.setResponsible(UserParser.parse(trade.getResponsible()));
        return result;
    }
}
