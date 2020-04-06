package com.esgi.group5.jeeproject.repositories.beers;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class ABeerRepositoryTest {
    protected IBeerRepository beerRepository;
    private String name = "test1";

    abstract void setup();

    @Test
    void should_be_empty_after_init(){
        assertThat(beerRepository.get().isEmpty());
    }

    @Test
    void should_add_new_beer_and_returns_new_beer_id(){
        long id = beerRepository.add(new Beer());
        assertEquals(0, id);
    }

    @Test
    void should_get_must_have_1_beer_after_adding_1_beer(){
        long id = beerRepository.add(new Beer());
        assertEquals(1, beerRepository.get().size());
    }

    @Test
    void should_new_beer_can_be_returned_by_get(){
        long id = beerRepository.add(new Beer());
        Beer b = beerRepository.get(id);
        assertNotNull(b);
    }

}
