package com.esgi.group5.jeeproject.domain.use_cases.beers;

import org.junit.jupiter.api.BeforeEach;

public class BeerDAOServiceTest extends ABeerDAOServiceTest {

    @BeforeEach
    @Override
    protected void setup(){
        super.setup();
        createBeerService = new CreateBeerService(beerRepository);
        readBeerService = new ReadBeerService(beerRepository);
        updateBeerService = new UpdateBeerService(imageUploadService, beerRepository);
        deleteBeerService = new DeleteBeerService(beerRepository);
    }
}
