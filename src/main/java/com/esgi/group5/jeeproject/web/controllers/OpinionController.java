package com.esgi.group5.jeeproject.web.controllers;
/*
import com.esgi.group5.jeeproject.persistence.datatbase.daos.OpinionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/opinions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OpinionController {
    private final IOpinionService service;

    @GetMapping
    public List<OpinionDAO> get(){
        return service.get();
    }

    @GetMapping("/{opinionId}")
    public OpinionDAO get(@PathVariable("opinionId") int opinionId){
        return service.get(opinionId);
    }

    @PostMapping
    public ResponseEntity<?> post(HttpServletRequest request, @RequestBody @Valid OpinionDAO opinion){
        long id = service.add(opinion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }
}
*/