package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers;

import com.esgi.group5.jeeproject.domain.models.History;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.HistoryDAO;

public class HistoryParser {
    public static History parse(HistoryDAO historyDAO) {
        History result = new History();

        result.setId(historyDAO.getId());
        result.setResultCount(historyDAO.getResultCount());
        result.setFields(historyDAO.getFields());
        result.setType(historyDAO.getType());

        return result;
    }

    public static HistoryDAO parse(History history) {
        HistoryDAO result = new HistoryDAO();

        result.setId(history.getId());
        result.setResultCount(history.getResultCount());
        result.setFields(history.getFields());
        result.setType(history.getType());

        return result;
    }
}
