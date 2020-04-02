package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.Trade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeController {
    private List<Trade> db;

    public TradeController() {
        db = new ArrayList<>();
    }

    @GetMapping
    public List<Trade> get(){
        return db;
    }

    @GetMapping("/{tradeId}")
    public Trade get(@PathVariable("tradeId") int tradeId){
        if(tradeId >= db.size())
            return null;
        return db.get(tradeId);
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid Trade trade){
        db.add(trade);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(db.size() - 1);
    }
}
