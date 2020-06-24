package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;

public class DeleteOpinion {
    private final OpinionRepository opinionRepository;

    public DeleteOpinion(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public void execute(Opinion opinion){
        opinionRepository.deleteOpinionById(opinion.getId());
    }
}
