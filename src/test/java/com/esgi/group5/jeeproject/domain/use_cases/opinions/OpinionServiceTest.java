package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import org.junit.jupiter.api.BeforeEach;

public class OpinionServiceTest extends AOpinionServiceTest {

    @BeforeEach
    @Override
    protected void setup() {
        super.setup();
        readOpinionService = new ReadOpinionService(repository);
        createOpinionService = new CreateOpinionService(repository);
    }
}
