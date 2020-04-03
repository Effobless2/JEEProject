package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.models.Opinion;
import com.esgi.group5.jeeproject.repositories.contracts.IOpinionRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpinionRepository implements IOpinionRepository {
    private List<Opinion> db;

    public OpinionRepository() {
        db = new ArrayList<>();
    }

    @Override
    public int add(Opinion opinion) {
        db.add(opinion);
        return db.size() - 1;
    }

    @Override
    public List<Opinion> get() {
        return db;
    }

    @Override
    public Opinion get(int opinionId) {
        if(opinionId >= db.size())
            return null;
        return db.get(opinionId);
    }
}
