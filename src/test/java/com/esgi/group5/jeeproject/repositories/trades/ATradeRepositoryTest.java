package com.esgi.group5.jeeproject.repositories.trades;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ATradeRepositoryTest {
    protected ITradeRepository repository;

    abstract void setup();

    @Test
    void should_be_empty_after_init(){
        assertTrue(repository.get().isEmpty());
    }

    @Test
    void should_add_new_trade_and_returns_new_trade_id(){
        for (int i = 0; i < 10; i++) {
            Trade test = new Trade();

            int id = repository.add(test);
            assertEquals(i, id);
            assertEquals(i + 1, repository.get().size());

            Trade t = repository.get(id);
            assertNotNull(t);
        }
    }

}
