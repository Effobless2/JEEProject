package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.security.JwtUtilService;
import com.esgi.group5.jeeproject.services.contracts.IBeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
public class BeerController {
    private final IBeerService service;

    @Autowired
    JwtUtilService jwtUtilService;

    @GetMapping
    public ResponseEntity<?> get(@RequestHeader Map<String, String> headers){
        if(jwtUtilService.validateToken(headers.get("authorization"))){
            List<Beer> beers = service.get();
            return ResponseEntity.ok().body(beers);
        }else{
            return ResponseEntity.status(403).body("access denied");
        }
    }

    @GetMapping("/{beerId}")
    public Beer get(@PathVariable("beerId") int beerId){
        return service.get(beerId);
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid Beer beer){
        long id = service.add(beer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @PutMapping
    public ResponseEntity put(HttpServletRequest request, @RequestBody @Valid Beer beer){
        boolean result = service.update(beer);
        return ResponseEntity
                .status(result ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(beer);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity delete(@PathVariable("beerId") long beerId){
        boolean result = service.delete(beerId);
        return ResponseEntity
                .status(result ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }
}
