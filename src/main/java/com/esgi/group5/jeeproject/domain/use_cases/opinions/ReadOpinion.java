package com.esgi.group5.jeeproject.domain.use_cases.opinions;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;

import java.util.Collection;

public class ReadOpinion {
    private final OpinionRepository opinionRepository;

    public ReadOpinion(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    public Collection<Opinion> getAllOpinions(){
        return this.opinionRepository.getAllOpinions();
    }
}
