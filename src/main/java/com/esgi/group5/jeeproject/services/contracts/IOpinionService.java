package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Opinion;

import java.util.List;

public interface IOpinionService {
    long add(Opinion opinion);
    List<Opinion> get();
    Opinion get(long opinionId);
}
