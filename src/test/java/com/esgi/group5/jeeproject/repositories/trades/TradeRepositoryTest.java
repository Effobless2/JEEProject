package com.esgi.group5.jeeproject.repositories.trades;

import com.esgi.group5.jeeproject.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;

public class TradeRepositoryTest extends ATradeRepositoryTest {
    @BeforeEach
    @Override
    protected void setup() {
        super.setup();
        repository = new TradeRepository(dal);
    }
}
