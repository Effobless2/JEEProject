package com.esgi.group5.jeeproject.domain.repositories;

import com.esgi.group5.jeeproject.domain.models.Opinion;

import java.util.Collection;
import java.util.Optional;

public interface OpinionRepository {
    Opinion createOpinion(Opinion opinion);
    Collection<Opinion> getAllOpinions();
    Optional<Opinion> getOpinionById(Long id);
    void deleteOpinionById(Long id);
    Optional<Opinion> updateOpinion(Opinion opinion);
}
