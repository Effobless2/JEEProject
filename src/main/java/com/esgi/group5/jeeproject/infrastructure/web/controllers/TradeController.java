package com.esgi.group5.jeeproject.infrastructure.web.controllers;

import com.esgi.group5.jeeproject.domain.exceptions.*;
import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.use_cases.trades.*;
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
    private final GetAllTrades getAllTrades;
    private final CreateTrade createTrade;
    private final UpdateTrade updateTrade;
    private final DeleteTrade deleteTrade;
    private final GetTradeByIdWithTheirBeers getTradeByIdWithTheirBeers;
    private final MakeBeerSoldByTrade makeBeerSoldByTrade;
    private final RemoveBeerFromTradeItems removeBeerFromTradeItems;
    private final TokenProvider tokenProvider;
    private final FilterTrades filterTrades;
    private final GetTradeById getTradeById;
    private final GetTradeByResponsibleId getTradeByResponsibleId;

    public TradeController(GetAllTrades getAllTrades,
                           CreateTrade createTrade,
                           UpdateTrade updateTrade,
                           DeleteTrade deleteTrade,
                           GetTradeByIdWithTheirBeers getTradeByIdWithTheirBeers,
                           MakeBeerSoldByTrade makeBeerSoldByTrade,
                           RemoveBeerFromTradeItems removeBeerFromTradeItems,
                           TokenProvider tokenProvider,
                           FilterTrades filterTrades, GetTradeById tradeById, GetTradeByResponsibleId getTradeByResponsibleId) {
        this.getAllTrades = getAllTrades;
        this.createTrade = createTrade;
        this.updateTrade = updateTrade;
        this.deleteTrade = deleteTrade;
        this.getTradeByIdWithTheirBeers = getTradeByIdWithTheirBeers;
        this.makeBeerSoldByTrade = makeBeerSoldByTrade;
        this.removeBeerFromTradeItems = removeBeerFromTradeItems;
        this.tokenProvider = tokenProvider;
        this.filterTrades = filterTrades;
        this.getTradeById = tradeById;
        this.getTradeByResponsibleId = getTradeByResponsibleId;
    }

    @GetMapping
    public ResponseEntity<?> get(){
        Collection<Trade> trades = getAllTrades.execute();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(trades);
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<?> get(@PathVariable("tradeId") Long tradeId){
        Trade trade = getTradeByIdWithTheirBeers.execute(tradeId);
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
        Trade saved = createTrade.execute(trade, UserParser.parse(user));

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(saved.getId());
    }

    @PutMapping("/{tradeId}")
    public ResponseEntity<?> put(HttpServletRequest request, @PathVariable("tradeId") Long tradeId, @RequestBody @Valid EditTradeDTO editTradeDTO){
        UserWithTokenDTO user = tokenProvider.getUser(request);

        if(user == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        Trade trade = TradeParser.parser(editTradeDTO);
        trade.setId(tradeId);

        try {
            Trade updated = updateTrade.execute(trade, UserParser.parse(user));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updated);

        } catch (UserNotAllowedToUpdateTradeException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        } catch (TradeDoesntExistException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PatchMapping("/image/{tradeId}")
    public ResponseEntity<?> patchImage(HttpServletRequest request, @PathVariable("tradeId") Long tradeId, @RequestParam("file") MultipartFile file) throws IOException {
        UserWithTokenDTO user = tokenProvider.getUser(request);

        if (user == null)
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();

        Trade trade = getTradeById.execute(tradeId);
        if(trade == null)
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();

        try {
            Trade updated = updateTrade.execute(trade, UserParser.parse(user), file);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updated);

        } catch (UserNotAllowedToUpdateTradeException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        } catch (TradeDoesntExistException e) {

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBeersWithFilters(
            @RequestParam("name") Optional<String> name,
            @RequestParam("types") Optional<List<String>> types,
            @RequestParam("lng") Optional<Double> lng,
            @RequestParam("lat") Optional<Double> lat
    ){
        Collection<Trade> filteredTrades = filterTrades.execute(name, types, lng, lat);
        return ResponseEntity
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

        try {
            boolean removed = deleteTrade.execute(tradeId, UserParser.parse(user));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (TradeDoesntExistException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } catch (UserNotAllowedToDeleteTradeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

    }

    @PatchMapping("/{tradeId}/add/{beerId}")
    public ResponseEntity<?> addBeerToTradeItems(
            HttpServletRequest request,
            @PathVariable("tradeId") Long tradeId,
            @PathVariable("beerId") Long beerId) {

        UserWithTokenDTO user = tokenProvider.getUser(request);
        if(user == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        try {

            boolean beerAdded = makeBeerSoldByTrade.execute(tradeId, beerId, UserParser.parse(user));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (TradeDoesntExistException | BeerDoesntExistException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } catch (UserNotAllowedToManageStocksException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }

    @PatchMapping("/{tradeId}/remove/{beerId}")
    public ResponseEntity<?> removeBeerToTradeItems(
            HttpServletRequest request,
            @PathVariable("tradeId") Long tradeId,
            @PathVariable("beerId") Long beerId) {

        UserWithTokenDTO user = tokenProvider.getUser(request);
        if(user == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        try {

            boolean beerAdded = removeBeerFromTradeItems.execute(tradeId, beerId, UserParser.parse(user));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();

        } catch (TradeDoesntExistException | BeerDoesntExistException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } catch (UserNotAllowedToManageStocksException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }

    @GetMapping("/mine")
    public ResponseEntity<?> getAllTradesOfCallingUser(HttpServletRequest request) {
        UserWithTokenDTO user = tokenProvider.getUser(request);

        if(user == null)
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();

        Collection<Trade> result = getTradeByResponsibleId.execute(UserParser.parse(user));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);

    }
}
