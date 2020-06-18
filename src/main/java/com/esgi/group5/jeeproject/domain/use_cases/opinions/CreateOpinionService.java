package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;

public class CreateOpinionService {
    private final OpinionRepository opinionRepository;

    public CreateOpinionService(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public Opinion createOpinion(Opinion opinion) {
        return opinionRepository.create(opinion);
    }

}
