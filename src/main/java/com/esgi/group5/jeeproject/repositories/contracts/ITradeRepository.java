package com.esgi.group5.jeeproject.repositories.contracts;


import com.esgi.group5.jeeproject.models.Trade;

import java.util.List;

public interface ITradeRepository {
    int add(Trade beer);
    List<Trade> get();
    Trade get(int id);
}
