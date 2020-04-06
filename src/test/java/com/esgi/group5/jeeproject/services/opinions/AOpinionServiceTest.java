package com.esgi.group5.jeeproject.services.opinions;

import com.esgi.group5.jeeproject.models.Opinion;
import com.esgi.group5.jeeproject.repositories.contracts.IOpinionRepository;
import com.esgi.group5.jeeproject.services.contracts.IOpinionService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class AOpinionServiceTest {
    protected IOpinionService service;

    protected IOpinionRepository repository;

    protected void setup(){
        repository = mock(IOpinionRepository.class);
    }

    @Test
    void should_be_empty_after_init(){
        given(repository.get()).willReturn(new ArrayList<>());
        assertTrue(service.get().isEmpty());
    }

    @Test
    void should_add_new_opinion_and_returns_new_opinion_id(){
        ArrayList<Opinion> mockOpinion = new ArrayList<>();
        given(repository.get()).willReturn(mockOpinion);
        for(long i = 0; i < 10; i++){
            Opinion test = new Opinion();
            mockOpinion.add(test);

            given(repository.add(test)).willReturn(i);
            long id = service.add(test);
            assertEquals(i, id);
            assertEquals(i + 1, service.get().size());

            given(repository.get(id)).willReturn(test);
            Opinion o = service.get(id);
            assertNotNull(o);
        }
    }
}
