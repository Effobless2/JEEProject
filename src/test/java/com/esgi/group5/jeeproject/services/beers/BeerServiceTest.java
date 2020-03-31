package com.esgi.group5.jeeproject.services.beers;

import com.esgi.group5.jeeproject.services.BeerService;
import org.junit.jupiter.api.BeforeEach;

public class BeerServiceTest extends ABeerServiceTest {

    @BeforeEach
    @Override
    protected void setup(){
        super.setup();
        beerService = new BeerService(beerRepository);
    }
}
