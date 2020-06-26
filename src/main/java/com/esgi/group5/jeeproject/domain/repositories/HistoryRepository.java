package com.esgi.group5.jeeproject.domain.repositories;


import com.esgi.group5.jeeproject.domain.models.History;

import java.util.Collection;

public interface HistoryRepository {
    Collection<History> getAllResearchs();
    History saveResearch(History research);
}
