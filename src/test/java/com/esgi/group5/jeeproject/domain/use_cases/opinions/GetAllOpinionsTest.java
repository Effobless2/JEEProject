package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetAllOpinionsTest {
    protected GetAllOpinions getAllOpinions;
    protected OpinionRepository repository;

    @BeforeEach
    void setup() {
        repository = mock(OpinionRepository.class);
        getAllOpinions = new GetAllOpinions(repository);

    }

    @Test
    void should_be_empty_after_init(){
        given(repository.getAllOpinions()).willReturn(new ArrayList<>());
        assertTrue(getAllOpinions.execute().isEmpty());
    }
}
