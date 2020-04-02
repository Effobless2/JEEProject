package com.esgi.group5.jeeproject.services.trades;

import com.esgi.group5.jeeproject.services.TradeService;
import org.junit.jupiter.api.BeforeEach;

public class TradeServiceTest extends ATradeServiceTest{
    @BeforeEach
    @Override
    void setup() {
        service = new TradeService();
    }
}
