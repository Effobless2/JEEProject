package com.esgi.group5.jeeproject.infrastructure.web.dtos.trades.parsers;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.trades.EditTradeDTO;

public class TradeParser {
    public static Trade parser(EditTradeDTO editTradeDTO){
        Trade result = new Trade();

        result.setName(editTradeDTO.getName());
        result.setDescription(editTradeDTO.getDescription());
        result.setAddress(editTradeDTO.getAddress());
        result.setLatitude(editTradeDTO.getLatitude());
        result.setLongitude(editTradeDTO.getLongitude());
        result.setProfilePict(editTradeDTO.getProfilePict());
        result.setType(editTradeDTO.getType());

        return result;
    }
}
