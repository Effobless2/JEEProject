package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class DeleteBeerTest {
    protected DeleteBeer deleteBeer;
    private BeerRepository beerRepository;


    @BeforeEach
    void setup(){
        beerRepository = mock(BeerRepository.class);
        deleteBeer = new DeleteBeer(beerRepository);
    }

    @Test
    void should_delete_beer(){
        assertTrue(deleteBeer.deleteBeer(1L));
    }
}
