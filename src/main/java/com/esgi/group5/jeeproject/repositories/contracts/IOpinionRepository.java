package com.esgi.group5.jeeproject.repositories.contracts;

import com.esgi.group5.jeeproject.models.Opinion;

import java.util.List;

public interface IOpinionRepository {
    long add(Opinion opinion);
    List<Opinion> get();
    Opinion get(long opinionId);
}
