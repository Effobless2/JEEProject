package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Opinion;
import com.esgi.group5.jeeproject.repositories.contracts.IOpinionRepository;
import com.esgi.group5.jeeproject.services.contracts.IOpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpinionService implements IOpinionService {
    private IOpinionRepository repository;


    @Autowired
    public OpinionService(IOpinionRepository repository) {
        this.repository = repository;
    }

    @Override
    public int add(Opinion opinion) {
        return repository.add(opinion);
    }

    @Override
    public List<Opinion> get() {
        return repository.get();
    }

    @Override
    public Opinion get(int opinionId) {
        return repository.get(opinionId);
    }
}
