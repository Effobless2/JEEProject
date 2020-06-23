package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.domain.tools.ImageUploadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateBeerTest {
    private BeerRepository beerRepository;
    private UpdateBeer updateBeer;
    private ImageUploadService imageUploadService;

    @BeforeEach
    void setup(){
        beerRepository = mock(BeerRepository.class);
        imageUploadService = mock(ImageUploadService.class);
        updateBeer = new UpdateBeer(imageUploadService, beerRepository);
    }

    @Test
    void should_update_beer(){
        Beer test = new Beer();
        test.setId(1L);

        when(beerRepository.updateBeer(test)).thenReturn(Optional.of(test));
        Beer b = updateBeer.updateBeer(test);
        assertNotNull(b);
        assertEquals(b, test);
    }
}
