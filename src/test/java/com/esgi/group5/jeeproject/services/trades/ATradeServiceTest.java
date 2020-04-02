package com.esgi.group5.jeeproject.services.trades;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public abstract class ATradeServiceTest {
    protected ITradeService service;

    abstract void setup();

    @Test
    void should_be_empty_after_init(){
        assertTrue(service.get().isEmpty());
    }

    @Test
    void should_add_new_trade_and_returns_new_trade_id(){
        for (int i = 0; i < 10; i++) {
            Trade test = new Trade();

            int id = service.add(test);
            assertEquals(i, id);
            assertEquals(i + 1, service.get().size());

            Trade t = service.get(id);
            assertNotNull(t);
        }
    }
}
