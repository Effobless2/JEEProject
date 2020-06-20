package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CreateBeerTest {

    private BeerRepository beerRepository;
    private CreateBeer createBeer;

    @BeforeEach
    void setup(){
        beerRepository = mock(BeerRepository.class);
        createBeer = new CreateBeer(beerRepository);
    }

    @Test
    void should_add_new_beer(){
        ArrayList<Beer> mockBeer = new ArrayList<>();
        given(beerRepository.getAllBeers()).willReturn(mockBeer);
        for(long i = 0L; i < 10; i++){
            Beer test = new Beer();
            mockBeer.add(test);
            given(beerRepository.createBeer(test)).willReturn(test);
            Beer beer = createBeer.createBeer(test);
            assertEquals(i + 1, beerRepository.getAllBeers().size());
            assertEquals(beer, test);
        }
    }
}
