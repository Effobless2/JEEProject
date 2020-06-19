package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;

public class DeleteOpinionService {
    private final OpinionRepository opinionRepository;

    public DeleteOpinionService(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public void deleteOpinion(Opinion opinion){
        opinionRepository.deleteOpinionWithId(opinion.getId());
    }
}
