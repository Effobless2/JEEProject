package com.esgi.group5.jeeproject.repositories.trades;

import com.esgi.group5.jeeproject.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;

public class TradeRepositoryTest extends ATradeRepositoryTest {
    @BeforeEach
    @Override
    void setup() {
        repository = new TradeRepository();
    }
}
