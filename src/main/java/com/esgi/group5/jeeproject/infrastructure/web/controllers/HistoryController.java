package com.esgi.group5.jeeproject.infrastructure.web.controllers;

import com.esgi.group5.jeeproject.domain.use_cases.history.GetHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "*")
public class HistoryController {
    private final GetHistory getHistory;

    public HistoryController(GetHistory getHistory) {
        this.getHistory = getHistory;
    }

    @GetMapping
    public ResponseEntity<?> getAllResearches() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getHistory.execute());
    }
}
