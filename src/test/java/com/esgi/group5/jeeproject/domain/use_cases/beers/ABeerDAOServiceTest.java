package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.domain.tools.ImageUploadService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class ABeerDAOServiceTest {
    protected CreateBeerService createBeerService;
    protected ReadBeerService readBeerService;
    protected UpdateBeerService updateBeerService;
    protected DeleteBeerService deleteBeerService;

    protected ImageUploadService imageUploadService;
    protected BeerRepository beerRepository;

    protected void setup(){
        beerRepository = mock(BeerRepository.class);
    }

    @Test
    void should_be_empty_after_init(){
        given(beerRepository.get()).willReturn(new ArrayList<>());
        assertTrue(readBeerService.get().isEmpty());
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
            assertEquals(i + 1, readBeerService.get().size());
            assertEquals(beer, test);
        }
    }

    @Test
    void should_update_beer(){
        Beer test = new Beer();
        test.setId(1L);

        when(beerRepository.update(test)).thenReturn(Optional.of(test));
        Beer b = updateBeerService.updateBeer(test);
        assertNotNull(b);
        assertEquals(b, test);
    }

    @Test
    void should_delete_beer(){
        assertTrue(deleteBeerService.deleteBeer(1L));
    }
}
