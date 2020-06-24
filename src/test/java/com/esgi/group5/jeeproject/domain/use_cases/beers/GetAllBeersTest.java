package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetAllBeersTest {

    private BeerRepository beerRepository;
    private GetAllBeers getAllBeers;
    private GetBeerById getBeerById;

    @BeforeEach
    void setup(){
        beerRepository = mock(BeerRepository.class);
        getAllBeers = new GetAllBeers(beerRepository);
        getBeerById = new GetBeerById(beerRepository);
    }

    @Test
    void should_be_empty_after_init(){
        given(beerRepository.getAllBeers()).willReturn(new ArrayList<>());
        assertTrue(getAllBeers.execute().isEmpty());
    }

    @Test
    void should_return_beers(){
        ArrayList<Beer> beers = new ArrayList<>();
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());
        beers.add(new Beer());

        given(beerRepository.getAllBeers()).willReturn(beers);
        assertFalse(getAllBeers.execute().isEmpty());
        assertEquals(4, getAllBeers.execute().size());
    }

    @Test
    void should_return_beer_with_correct_id(){
        Beer beer = new Beer();

        given(beerRepository.getBeerById(1L)).willReturn(java.util.Optional.of(beer));
        assertNotNull(getBeerById.execute(1L));
    }
}
