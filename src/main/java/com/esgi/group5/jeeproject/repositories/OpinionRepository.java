package com.esgi.group5.jeeproject.repositories;
/*
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.repositories.JpaOpinionRepository;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.OpinionDAO;
import com.esgi.group5.jeeproject.repositories.contracts.IOpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OpinionRepository implements IOpinionRepository {
    private final JpaOpinionRepository jpaOpinionRepository;

    @Override
    public long add(OpinionDAO opinion) {
        OpinionDAO result = jpaOpinionRepository.save(opinion);
        return result.getId();
    }

    @Override
    public List<OpinionDAO> get() {
        return jpaOpinionRepository.findAll();
    }

    @Override
    public OpinionDAO get(long opinionId) {
        Optional<OpinionDAO> result = jpaOpinionRepository.findById(opinionId);
        return result.orElse(null);
    }
}
*/