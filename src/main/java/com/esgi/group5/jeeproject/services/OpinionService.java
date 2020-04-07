package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Opinion;
import com.esgi.group5.jeeproject.repositories.contracts.IOpinionRepository;
import com.esgi.group5.jeeproject.services.contracts.IOpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpinionService implements IOpinionService {
    private final IOpinionRepository repository;

    @Override
    public long add(Opinion opinion) {
        return repository.add(opinion);
    }

    @Override
    public List<Opinion> get() {
        return repository.get();
    }

    @Override
    public Opinion get(long opinionId) {
        return repository.get(opinionId);
    }
}
