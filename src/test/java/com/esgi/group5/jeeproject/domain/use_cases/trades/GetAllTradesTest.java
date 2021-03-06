package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetAllTradesTest {
    protected GetAllTrades getAllTrades;
    protected TradeRepository repository;

    @BeforeEach
    protected void setup() {
        repository = mock(TradeRepository.class);
        getAllTrades = new GetAllTrades(repository);
    }

    @Test
    void should_be_empty_after_init(){
        given(repository.getAllTrades()).willReturn(new ArrayList<>());
        assertTrue(getAllTrades.execute().isEmpty());
    }
}
