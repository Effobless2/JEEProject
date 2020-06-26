package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.domain.models.History;
import com.esgi.group5.jeeproject.domain.repositories.HistoryRepository;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.HistoryDAO;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers.HistoryParser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public interface JpaHistoryRepository extends JpaRepository<HistoryDAO, Long>, HistoryRepository {
    default Collection<History> getAllResearchs() {
        return findAll()
                .stream()
                .map(HistoryParser::parse)
                .collect(Collectors.toList());
    }
    default History saveResearch(History research) {
        HistoryDAO dao = HistoryParser.parse(research);

        HistoryDAO saved = save(dao);

        return HistoryParser.parse(saved);
    }
}
