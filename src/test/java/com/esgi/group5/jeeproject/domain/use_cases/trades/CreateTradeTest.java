package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CreateTradeTest {
    protected CreateTradeService createTradeService;
    protected TradeRepository repository;

    @BeforeEach
    protected void setup() {
        repository = mock(TradeRepository.class);
        createTradeService = new CreateTradeService(repository);
    }


    @Test
    void should_add_new_trade(){
        ArrayList<Trade> mockTrade = new ArrayList<>();
        given(repository.get()).willReturn(mockTrade);

        User userTest = new User();
        for (long i = 0L; i < 10; i++) {
            Trade test = new Trade();
            mockTrade.add(test);
            given(createTradeService.createTrade(test, userTest)).willReturn(test);
            Trade created = createTradeService.createTrade(test, userTest);
            assertNotNull(created);
        }
    }
}
