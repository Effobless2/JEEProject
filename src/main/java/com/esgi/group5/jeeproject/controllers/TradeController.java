package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.security.jwt.contracts.IBeererTokenService;
import com.esgi.group5.jeeproject.services.contracts.IAzureService;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
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
import java.util.Optional;

@RestController
@RequestMapping("/trades")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TradeController {
    private final ITradeService tradeService;
    private final IBeererTokenService tokenService;
    private final IAzureService azureService;

    @GetMapping
    public List<Trade> get(){
        return tradeService.get();
    }

    @GetMapping("/{tradeId}")
    public Trade get(@PathVariable("tradeId") int tradeId){
        return tradeService.get(tradeId);
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid Trade trade){
        User user = tokenService.getUser(request);
        if(user == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        trade.setResponsible(user);
        Long id = tradeService.add(trade);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @PatchMapping("/image/{tradeId}")
    public ResponseEntity<?> patchImage(HttpServletRequest request, @PathVariable("tradeId") Long tradeId, @RequestParam("file") MultipartFile file) throws IOException {
        User user = tokenService.getUser(request);
        Trade trade = tradeService.get(tradeId);

        if(trade == null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        if(user == null || (
                trade.getResponsible() != null &&
                !trade.getResponsible().getId().equals(user.getId()))
        ) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        URI url = azureService.uploadImageToStorage(file, tradeId, "trade");
        trade.setProfilePict(String.valueOf(url));
        tradeService.update(trade);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBeersWithFilters(
            HttpServletRequest request,
            @RequestParam("name") Optional<String> name,
            @RequestParam("types") Optional<List<String>> types,
            @RequestParam("lng") Optional<Double> lng,
            @RequestParam("lat") Optional<Double> lat
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.tradeService.filter(name, types, lng, lat));
    }
}
