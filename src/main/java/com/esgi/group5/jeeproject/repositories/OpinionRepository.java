package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.DAL.OpinionDAL;
import com.esgi.group5.jeeproject.models.Opinion;
import com.esgi.group5.jeeproject.repositories.contracts.IOpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OpinionRepository implements IOpinionRepository {
    private final OpinionDAL opinionDAL;

    @Override
    public long add(Opinion opinion) {
        Opinion result = opinionDAL.save(opinion);
        return result.getId();
    }

    @Override
    public List<Opinion> get() {
        return opinionDAL.findAll();
    }

    @Override
    public Opinion get(long opinionId) {
        Optional<Opinion> result = opinionDAL.findById(opinionId);
        return result.orElse(null);
    }
}
