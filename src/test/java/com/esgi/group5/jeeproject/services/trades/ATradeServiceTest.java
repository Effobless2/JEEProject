package com.esgi.group5.jeeproject.services.trades;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public abstract class ATradeServiceTest {
    protected ITradeService service;

    protected ITradeRepository repository;

    protected void setup(){
        repository = mock(ITradeRepository.class);
    }

    @Test
    void should_be_empty_after_init(){
        given(repository.get()).willReturn(new ArrayList<>());
        assertTrue(service.get().isEmpty());
    }

    @Test
    void should_add_new_trade_and_returns_new_trade_id(){
        ArrayList<Trade> mockTrade = new ArrayList<>();
        given(repository.get()).willReturn(mockTrade);

        for (long i = 0; i < 10; i++) {
            Trade test = new Trade();
            mockTrade.add(test);

            given(repository.add(test)).willReturn(i);
            long id = service.add(test);
            assertEquals(i, id);
            assertEquals(i + 1, service.get().size());

            given(repository.get(id)).willReturn(test);
            Trade t = service.get(id);
            assertNotNull(t);
            assertEquals(test, t);
        }
    }
}
