package com.esgi.group5.jeeproject.services.beers;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import com.esgi.group5.jeeproject.services.contracts.IBeerService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class ABeerServiceTest {
    protected IBeerService beerService;

    protected IBeerRepository beerRepository;

    protected void setup(){
        beerRepository = mock(IBeerRepository.class);
    }

    @Test
    void should_be_empty_after_init(){
        given(beerRepository.get()).willReturn(new ArrayList<>());
        assertTrue(beerService.get().isEmpty());
    }

    @Test
    void should_add_new_beer_and_returns_new_beer_id(){
        ArrayList<Beer> mockBeer = new ArrayList<>();
        given(beerRepository.get()).willReturn(mockBeer);
        for(int i = 0; i < 10; i++){
            Beer test = new Beer();
            mockBeer.add(test);

            given(beerRepository.add(test)).willReturn(i);
            int id = beerService.add(test);
            assertEquals(i, id);
            assertEquals(i + 1, beerService.get().size());

            given(beerRepository.get(id)).willReturn(test);
            Beer b = beerService.get(id);
            assertNotNull(b);
        }
    }
}
