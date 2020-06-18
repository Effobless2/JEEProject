package com.esgi.group5.jeeproject.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.BeerDAO;
import com.esgi.group5.jeeproject.persistence.datatbase.parsers.BeerParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface JpaBeerRepository extends JpaRepository<BeerDAO, Long>, BeerRepository {

    default Beer create(Beer beer) {
        BeerDAO dao = BeerParser.parse(beer);
        BeerDAO saved = save(dao);
        return BeerParser.parse(saved);
    }

    default Collection<Beer> get() {
        Collection<BeerDAO> collection = findAll();
        return collection
                .stream()
                .map(BeerParser::parse)
                .collect(Collectors.toList());
    }
    default Optional<Beer> get(Long id) {
        Optional<BeerDAO> found = findById(id);
        return found.map(BeerParser::parse);
    }
    default void delete(Long id) {
        deleteById(id);
    }
    default Optional<Beer> update(Beer beer) {
        if(beer.getId() == null)
            return Optional.empty();
        update(
                beer.getId(),
                beer.getName(),
                beer.getAlcoholLevel(),
                beer.getDescription(),
                beer.getType(),
                beer.getProfilePict());
        return get(beer.getId());
    }


    @Transactional
    @Modifying
    @Query(value = "UPDATE BeerDAO beer " +
            "SET beer.name = :name, " +
            "beer.alcoholLevel = :alcoholLevel," +
            "beer.description = :description," +
            "beer.type = :type," +
            "beer.profilePict = :profilePict " +
            "WHERE beer.id = :id")
    void update(Long id,
                String name,
                Double alcoholLevel,
                String description,
                String type,
                String profilePict
    );
}
