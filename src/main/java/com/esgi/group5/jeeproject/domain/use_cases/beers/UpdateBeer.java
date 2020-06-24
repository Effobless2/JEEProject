package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.domain.services.ImageUploadService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class UpdateBeer {
    private final ImageUploadService imageUploadService;
    private final BeerRepository beerRepository;

    public UpdateBeer(ImageUploadService imageUploadService, BeerRepository beerRepository) {
        this.imageUploadService = imageUploadService;
        this.beerRepository = beerRepository;
    }

    public Beer execute(Beer beer) {
        Optional<Beer> updated = beerRepository.updateBeer(beer);
        return updated.orElse(null);
    }

    public Beer execute(Beer beer, MultipartFile newImageOfTheBeerToUpload) {
        Optional<String> imageUrl = imageUploadService.uploadImageBeerImage(newImageOfTheBeerToUpload, beer.getId());
        imageUrl.ifPresent(beer::setProfilePict);
        return execute(beer);
    }
}
