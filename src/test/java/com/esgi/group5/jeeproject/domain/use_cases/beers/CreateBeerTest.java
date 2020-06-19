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
    private CreateBeerService createBeerService;

    @BeforeEach
    void setup(){
        beerRepository = mock(BeerRepository.class);
        createBeerService = new CreateBeerService(beerRepository);
    }

    @Test
    void should_add_new_beer(){
        ArrayList<Beer> mockBeer = new ArrayList<>();
        given(beerRepository.get()).willReturn(mockBeer);
        for(long i = 0L; i < 10; i++){
            Beer test = new Beer();
            mockBeer.add(test);
            given(beerRepository.create(test)).willReturn(test);
            Beer beer = createBeerService.createBeer(test);
            assertEquals(i + 1, beerRepository.get().size());
            assertEquals(beer, test);
        }
    }
}
