package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.domain.tools.ImageUploadService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class UpdateBeerService {
    private final ImageUploadService imageUploadService;
    private final BeerRepository beerRepository;

    public UpdateBeerService(ImageUploadService imageUploadService, BeerRepository beerRepository) {
        this.imageUploadService = imageUploadService;
        this.beerRepository = beerRepository;
    }

    public Beer updateBeer(Beer beer) {
        Optional<Beer> updated = beerRepository.updateBeer(beer);
        return updated.orElse(null);
    }

    public Beer updateBeer(Beer beer, MultipartFile image) {
        Optional<String> imageUrl = imageUploadService.uploadImageBeerImage(image, beer.getId());
        imageUrl.ifPresent(beer::setProfilePict);
        return updateBeer(beer);
    }
}
