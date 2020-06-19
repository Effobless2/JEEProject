package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ReadBeerTest {

    private BeerRepository beerRepository;
    private ReadBeerService readBeerService;

    @BeforeEach
    void setup(){
        beerRepository = mock(BeerRepository.class);
        readBeerService = new ReadBeerService(beerRepository);
    }

    @Test
    void should_be_empty_after_init(){
        given(beerRepository.get()).willReturn(new ArrayList<>());
        assertTrue(readBeerService.get().isEmpty());
    }

    @Test
    void should_return_beers(){
        ArrayList<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());

        given(beerRepository.get()).willReturn(beers);
        assertFalse(readBeerService.get().isEmpty());
        assertEquals(4, readBeerService.get().size());
    }

    @Test
    void should_return_beer_with_correct_id(){
        Beer beer = new Beer();

        given(beerRepository.get(1L)).willReturn(java.util.Optional.of(beer));
        assertNotNull(readBeerService.get(1L));
    }
}
