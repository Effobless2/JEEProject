package com.esgi.group5.jeeproject.services.opinions;

import com.esgi.group5.jeeproject.services.OpinionService;
import org.junit.jupiter.api.BeforeEach;

public class OpinionServiceTest extends AOpinionServiceTest {

    @BeforeEach
    @Override
    protected void setup() {
        super.setup();
        service = new OpinionService(repository);
    }
}
