package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CreateOpinionTest {
    protected CreateOpinion createOpinion;
    protected OpinionRepository repository;

    @BeforeEach
    void setup() {
        repository = mock(OpinionRepository.class);
        createOpinion = new CreateOpinion(repository);
    }


    @Test
    void should_add_new_opinion(){
        for(long i = 0; i < 10; i++){
            Opinion test = new Opinion();

            given(repository.createOpinion(test)).willReturn(test);
            Opinion opinion = createOpinion.createOpinion(test);
            assertNotNull(opinion);
        }
    }
}
