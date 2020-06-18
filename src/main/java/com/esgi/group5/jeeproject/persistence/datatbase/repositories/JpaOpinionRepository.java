package com.esgi.group5.jeeproject.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.domain.models.Opinion;
import com.esgi.group5.jeeproject.domain.repositories.OpinionRepository;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.OpinionDAO;
import com.esgi.group5.jeeproject.persistence.datatbase.parsers.OpinionParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface JpaOpinionRepository extends JpaRepository<OpinionDAO, Long>, OpinionRepository {

    default Opinion create(Opinion opinion) {
        OpinionDAO dao = OpinionParser.parse(opinion);
        OpinionDAO saved = save(dao);
        return OpinionParser.parse(saved);
    }
    default Collection<Opinion> get() {
        return findAll()
                .stream()
                .map(OpinionParser::parse)
                .collect(Collectors.toList());
    }
    default Optional<Opinion> get(Long id) {
        Optional<OpinionDAO> found = findById(id);
        return found.map(OpinionParser::parse);
    }
    default void delete(Long id) {
        deleteById(id);
    }
    default Optional<Opinion> update(Opinion opinion) {
        if(opinion.getId() == null)
            return Optional.empty();
        update(
                opinion.getId(),
                opinion.getName(),
                opinion.getComment());
        return get(opinion.getId());
    }

    @Transactional
    @Modifying
    @Query(value = "UPDATE OpinionDAO opinion " +
            "SET opinion.name = :name, " +
            "opinion.comment = :comment " +
            "WHERE opinion.id = :id")
    void update(Long id,
                String name,
                String comment
    );
}
