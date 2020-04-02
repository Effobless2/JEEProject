package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Opinion;

import java.util.List;

public interface IOpinionService {
    int add(Opinion opinion);
    List<Opinion> get();
    Opinion get(int opinionId);
}