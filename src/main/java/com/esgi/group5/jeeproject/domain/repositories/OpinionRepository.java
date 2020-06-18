package com.esgi.group5.jeeproject.domain.repositories;

import com.esgi.group5.jeeproject.domain.models.Opinion;

import java.util.Collection;
import java.util.Optional;

public interface OpinionRepository {
    Opinion create(Opinion opinion);
    Collection<Opinion> get();
    Optional<Opinion> get(Long id);
    void delete(Long id);
    Optional<Opinion> update(Opinion opinion);
}
