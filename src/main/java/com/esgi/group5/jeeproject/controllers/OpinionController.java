package com.esgi.group5.jeeproject.controllers;

import com.esgi.group5.jeeproject.models.Opinion;
import com.esgi.group5.jeeproject.services.contracts.IOpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/opinions")
@RequiredArgsConstructor
public class OpinionController {
    private final IOpinionService service;

    @GetMapping
    public List<Opinion> get(){
        return service.get();
    }

    @GetMapping("/{opinionId}")
    public Opinion get(@PathVariable("opinionId") int opinionId){
        return service.get(opinionId);
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid Opinion opinion){
        long id = service.add(opinion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }
}
