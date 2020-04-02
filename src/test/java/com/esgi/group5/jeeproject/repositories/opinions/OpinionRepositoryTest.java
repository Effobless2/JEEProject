package com.esgi.group5.jeeproject.repositories.opinions;

import com.esgi.group5.jeeproject.repositories.OpinionRepository;
import org.junit.jupiter.api.BeforeEach;

public class OpinionRepositoryTest extends AOpinionRepositoryTest {
    @BeforeEach
    @Override
    void setup() {
        repository = new OpinionRepository();
    }
}
