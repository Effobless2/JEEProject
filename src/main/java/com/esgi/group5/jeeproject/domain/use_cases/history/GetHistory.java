package com.esgi.group5.jeeproject.domain.use_cases.history;

import com.esgi.group5.jeeproject.domain.models.History;
import com.esgi.group5.jeeproject.domain.repositories.HistoryRepository;

import java.util.Collection;

public class GetHistory {
    private final HistoryRepository historyRepository;

    public GetHistory(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Collection<History> execute() {
        return historyRepository.getAllResearchs();
    }
}
