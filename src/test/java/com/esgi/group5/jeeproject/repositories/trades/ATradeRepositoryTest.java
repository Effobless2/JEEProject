package com.esgi.group5.jeeproject.repositories.trades;

import com.esgi.group5.jeeproject.DAL.TradeDAL;
import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class ATradeRepositoryTest {
    protected ITradeRepository repository;
    protected TradeDAL dal;

    protected void setup(){
        dal = mock(TradeDAL.class);
    }

    @Test
    void should_be_empty_after_init(){
        when(dal.findAll()).thenReturn(new ArrayList<>());
        assertTrue(repository.get().isEmpty());
    }

    @Test
    void should_add_new_trade_and_returns_new_trade_id(){
        List<Trade> trades = new ArrayList<>();
        when(dal.findAll()).thenReturn(trades);
        for (long i = 0; i < 10; i++) {
            Trade test = new Trade();
            test.setId(i);
            trades.add(test);

            when(dal.save(test)).thenReturn(test);
            long id = repository.add(test);
            assertEquals(i, id);
            assertEquals(i + 1, repository.get().size());

            when(dal.findById(id)).thenReturn(Optional.of(test));
            Trade t = repository.get(id);
            assertNotNull(t);
        }
    }

}
