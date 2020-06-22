package com.esgi.group5.jeeproject.infrastructure.web.controllers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.use_cases.beers.*;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.beers.EditBeerDTO;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.beers.parsers.BeerParser;
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
    private final CreateBeer createBeer;
    private final UpdateBeer updateBeer;
    private final ReadBeer readBeer;
    private final DeleteBeer deleteBeer;
    private final GetBeerByIdWithTheirSellers getBeerByIdWithTheirSellers;

    public BeerController(
            CreateBeer createBeer,
            UpdateBeer updateBeer,
            ReadBeer readBeer,
            DeleteBeer deleteBeer,
            GetBeerByIdWithTheirSellers getBeerByIdWithTheirSellers) {
        this.createBeer = createBeer;
        this.updateBeer = updateBeer;
        this.readBeer = readBeer;
        this.deleteBeer = deleteBeer;
        this.getBeerByIdWithTheirSellers = getBeerByIdWithTheirSellers;
    }

    @GetMapping
    public ResponseEntity<?> get(){
        Collection<Beer> beers = readBeer.getAllBeers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(beers);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<?> get(@PathVariable("beerId") Long beerId){
        Beer beer = getBeerByIdWithTheirSellers.getBeerByIdWithSellers(beerId);
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
        Beer saved = createBeer.createBeer(beer);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saved.getId());
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<?> put(@PathVariable("beerId") Long beerId, @RequestBody @Valid EditBeerDTO editBeerDTO){
        Beer beer = BeerParser.parse(editBeerDTO);
        beer.setId(beerId);

        Beer updated = updateBeer.updateBeer(beer);

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
        boolean removed = deleteBeer.deleteBeer(beerId);
        return ResponseEntity
            .status(removed ? HttpStatus.OK : HttpStatus.NOT_FOUND)
            .build();
    }

    @PatchMapping("/image/{tradeId}")
    public ResponseEntity<?> patchImage(@PathVariable("tradeId") Long beerId, @RequestParam("file") MultipartFile file) {
        Beer beer = readBeer.getBeerById(beerId);

        if(beer == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Beer updated = updateBeer.updateBeer(beer, file);

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
        Collection<Beer> filteredBeers = readBeer.filter(name, types, alcoholLevel);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(filteredBeers);
    }
}
