package com.esgi.group5.jeeproject.infrastructure.web.controllers;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.use_cases.trades.CreateTrade;
import com.esgi.group5.jeeproject.domain.use_cases.trades.DeleteTrade;
import com.esgi.group5.jeeproject.domain.use_cases.trades.ReadTrade;
import com.esgi.group5.jeeproject.domain.use_cases.trades.UpdateTrade;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.trades.EditTradeDTO;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.trades.parsers.TradeParser;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.users.UserWithTokenDTO;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.users.parsers.UserParser;
import com.esgi.group5.jeeproject.infrastructure.web.security.beererToken.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trades")
@CrossOrigin(origins = "*")
public class TradeController {
    private final ReadTrade readTrade;
    private final CreateTrade createTrade;
    private final UpdateTrade updateTrade;
    private final DeleteTrade deleteTrade;
    private final TokenProvider tokenProvider;

    public TradeController(ReadTrade readTrade,
                           CreateTrade createTrade,
                           UpdateTrade updateTrade,
                           DeleteTrade deleteTrade,
                           TokenProvider tokenProvider
    ) {
        this.readTrade = readTrade;
        this.createTrade = createTrade;
        this.updateTrade = updateTrade;
        this.deleteTrade = deleteTrade;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping
    public ResponseEntity<?> get(){
        Collection<Trade> trades = readTrade.getAllTrades();

        return trades.isEmpty() ?
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build() :
            ResponseEntity
                .status(HttpStatus.OK)
                .body(trades);
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<?> get(@PathVariable("tradeId") Long tradeId){
        Trade trade = readTrade.getTradeById(tradeId);
        return trade != null ?
            ResponseEntity
                .status(HttpStatus.OK)
                .body(trade) :
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid EditTradeDTO editTradeDTO){
        UserWithTokenDTO user = tokenProvider.getUser(request);

        if(user == null)
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();

        Trade trade = TradeParser.parser(editTradeDTO);
        Trade saved = createTrade.createTrade(trade, UserParser.parse(user));

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saved);
    }

    @PutMapping("/{tradeId}")
    public ResponseEntity<?> put(HttpServletRequest request, @PathVariable("tradeId") Long tradeId, @RequestBody @Valid EditTradeDTO editTradeDTO){
        UserWithTokenDTO user = tokenProvider.getUser(request);

        if(user == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        Trade trade = TradeParser.parser(editTradeDTO);
        Trade updated = updateTrade.updateTrade(trade, UserParser.parse(user));

        return updated != null ?
            ResponseEntity
                .status(HttpStatus.OK)
                .body(updated) :
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PatchMapping("/image/{tradeId}")
    public ResponseEntity<?> patchImage(HttpServletRequest request, @PathVariable("tradeId") Long tradeId, @RequestParam("file") MultipartFile file) throws IOException {
        UserWithTokenDTO user = tokenProvider.getUser(request);

        if(user == null)
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();

        Trade trade = readTrade.getTradeById(tradeId);
        if(trade == null)
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();

        Trade updated = updateTrade.updateTrade(trade, UserParser.parse(user), file);

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
            @RequestParam("name") Optional<String> name,
            @RequestParam("types") Optional<List<String>> types,
            @RequestParam("lng") Optional<Double> lng,
            @RequestParam("lat") Optional<Double> lat
    ){
        Collection<Trade> filteredTrades = readTrade.filter(name, types, lng, lat);
        return filteredTrades.isEmpty() ?
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build() :
            ResponseEntity
                .status(HttpStatus.OK)
                .body(filteredTrades);
    }

    @DeleteMapping("/{tradeId}")
    public ResponseEntity<?> deleteTrade(HttpServletRequest request, @PathVariable("tradeId") Long tradeId) {
        UserWithTokenDTO user = tokenProvider.getUser(request);

        if(user == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        boolean removed = deleteTrade.deleteTrade(tradeId, UserParser.parse(user));
        return ResponseEntity
                .status(removed ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();

    }

}
