package com.esgi.group5.jeeproject.repositories.opinions;

import com.esgi.group5.jeeproject.DAL.OpinionDAL;
import com.esgi.group5.jeeproject.models.Opinion;
import com.esgi.group5.jeeproject.repositories.contracts.IOpinionRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AOpinionRepositoryTest {
    protected IOpinionRepository repository;
    protected OpinionDAL dal;

    protected void setup(){
        dal = mock(OpinionDAL.class);
    }


    @Test
    void should_be_empty_after_init(){
        when(dal.findAll()).thenReturn(new ArrayList<>());
        assertThat(repository.get().isEmpty());
    }

    @Test
    void should_add_new_user_and_returns_new_opinion_id(){
        Opinion test = new Opinion();
        test.setId((long) 1);
        when(dal.save(test)).thenReturn(test);
        long id = repository.add(test);
        assertEquals(test.getId(), id);
    }

    @Test
    void should_get_must_have_1_opinion_after_adding_1_opinion(){
        List<Opinion> mockTest = new ArrayList<>();
        Opinion test = new Opinion();
        test.setId((long) 1);
        mockTest.add(test);
        when(dal.save(test)).thenReturn(test);
        when(dal.findAll()).thenReturn(mockTest);
        long id = repository.add(test);
        assertEquals(1, repository.get().size());
    }

    @Test
    void should_new_opinion_can_be_returned_by_get(){
        Opinion test = new Opinion();
        test.setId((long) 1);
        when(dal.save(test)).thenReturn(test);
        long id = repository.add(test);
        when(dal.findById(id)).thenReturn(Optional.of(test));
        Opinion o = repository.get(id);
        assertNotNull(o);
        assertEquals(test, o);
    }
}
