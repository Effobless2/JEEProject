package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.services.contracts.IBeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
public class BeerController {
    public final IBeerService service;

    @GetMapping
    public List<Beer> get(){
        return service.get();
    }

    @GetMapping("/{beerId}")
    public Beer get(@PathVariable("beerId") int beerId){
        return service.get(beerId);
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid Beer beer){
        int id = service.add(beer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }
}
