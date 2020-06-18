package com.esgi.group5.jeeproject.domain.use_cases.trades;

import org.junit.jupiter.api.BeforeEach;

public class TradeServiceTest extends ATradeServiceTest{
    @BeforeEach
    @Override
    protected void setup() {
        super.setup();
        createTradeService = new CreateTradeService(repository);
        readTradeService = new ReadTradeService(repository);
    }
}
