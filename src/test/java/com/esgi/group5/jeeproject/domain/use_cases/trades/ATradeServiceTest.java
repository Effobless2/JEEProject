package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.TradeDAO;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public abstract class ATradeServiceTest {
    protected ReadTradeService readTradeService;
    protected CreateTradeService createTradeService;

    protected TradeRepository repository;

    protected void setup(){
        repository = mock(TradeRepository.class);
    }

    @Test
    void should_be_empty_after_init(){
        given(repository.get()).willReturn(new ArrayList<>());
        assertTrue(readTradeService.get().isEmpty());
    }

    @Test
    void should_add_new_trade(){
        ArrayList<Trade> mockTrade = new ArrayList<>();
        given(repository.get()).willReturn(mockTrade);

        for (long i = 0L; i < 10; i++) {
            Trade test = new Trade();
            mockTrade.add(test);
            User userTest = new User();
            given(createTradeService.createTrade(test, userTest)).willReturn(test);
            Trade created = createTradeService.createTrade(test, userTest);
            assertEquals(i + 1, readTradeService.get().size());
        }
    }
}
