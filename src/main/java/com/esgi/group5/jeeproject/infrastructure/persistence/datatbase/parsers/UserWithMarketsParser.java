package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.UserDAO;

import java.util.stream.Collectors;

public class UserWithMarketsParser {
    public static User parse(UserDAO userDAO) {
        User result = UserParser.parse(userDAO);
        result.setMarkets(userDAO.getMarkets()
                .stream()
                .map(TradeParser::parse)
                .collect(Collectors.toSet()));
        return result;
    }
}
