package com.esgi.group5.jeeproject.repositories.beers;

import com.esgi.group5.jeeproject.DAL.BeerDAL;
import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class ABeerRepositoryTest {
    protected IBeerRepository beerRepository;
    protected BeerDAL dal;

    protected void setup(){
        dal = mock(BeerDAL.class);
    }

    @Test
    void should_be_empty_after_init(){
        when(dal.findAll()).thenReturn(new ArrayList<>());
        assertThat(beerRepository.get().isEmpty());
    }

    @Test
    void should_add_new_beer_and_returns_new_beer_id(){
        Beer test = new Beer();
        test.setId((long)1);
        when(dal.save(test)).thenReturn(test);
        long id = beerRepository.add(test);
        assertEquals(1, id);
    }

    @Test
    void should_get_must_have_1_beer_after_adding_1_beer(){
        ArrayList<Beer> beers = new ArrayList<>();
        Beer test = new Beer();
        test.setId((long)1);
        beers.add(test);
        when(dal.save(test)).thenReturn(test);
        when(dal.findAll()).thenReturn(beers);
        long id = beerRepository.add(test);
        assertEquals(1, beerRepository.get().size());
    }

    @Test
    void should_new_beer_can_be_returned_by_get(){
        Beer test = new Beer();
        test.setId((long)1);
        when(dal.save(test)).thenReturn(test);
        long id = beerRepository.add(test);
        when(dal.findById(id)).thenReturn(Optional.of(test));
        Beer b = beerRepository.get(id);
        assertNotNull(b);
    }

    @Test
    void should_update_beer(){
        Beer test = new Beer();
        test.setId((long) 1);
        test.setName("First");

        when(dal.findById(test.getId())).thenReturn(Optional.of(test));
        when(dal.save(test)).thenReturn(test);

        assertTrue(beerRepository.update(test));

    }

    @Test
    void should_not_update_beer(){
        Beer test = new Beer();
        test.setId((long) 1);
        test.setName("First");

        when(dal.findById(test.getId())).thenReturn(Optional.empty());

        assertFalse(beerRepository.update(test));

    }

}
