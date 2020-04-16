package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trades")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TradeController {
    private final ITradeService service;

    @GetMapping
    public List<Trade> get(){
        return service.get();
    }

    @GetMapping("/{tradeId}")
    public Trade get(@PathVariable("tradeId") int tradeId){
        return service.get(tradeId);
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid Trade trade){
        long id = service.add(trade);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }
}
