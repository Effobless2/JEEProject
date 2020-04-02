package com.esgi.group5.jeeproject.repositories.opinions;

import com.esgi.group5.jeeproject.models.Opinion;
import com.esgi.group5.jeeproject.repositories.contracts.IOpinionRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AOpinionRepositoryTest {
    protected IOpinionRepository repository;

    abstract void setup();


    @Test
    void should_be_empty_after_init(){
        assertThat(repository.get().isEmpty());
    }

    @Test
    void should_add_new_user_and_returns_new_user_id(){
        int id = repository.add(new Opinion());
        assertEquals(0, id);
    }

    @Test
    void should_get_must_have_1_user_after_adding_1_user(){
        int id = repository.add(new Opinion());
        assertEquals(1, repository.get().size());
    }

    @Test
    void should_new_user_can_be_returned_by_get(){
        Opinion test = new Opinion();
        int id = repository.add(test);
        Opinion o = repository.get(id);
        assertNotNull(o);
        assertEquals(test, o);
    }
}
