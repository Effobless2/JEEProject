package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.OpinionDAO;

public class OpinionParser {
    public static Opinion parse(OpinionDAO opinionDAO) {
        Opinion result = new Opinion();

        result.setId(opinionDAO.getId());
        result.setName(opinionDAO.getName());
        result.setComment(opinionDAO.getComment());

        return result;
    }


    public static OpinionDAO parse(Opinion opinion) {
        OpinionDAO result = new OpinionDAO();

        result.setId(opinion.getId());
        result.setName(opinion.getName());
        result.setComment(opinion.getComment());

        return result;
    }
}
