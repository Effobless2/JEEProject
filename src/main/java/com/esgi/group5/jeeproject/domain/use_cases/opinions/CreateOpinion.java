package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;

public class CreateOpinion {
    private final OpinionRepository opinionRepository;

    public CreateOpinion(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public Opinion createOpinion(Opinion opinion) {
        return opinionRepository.createOpinion(opinion);
    }

}
