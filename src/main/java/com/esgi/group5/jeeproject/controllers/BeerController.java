package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.security.jwt.contracts.IBeererTokenService;
import com.esgi.group5.jeeproject.services.contracts.IAzureService;
import com.esgi.group5.jeeproject.services.contracts.IBeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BeerController {
    private final IBeerService service;
    private final IBeererTokenService tokenService;
    private final IAzureService azureService;

    @GetMapping
    public ResponseEntity<?> get(@RequestHeader Map<String, String> headers){
        List<Beer> beers = service.get();
        return ResponseEntity.ok().body(beers);
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

    @PutMapping("/{beerId}")
    public ResponseEntity put(HttpServletRequest request, @PathVariable("beerId") Long beerId, @RequestBody @Valid Beer beer){
        Beer older = service.get(beerId);

        if(older == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(beerId);

        Fill(beer, older);
        boolean result = service.update(older);

        return ResponseEntity
                .status(result ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(older);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity delete(@PathVariable("beerId") long beerId){
        boolean result = service.delete(beerId);
        return ResponseEntity
                .status(result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND)
                .build();
    }

    @PatchMapping("/image/{tradeId}")
    public ResponseEntity<?> patchImage(HttpServletRequest request, @PathVariable("tradeId") Long beerId, @RequestParam("file") MultipartFile file) throws IOException {
        User user = tokenService.getUser(request);
        Beer beer = service.get(beerId);

        if(beer == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        URI url = azureService.uploadImageToStorage(file, beerId, "beer");
        beer.setProfilePict(String.valueOf(url));
        service.update(beer);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBeersWithFilters(
            HttpServletRequest request,
            @RequestParam("name") Optional<String> name,
            @RequestParam("types") Optional<List<String>> types,
            @RequestParam("alcoholLevel") Optional<Double> alcoholLevel
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.service.filter(name, types, alcoholLevel));
    }

    private void Fill(Beer source, Beer destination){
        destination.setName(source.getName());
        destination.setType(source.getType());
        destination.setProfilePict(source.getProfilePict());
        destination.setDescription(source.getDescription());
    }
}
