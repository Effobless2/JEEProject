package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class AOpinionServiceTest {
    protected ReadOpinionService readOpinionService;
    protected CreateOpinionService createOpinionService;

    protected OpinionRepository repository;

    protected void setup(){
        repository = mock(OpinionRepository.class);
    }

    @Test
    void should_be_empty_after_init(){
        given(repository.get()).willReturn(new ArrayList<>());
        assertTrue(readOpinionService.get().isEmpty());
    }

    @Test
    void should_add_new_opinion(){
        ArrayList<Opinion> mockOpinion = new ArrayList<>();
        given(repository.get()).willReturn(mockOpinion);
        for(long i = 0; i < 10; i++){
            Opinion test = new Opinion();
            mockOpinion.add(test);

            given(repository.create(test)).willReturn(test);
            Opinion opinion = createOpinionService.createOpinion(test);
            assertEquals(i, opinion.getId());
            assertEquals(i + 1, readOpinionService.get().size());
        }
    }
}
