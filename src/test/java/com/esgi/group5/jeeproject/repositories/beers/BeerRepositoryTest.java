package com.esgi.group5.jeeproject.repositories.beers;

import com.esgi.group5.jeeproject.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;

public class BeerRepositoryTest extends ABeerRepositoryTest {

    @BeforeEach
    @Override
    protected void setup() {
        super.setup();
        this.beerRepository = new BeerRepository(dal);
    }
}
