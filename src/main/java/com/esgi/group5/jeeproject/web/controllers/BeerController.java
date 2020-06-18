package com.esgi.group5.jeeproject.web.controllers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.use_cases.beers.CreateBeerService;
import com.esgi.group5.jeeproject.domain.use_cases.beers.DeleteBeerService;
import com.esgi.group5.jeeproject.domain.use_cases.beers.ReadBeerService;
import com.esgi.group5.jeeproject.domain.use_cases.beers.UpdateBeerService;
import com.esgi.group5.jeeproject.web.dtos.beers.EditBeerDTO;
import com.esgi.group5.jeeproject.web.dtos.beers.parsers.BeerParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
@CrossOrigin(origins = "*")
public class BeerController {
    private final CreateBeerService createBeerService;
    private final UpdateBeerService updateBeerService;
    private final ReadBeerService readBeerService;
    private final DeleteBeerService deleteBeerService;

    public BeerController(
        CreateBeerService createBeerService,
        UpdateBeerService updateBeerService,
        ReadBeerService readBeerService,
        DeleteBeerService deleteBeerService
    ) {
        this.createBeerService = createBeerService;
        this.updateBeerService = updateBeerService;
        this.readBeerService = readBeerService;
        this.deleteBeerService = deleteBeerService;
    }

    @GetMapping
    public ResponseEntity<?> get(){
        Collection<Beer> beers = readBeerService.get();

        return beers.isEmpty() ?
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build() :
            ResponseEntity
                .status(HttpStatus.OK)
                .body(beers);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<?> get(@PathVariable("beerId") Long beerId){
        Beer beer = readBeerService.get(beerId);
        return beer != null ?
            ResponseEntity
                .status(HttpStatus.OK)
                .body(beer) :
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid EditBeerDTO beerDTO){

        Beer beer = BeerParser.parse(beerDTO);
        Beer saved = createBeerService.createBeer(beer);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saved.getId());
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<?> put(@PathVariable("beerId") Long beerId, @RequestBody @Valid EditBeerDTO editBeerDTO){
        Beer beer = BeerParser.parse(editBeerDTO);
        beer.setId(beerId);

        Beer updated = updateBeerService.updateBeer(beer);

        return updated != null ?
            ResponseEntity
                .status(HttpStatus.OK)
                .body(updated) :
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<?> delete(@PathVariable("beerId") Long beerId){
        boolean removed = deleteBeerService.deleteBeer(beerId);
        return ResponseEntity
            .status(removed ? HttpStatus.OK : HttpStatus.NOT_FOUND)
            .build();
    }

    @PatchMapping("/image/{tradeId}")
    public ResponseEntity<?> patchImage(@PathVariable("tradeId") Long beerId, @RequestParam("file") MultipartFile file) {
        Beer beer = readBeerService.get(beerId);

        if(beer == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Beer updated = updateBeerService.updateBeer(beer, file);

        return updated != null ?
            ResponseEntity
                .status(HttpStatus.OK)
                .body(updated) :
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBeersWithFilters(
            HttpServletRequest request,
            @RequestParam("name") Optional<String> name,
            @RequestParam("types") Optional<List<String>> types,
            @RequestParam("alcoholLevel") Optional<Double> alcoholLevel
    ){
        Collection<Beer> filteredBeers = readBeerService.filter(name, types, alcoholLevel);

        return filteredBeers.isEmpty() ?
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build() :
            ResponseEntity
                .status(HttpStatus.OK)
                .body(filteredBeers);
    }
}
